package org.jeecg.common.util;

import io.netty.util.internal.StringUtil;

/**
 * @ClassName StrUtil
 * @Description 字符串工具类
 * @Author lufer
 * @Date 2021/6/20 11:08
 * @Version 1.0
 */
public class StrUtil {
    /**
     * 判断一个字符串是否为空，如果为空则返回text;如果不为空则返回"";
     * @param param 字符串
     * @param text 为空是返回的说明
     * @return
     */
    public static String getNullStringText(String param,String text){
        if(StringUtil.isNullOrEmpty(param)){
            return text;
        }
        return "";
    }

    /**
     * 判断一个数是否为0，如果为0则返回text;如果不为空则返回"";
     * @param param
     * @param text
     * @return
     */
    public static String getNullIntegerText(Integer param,String text){
        if(param == 0){
            return text;
        }
        return "";
    }
}
