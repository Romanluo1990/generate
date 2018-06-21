package com.vip.xpf.common.converter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.util.Date;

/** 日期参数请求转换器 */
@Slf4j
public class DateConverter implements Converter<String, Date> {

  private static final String[] DATA_FORMAT =
      new String[] {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd", "yyyy-MM", "yyyy"};

  @Override
  public Date convert(String time) {
    try {
      return DateUtils.parseDate(time, DateConverter.DATA_FORMAT);
    } catch (ParseException e) {
      DateConverter.log.warn("请求参数parse异常:{}", time);
      return null;
    }
  }
}
