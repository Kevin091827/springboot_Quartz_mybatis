package com.kevin.demo.util;

import org.springframework.util.ObjectUtils;

/**
 * @Auther: Kevin
 * @Date:
 * @ClassName:IsEmptyUtils
 * @Description: TODO
 */
public class ArgsUtils {

    /**
     * 字符串拼接
     * @param s2
     * @return
     */
    public static String appendString(String s1,String s2){
        StringBuilder stringBuilder = new StringBuilder();
        String result = stringBuilder
                .append(s1)
                .append(s2)
                .toString();
        return result;
    }
}
