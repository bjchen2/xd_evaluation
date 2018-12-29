package com.xd.evaluation.form;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

/**
 * 反馈参数验证表单
 * Created By Cx On 2018/10/18 17:48
 */
@Data
public class FeedbackForm {

    //反馈标题
    @NotBlank(message = "反馈标题不能为空")
    private String title;

    //反馈内容
    @NotBlank(message = "反馈内容不能为空")
    private String content;

    private MultipartFile file;
}
