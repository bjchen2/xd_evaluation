package com.xd.evaluation.controller;

import com.xd.evaluation.VO.ResultVO;
import com.xd.evaluation.domain.User;
import com.xd.evaluation.dto.EvaluationInfo;
import com.xd.evaluation.pojo.EvaAddPojo;
import com.xd.evaluation.pojo.EvaLikePojo;
import com.xd.evaluation.service.CommentService;
import com.xd.evaluation.service.EvaluationService;
import com.xd.evaluation.service.UserService;
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
    private UserService userService;

    @Autowired
    private EvaluationService evaluationService;

    @Autowired
    private CommentService commentService;

    /**
     * 给 评论/评价 点赞
     * @throws Exception
     */
    @PostMapping("/like")
    public ResultVO likeEvaluation(@RequestBody EvaLikePojo evaLike) throws Exception {
        Integer likeType = evaLike.getLikeType();

        if(10 == likeType) {    // 进行评价的点赞操作
            evaluationService.likeEvaluation(
                    evaLike.getUserId(), evaLike.getObjId(), evaLike.getIsLike());

            LOGGER.info("[点赞]用户{}对评价{}进行点赞/反对操作",
                    evaLike.getUserId(), evaLike.getObjId());
        } else if(11 == likeType) {
            commentService.likeComment(
                    evaLike.getUserId(), evaLike.getObjId(), evaLike.getIsLike());

            LOGGER.info("[点赞]用户{}对评论{}进行点赞/反对操作",
                    evaLike.getUserId(), evaLike.getObjId());
        } else {    // 异常值
            LOGGER.error("[点赞]likeType值不能为{}", likeType);
            return ResultUtil.error("likeType非法值");
        }
        return ResultUtil.success();
    }

    /**
     * 取消用户对 评价/评论 的点赞/反对 操作
     * @return
     * @throws Exception
     */
    @DeleteMapping("/like")
    public ResultVO cancelLike(@RequestBody EvaLikePojo evaUnlike) throws Exception {
        Integer likeType = evaUnlike.getLikeType();
        if(10 == likeType) {    // 取消评价的点赞
            evaluationService.cancelLikeEvaluation(
                    evaUnlike.getUserId(), evaUnlike.getObjId(), evaUnlike.getIsLike());

            LOGGER.info("[取消点赞]用户{}对评价{}取消点赞",
                    evaUnlike.getUserId(), evaUnlike.getObjId());
        } else if(11 == likeType) { // 取消评论的点赞
            commentService.cancelLikeComment(
                    evaUnlike.getUserId(), evaUnlike.getObjId(), evaUnlike.getIsLike());

            LOGGER.info("[取消点赞]用户{}对评论{}取消点赞",
                    evaUnlike.getUserId(), evaUnlike.getObjId());
        } else {    // 异常值
            LOGGER.error("[点赞]likeType值不能为{}", likeType);
            return ResultUtil.error("likeType非法值");
        }
        return ResultUtil.success();
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
    @GetMapping("/all/{userId}")
    public ResultVO returnAllEvaluationInSort(@PathVariable Long userId,
                                              @RequestParam(defaultValue = "0") Integer sort,
                                              @RequestParam(defaultValue = "") String key,
                                              @RequestParam(defaultValue = "0") Integer type)
            throws Exception {
        LOGGER.info("用户" + userId + "将要查询全部评价，关键词key为" + key);
        // TODO: 可以加上校验
        // 如果key不为空
        if(!(null == key || "".equals(key))) {key = "%" + key + "%";}

        List<EvaluationInfo> infos =
                evaluationService.returnAllEvaluationOrder(userId, key, type, sort);

        /* 给infos添加userName属性 */
        evaluationService.putUsernameToEvaluationInfoList(infos);

        return ResultUtil.success(infos);
    }

    /**
     * 添加评价
     */
    @PostMapping("")
    public ResultVO addEvaluation(@RequestBody EvaAddPojo evaAdd) throws Exception {
        if(evaAdd == null || evaAdd.getUserId() == null) {
            LOGGER.error("[添加评论]不合法的空对象");
            return ResultUtil.error("请求参数为空或请求用户id为空");
        }
        evaluationService.addEvaluation(evaAdd.getUserId(),
                                        evaAdd.getEvaluationContent(),
                                        evaAdd.getTeacherName(),
                                        evaAdd.getCourseName(),
                                        evaAdd.getCourseType(),
                                        evaAdd.getIsRecommended());

        LOGGER.info("[添加评论]用户{}添加评论完毕", evaAdd.getUserId());
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
