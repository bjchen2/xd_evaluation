package com.xd.evaluation.exception;

import com.xd.evaluation.enums.ResultEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created By Cx On 2018/10/17 17:49
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class EvaluationException extends RuntimeException{
    //错误码
    private Integer code;

    public EvaluationException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public EvaluationException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public EvaluationException(String message) {
        super(message);
        this.code = ResultEnum.ERROR.getCode();
    }
}
