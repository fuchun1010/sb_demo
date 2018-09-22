package com.tank.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static com.tank.common.Constants.URL_PREFIX;

/**
 * @author fuchun
 */
@WebFilter(filterName = "requestFilter", urlPatterns = URL_PREFIX + "*")
@Slf4j
public class RequestFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    System.out.println(".....requestFilter......");
    log.warn("....requestFilter.... {}");
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) servletRequest;

    filterChain.doFilter(req, servletResponse);
  }

  @Override
  public void destroy() {

  }
}
