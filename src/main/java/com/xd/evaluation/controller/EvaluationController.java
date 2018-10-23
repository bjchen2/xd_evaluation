package com.xd.evaluation.controller;

import com.xd.evaluation.VO.ResultVO;
import com.xd.evaluation.dto.EvaluationInfo;
import com.xd.evaluation.service.CommentService;
import com.xd.evaluation.service.EvaluationService;
import com.xd.evaluation.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 进行评价有关的操作（注：评论的点赞操作也在这里）
 * @Author: Kripath
 * @Modified By:
 * @Date: Created in 8:38 2018/10/16
 */
@RestController
@RequestMapping(value = "/evaluation")
public class EvaluationController {
    @Autowired
    private static final Logger LOGGER = LoggerFactory.getLogger(EvaluationController.class);

    @Autowired
    private EvaluationService evaluationService;

    @Autowired
    private CommentService commentService;

    /**
     * 给 评论/评价 点赞
     * @param userId 点赞的用户
     * @param objId 被点赞的 评论/评价 id
     * @param isLike 是否赞同 true：赞同
     * @param likeType 评价还是评价 10：评价 11：评论
     * @return
     * @throws Exception
     */
    @PostMapping("/like")
    public ResultVO likeEvaluation(Long userId, Long objId, Boolean isLike, Integer likeType)
        throws Exception {
        if(10 == likeType) {    // 进行评价的点赞操作
            LOGGER.info("用户" + userId + "将进行评价" + objId + "的 点赞/反对 操作");
            evaluationService.likeEvaluation(userId, objId, isLike);
            return ResultUtil.success();
        } else if(11 == likeType) {
            LOGGER.info("用户" + userId + "将进行评论" + objId + "的 点赞/反对 操作");
            commentService.likeComment(userId, objId, isLike);
            return ResultUtil.success();
        } else {    // 异常值
            LOGGER.error("likeType值不能为" + likeType);
            return ResultUtil.error("likeType非法值");
        }
    }

    /**
     * 取消用户对 评价/评论 的点赞/反对 操作
     * @param userId 进行操作的用户id
     * @param objId 取消的 评价/评论 id
     * @param isLike 取消的是 赞同/反对 true:赞同
     * @param likeType 评价/评论 10：评价 | 11：评论
     * @return
     * @throws Exception
     */
    @DeleteMapping("/like")
    public ResultVO cancelLike(Long userId, Long objId, Boolean isLike, Integer likeType)
            throws Exception {
        if(10 == likeType) {    // 取消评价的点赞
            LOGGER.info("用户" + userId + "将要取消评价" + objId + "的点赞/反对");
            evaluationService.cancelLikeEvaluation(userId, objId, isLike);
            return ResultUtil.success();
        } else if(11 == likeType) { // 取消评论的点赞
            LOGGER.info("用户" + userId + "将要取消评论" + objId + "的点赞/反对");
            commentService.cancelLikeComment(userId, objId, isLike);
            return ResultUtil.success();
        } else {    // 异常值
            LOGGER.error("likeType值不能为" + likeType);
            return ResultUtil.error("likeType非法值");
        }
    }

    /**
     * 获取全部评价
     * @param userId 进行查询的用户id
     * @param sort 排序方式：0（默认）：按最新排序，1：按点赞量排序
     * @param key 搜索关键字，可以为空
     * @param type 类别：0（默认）：全部，10：校选修 11：必修 12：实验
     * @return
     * @throws Exception
     */
    @GetMapping("all/{userId}")
    public ResultVO returnAllEvaluationInSort(@PathVariable Long userId,
                                              Integer sort, String key, Integer type)
            throws Exception {
        LOGGER.info("用户" + userId + "将要查询全部评价，关键词key为" + key);
        // TODO: 可以加上校验
        if(!(null == key || "".equals(key))) key = "%" + key + "%"; // 如果key不为空

        List<EvaluationInfo> infos =
                evaluationService.returnAllEvaluationOrder(userId, key, type, sort);
        return ResultUtil.success(infos);
    }

    /**
     * 添加评价
     * @param isRecommended true: 推荐
     * @throws Exception
     */
    @PostMapping("")
    public ResultVO addEvaluation(Long userId, String evaluationContent, String teacherName,
                              String courseName, Integer courseType, Boolean isRecommended)
            throws Exception {
        LOGGER.info("用户" + userId + "将添加一条评价");
        evaluationService.addEvaluation(userId, evaluationContent, teacherName,
                                    courseName, courseType, isRecommended);
        return ResultUtil.success();
    }

    /**
     * 查询某用户创建的所有评价
     * 注：返回对象依然使用EvaluationInfo，所以会有一些冗余字段，默认置空
     * @param userId
     * @return 注意，当查询不到数据时，返回的List并不为空，而是size == 0
     * @throws Exception
     */
    @GetMapping("/{userId}")
    public ResultVO returnUserAllEvaluation(@PathVariable Long userId) throws Exception {
        LOGGER.info("请求查询用户" + userId + "创建的全部评价");
        List<EvaluationInfo> infos = evaluationService.returnUserAllEvaluation(userId);

        return ResultUtil.success(infos);
    }

    /**
     * 返回用户收藏的所有评价
     * @param userId
     * @return
     * @throws Exception
     */
    @GetMapping("/favorite/{userId}")
    public ResultVO returnUserFavorite(@PathVariable Long userId) throws Exception {
        LOGGER.info("用户" + userId + "请求查询收藏评价");
        List<EvaluationInfo> favList = evaluationService.returnUserFavorite(userId);
        return ResultUtil.success(favList);
    }

    /**
     * 通过id删除一条评价
     */
    @DeleteMapping("/{evaluationId}")
    public ResultVO deleteEvaluation(@PathVariable Long evaluationId) throws Exception {
        LOGGER.info("请求删除评价" + evaluationId);
        evaluationService.deleteEvaluationById(evaluationId);
        return ResultUtil.success();
    }

    /**
     * 收藏一条评价
     */
    @PostMapping("/favorite/{evaluationId}/{userId}")
    public ResultVO favoriteEvaluation(@PathVariable Long evaluationId, @PathVariable Long userId)
            throws Exception {
        LOGGER.info("用户" + userId + "请求收藏评价" + evaluationId);
        evaluationService.favoriteEvaluation(evaluationId, userId);
        return ResultUtil.success();
    }

    /**
     * 取消一条收藏的评价
     */
    @DeleteMapping("/favorite/{evaluationId}/{userId}")
    public ResultVO cancelEvaluationFavorite(@PathVariable Long evaluationId,
                                             @PathVariable Long userId) throws Exception {
        LOGGER.info("用户" + userId + "取消收藏评价" + evaluationId);
        evaluationService.cancelFavorite(evaluationId, userId);
        return ResultUtil.success();
    }

}
