package com.xd.evaluation.handler;

import com.xd.evaluation.VO.ResultVO;
import com.xd.evaluation.exception.EvaluationException;
import com.xd.evaluation.utils.ResultUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常捕获类
 * Created By Cx On 2018/10/4 21:26
 */
@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(EvaluationException.class)
    @ResponseBody
    public ResultVO handleEvaluationException(EvaluationException e){
        return ResultUtil.error(e.getCode(),e.getMessage());
    }

}
