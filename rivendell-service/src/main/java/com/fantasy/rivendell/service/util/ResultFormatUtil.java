package com.fantasy.rivendell.service.util;

import com.alibaba.fastjson.JSON;

/**
 * Created by lingyao on 16/7/14.
 */
public class ResultFormatUtil {
    public static String formatResult(Object result) {
        return JSON.toJSONString(result) + "\n";
    }
}
