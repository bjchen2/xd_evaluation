package com.xd.evaluation.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Json格式化工具，用于打印对象JSON，方便调试
 * Created By Cx On 2018/10/4 21:25
 */
public class JsonUtil {

    public static String toJson(Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        //设置json格式（自动换行），一般仅调试使用，影响性能
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }
}
