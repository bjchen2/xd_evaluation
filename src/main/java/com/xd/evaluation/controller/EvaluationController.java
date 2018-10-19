package com.xd.evaluation.controller;

import com.xd.evaluation.VO.ResultVO;
import com.xd.evaluation.service.CommentService;
import com.xd.evaluation.service.EvaluationService;
import com.xd.evaluation.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
