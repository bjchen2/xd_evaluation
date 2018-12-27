package com.xd.evaluation.service.impl;

import com.xd.evaluation.dao.repository.*;
import com.xd.evaluation.domain.*;
import com.xd.evaluation.dto.EvaluationInfo;
import com.xd.evaluation.enums.CourseTypeEnum;
import com.xd.evaluation.service.EvaluationService;
import com.xd.evaluation.service.UserService;
import com.xd.evaluation.utils.EntityUtil;
import com.xd.evaluation.utils.EnumUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: Kripath
 * @Modified By:
 * @Date: Created in 8:50 2018/10/16
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class EvaluationServiceImp implements EvaluationService {

    @Autowired
    private UserService userService;

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Autowired
    private EvaluationContentRepository evaluationContentRepository;

    @Autowired
    private UserLikeRepository userLikeRepository;

    @Autowired
    private FavoritesRepository favoritesRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public void likeEvaluation(Long userId, Long evaId, Boolean isLike) throws Exception {
        // 先查询数据库是否存在记录
        UserLike likeTemp =
                userLikeRepository.findByLikeTypeAndObjIdAndUserId(10, evaId, userId);
        if(null == likeTemp) {  // 没有这样的记录，所以需要添加
            likeTemp = new UserLike();

            if(isLike) likeTemp.setIsLike(true);    // 如果用户点的是赞同，就设为1
            else likeTemp.setIsLike(false);

            likeTemp.setLikeType(10);
            likeTemp.setObjId(evaId);
            likeTemp.setUserId(userId);
            // 创建这样一条记录
            userLikeRepository.save(likeTemp);

            // 同时还要更新evaluation表里的agree_count/disagree_count
            if(isLike) {    // 不能写在外面，因为从反对改为点赞和null改为点赞操作不一样
                // 只需要增加一个点赞数即可
                evaluationRepository.updateAgreeCountByEvaluationId(evaId, 1);
            } else {
                evaluationRepository.updateDisagreeCountByEvaluationId(evaId, 1);
            }
        } else {    // 如果存在记录，则只需要修改即可
            userLikeRepository.
                    updateIsLikeByLikeTypeAndObjIdAndUserId(10, evaId, userId, isLike);
            if(isLike) {    // 说明用户之前点的是反对，所以要把反对取消，再给点赞+1
                evaluationRepository.updateDisagreeCountByEvaluationId(evaId, -1);
                evaluationRepository.updateAgreeCountByEvaluationId(evaId, 1);
            } else {
                evaluationRepository.updateAgreeCountByEvaluationId(evaId, -1);
                evaluationRepository.updateDisagreeCountByEvaluationId(evaId, 1);
            }
        }
    }

    @Override
    public void cancelLikeEvaluation(Long userId, Long evaId, Boolean isLike) throws Exception {
        userLikeRepository.deleteByLikeTypeAndObjIdAndUserId(10, evaId, userId);

        // 还要修改evaluation表里的 赞数/反对数
        if(isLike) {    // 假如取消的是点赞，那么就要对agree_count减1
            evaluationRepository.updateAgreeCountByEvaluationId(evaId, -1);
        } else {
            evaluationRepository.updateDisagreeCountByEvaluationId(evaId, -1);
        }
    }

    @Override
    public List<EvaluationInfo> returnAllEvaluationOrder(
            Long userId, String key, Integer type, Integer sortType) throws Exception {
        // 先查询基础的数据
        List<Object[]> metaList;
        if(null == key || "".equals(key)) {
            // 不使用关键词
            // 当0 == type时，第一个参数为true，则不限定type搜索
            if(0 == sortType) {
                metaList = evaluationRepository
                        .findEvaluationMetaInfoOrderByCreateTime((0 == type), type);
            } else {
                metaList = evaluationRepository
                        .findEvaluationMetaInfoOrderByAgreeCount((0 == type), type);
            }
        } else {    // 关键词搜索
           if(0 == sortType) {
               metaList = evaluationRepository
                       .findEvaluationMetaInfoWithKeyWordOrderByCreateTime((0 == type), type, key);
           } else {
               metaList = evaluationRepository
                       .findEvaluationMetaInfoWithKeyWordOrderByAgreeCount((0 == type), type, key);
           }
        }

        // 对一些原始数据进行二次处理
        EntityUtil.processObjectListToEvalutionInfo(metaList);

        /* 把对象数组集合转化为DTO对象集合 */
        EvaluationInfo temp =    // 这是为了控制传入参数的临时对象
                new EvaluationInfo(1L, 1L, "111",
                        "111", "111", "111",
                        false, 1, 1, 1L);
        List<EvaluationInfo> evaList =
                EntityUtil.castEntity(metaList, EvaluationInfo.class, temp);

        /* 进行额外信息的查询和设置 */
        for (EvaluationInfo info : evaList) {
            // 查询用户是否给评价点赞
            UserLike likeObj =
                    userLikeRepository.findByLikeTypeAndObjIdAndUserId
                            (10, info.getEvaluationId(), userId);
            if(null != likeObj) {info.setIsLike(likeObj.getIsLike());}

            // 查询用户是否收藏该课
            Favorite favObj = favoritesRepository
                    .findByEvaluationIdAndUserId(info.getEvaluationId(), userId);
            info.setIsFavorited(null != favObj);

            // 查询评论数量
            Integer commentCount = commentRepository.countByEvaluationId(info.getEvaluationId());
            info.setCommentCount(commentCount);
        }

        return evaList;
    }

    @Override
    public void addEvaluation(Long userId, String content, String teacherName, String courseName,
                              Integer courseType, Boolean isRecommended) throws Exception {
        Evaluation eva = new Evaluation();
        eva.setUserId(userId);
        eva.setTeacherName(teacherName);
        eva.setCourseName(courseName);
        eva.setCourseType(courseType);
        eva.setIsRecommended(isRecommended);
        eva.setAgreeCount(0);
        eva.setDisagreeCount(0);

        Evaluation temp = evaluationRepository.save(eva);   // 从返回值中获取id

        EvaluationContent evaContent = new EvaluationContent();
        evaContent.setEvaluationId(temp.getEvaluationId());
        evaContent.setEvaluationContent(content);

        evaluationContentRepository.save(evaContent);
    }

    @Override
    public List<EvaluationInfo> returnUserAllEvaluation(Long userId) throws Exception {
        List<EvaluationInfo> infos = new ArrayList<>();

        List<Evaluation> evaList = evaluationRepository.findAllByUserId(userId);
        for(Evaluation eva: evaList) {
            EvaluationInfo info = new EvaluationInfo();
            BeanUtils.copyProperties(eva, info);    // eva -> info
            EvaluationContent evaContent
                    = evaluationContentRepository.findByEvaluationId(info.getEvaluationId());
            info.setEvaluationContent(evaContent.getEvaluationContent());

            Favorite fav =  // 查询是否收藏
                    favoritesRepository.findByEvaluationIdAndUserId(info.getEvaluationId(), userId);
            info.setIsFavorited(null != fav);

            infos.add(info);
        }
        return infos;
    }

    @Override
    public List<EvaluationInfo> returnUserFavorite(Long userId) throws Exception {
        List<Object[]> metaList = evaluationRepository.findFavoriteByUserId(userId);

        // 查询不到就直接返回
        if(null == metaList || metaList.isEmpty()) { return null;}

        // 对一些原始数据进行二次处理
        EntityUtil.processObjectListToEvalutionInfo(metaList);
        /* 把对象数组集合转化为DTO对象集合 */
        EvaluationInfo temp =    // 这是为了控制传入参数的临时对象
                new EvaluationInfo(1L, 1L, "111",
                        "111", "111", "111",
                        false, 1, 1, 1L);


        List<EvaluationInfo> infos =
                EntityUtil.castEntity(metaList, EvaluationInfo.class, temp);

        // 添加username属性，根据userId查找
        for(EvaluationInfo info: infos) {
            User user = userService.findByUserId(info.getUserId());
            info.setUserName(user == null ? null : user.getUserName());
            // 因为是查询用户收藏的评价，所以一定是已收藏状态
            info.setIsFavorited(true);
            // 添加点赞状态
            UserLike likeEntity = userLikeRepository
                    .findByLikeTypeAndObjIdAndUserId(10, info.getEvaluationId(), userId);
            if(likeEntity != null) { info.setIsLike(likeEntity.getIsLike()); }

            // 查询评论数量
            info.setCommentCount(commentRepository
                    .countByEvaluationId(info.getEvaluationId()));
        }
        return infos;
    }

    @Override
    public void deleteEvaluationById(Long evaluationId) throws Exception {
        evaluationRepository.deleteById(evaluationId);
    }

    @Override
    public void favoriteEvaluation(Long evaluationId, Long userId) throws Exception {
        // 查询用户是否收藏过评价
        Favorite isFavorited
                = favoritesRepository.findByEvaluationIdAndUserId(evaluationId, userId);
        if(isFavorited != null) { return; }

        Favorite record = new Favorite();
        record.setUserId(userId);
        record.setEvaluationId(evaluationId);

        favoritesRepository.save(record);
    }

    @Override
    public void cancelFavorite(Long evaluationId, Long userId) throws Exception {
        favoritesRepository.deleteByEvaluationIdAndUserId(evaluationId, userId);
    }

    @Override
    public void putUsernameToEvaluationInfoList(List<EvaluationInfo> infos) {
        for(EvaluationInfo info: infos) {
            User user = userService.findByUserId(info.getUserId());
            info.setUserName(user == null ? null : user.getUserName());
        }
    }


}
