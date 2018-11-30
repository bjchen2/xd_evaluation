package com.xd.evaluation.controller;


import com.xd.evaluation.VO.ResultVO;
import com.xd.evaluation.dto.CommentInfo;
import com.xd.evaluation.service.CommentService;
import com.xd.evaluation.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 实现对评论的操作，包括添加评论，查询【评论
 * Created By Cx On 2018/10/9 21:44
 */
@RestController
@RequestMapping(value = "/comment")
public class CommentController {

    @Autowired
    private static final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private CommentService commentService;

    @Autowired
    private HttpServletRequest request;

    /**
     * 获取某评价下的所有评论
     * @param evaluationId 评价id
     * @param userId 当前登陆用户id
     */
    @RequestMapping(value = "/{evaluationId}/{userId}", method = RequestMethod.GET)
    public ResultVO returnAllComment(@PathVariable Long evaluationId, @PathVariable Long userId)
        throws Exception {
        LOGGER.info("用户 " + userId + " 请求查询 " + evaluationId + " 号评价所有评论");
        if(null == evaluationId || null == userId) {    // 异常请求
            LOGGER.error(request.getRemoteAddr() + ": 异常请求，id不能为空");
            return ResultUtil.error("id不能为空");
        }

        List<CommentInfo> comments = commentService.returnAllComments(evaluationId, userId);
        return ResultUtil.success(comments);
    }

    /**
     * 添加一条评论
     * @param userId 评论人id
     * @param evaluationId 评论的评价id
     * @param commentContent 评论内容
     */
    @PostMapping("")
    public ResultVO addOneComment(@RequestBody Long userId,@RequestBody Long evaluationId,
                                  @RequestBody String commentContent) throws Exception {
        LOGGER.info("用户 " + userId + " 请求添加一条评论");
        if(null == evaluationId || null == userId) {    // 异常请求
            LOGGER.error(request.getRemoteAddr() + ": 异常请求，id不能为空");
            return ResultUtil.error("id不能为空");
        }

        commentService.addComment(userId, evaluationId, commentContent);
        return ResultUtil.success();
    }
}
