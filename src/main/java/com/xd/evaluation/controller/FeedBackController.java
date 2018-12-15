package com.xd.evaluation.controller;

import com.xd.evaluation.VO.ResultVO;
import com.xd.evaluation.domain.Feedback;
import com.xd.evaluation.enums.LikeTypeEnum;
import com.xd.evaluation.form.FeedbackForm;
import com.xd.evaluation.service.FeedbackService;
import com.xd.evaluation.utils.EnumUtil;
import com.xd.evaluation.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created By Cx On 2018/10/18 17:47
 */
@RestController
@RequestMapping("/feedback")
@Slf4j
public class FeedBackController {

    @Autowired
    FeedbackService feedbackService;

    //创建反馈
    @PostMapping("/{userId}")
    public ResultVO create(@PathVariable Long userId, @Valid @RequestBody FeedbackForm feedbackForm, BindingResult bindingResult){
        //验证反馈参数是否正确
        if (bindingResult.hasErrors()){
            log.error("【添加反馈】参数不正确,feedBackForm={}",feedbackForm);
            return ResultUtil.error(bindingResult.getFieldError()==null?"参数不正确":bindingResult.getFieldError().getDefaultMessage());
        }
        Feedback feedback = new Feedback();
        BeanUtils.copyProperties(feedbackForm,feedback);
        feedback.setUserId(userId);
        return ResultUtil.success(feedbackService.create(feedback));
    }
}
