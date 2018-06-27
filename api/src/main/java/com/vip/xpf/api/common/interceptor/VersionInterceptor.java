package com.vip.xpf.api.common.interceptor;

import com.vip.xpf.api.common.manager.VersionManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VersionInterceptor extends HandlerInterceptorAdapter {

  private static final String FIELD_NAME = "version";

  @Override
  public boolean preHandle(
      HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o)
      throws Exception {
    String version = httpServletRequest.getParameter(VersionInterceptor.FIELD_NAME);
    if (StringUtils.isNotEmpty(version)) {
      VersionManager.setVersion(version);
    }
    return true;
  }

  @Override
  public void afterCompletion(
      HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
      throws Exception {
    VersionManager.removeVersion();
  }
}
