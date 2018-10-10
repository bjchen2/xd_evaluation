package com.xd.evaluation.utils;

import com.xd.evaluation.enums.ResultEnum;
import com.xd.evaluation.VO.ResultVO;

/**
 * Created By Cx On 2018/10/4 21:25
 */
public class ResultUtil {

    public static ResultVO success(){
        return success(null);
    }

    public static ResultVO success(Object data){
        return new ResultVO(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMsg(),data);
    }

    public static ResultVO error(Integer errorCode,String msg){
        return new ResultVO(errorCode,msg,null);
    }

    public static ResultVO error(){
        return error(ResultEnum.ERROR.getCode(),ResultEnum.ERROR.getMsg());
    }

    public static ResultVO error(String errorMsg){
        return error(ResultEnum.ERROR.getCode(),errorMsg);
    }

}
