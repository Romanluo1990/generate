package com.vip.xpf.common.converter;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.util.Date;

/**
 * 日期参数请求转换器
 */
public class DateConverter implements Converter<String, Date> {

    private static final String[] DATA_FORMAT = new String[]{"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd","yyyy-MM","yyyy"};

    @Override
    public Date convert(String s) {
        try {
            return DateUtils.parseDate(s,DATA_FORMAT);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
