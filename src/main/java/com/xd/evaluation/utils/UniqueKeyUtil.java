package com.xd.evaluation.utils;

import java.util.Random;

/**
 * 生成唯一数字串
 * Created By Cx On 2018/10/18 17:48
 */
public class UniqueKeyUtil {
    /**
     * 在一毫秒内产生冲突的可能性是 1/10000
     * 如果需要降低冲突可能性，将生成随机数的范围变大更改即可
     * synchronized关键字，防止多线程冲突
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        //生成一个四位的随机数
        String number = String.valueOf(random.nextInt(9000) + 1000);
        return System.currentTimeMillis() + number;
    }

}
