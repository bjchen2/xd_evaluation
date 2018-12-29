package com.xd.evaluation.utils;

import com.xd.evaluation.enums.CourseTypeEnum;
import org.junit.Test;

import java.io.File;
import java.nio.ByteBuffer;
import java.util.Date;

/**
 * @Description: 测试工具类
 * @Author: Kripath
 * @Modified By:
 * @Date: Created in 15:47 2018/10/19
 */
public class UtilTests {

    @Test
    public void dateFormatTest() {
        Byte b = 11;
        Integer a = Byte.toUnsignedInt(b);
        System.out.println("value: " + a);
    }

    @Test
    public void testFile(){
        File f = new File("");
        System.out.println(f.canRead());
    }
}
