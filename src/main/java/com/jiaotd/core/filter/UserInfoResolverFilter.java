package com.jiaotd.core.filter;


import com.alibaba.fastjson.JSON;
import com.jiaotd.core.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLDecoder;

/**
 * 描述：TODO
 *
 * @author jiaotd
 * Date:     2020/3/22
 */
@Slf4j
@Configuration
@WebFilter(filterName = "userInfoResolverFilter", urlPatterns = "/*")
public class UserInfoResolverFilter implements Filter {
    //@WebFilter 用于将一个类声明为过滤器，该注解将会在部署时被容器处理，容器将根据具体的属性配置将相应的类部署为过滤器

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("UserInfoResolverFilter init().....");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String json = ((HttpServletRequest) servletRequest).getHeader("user");
        log.info("请求头中的User为：{}", json);
        User user = JSON.parseObject(URLDecoder.decode(json, "utf-8"), User.class);
        servletRequest.setAttribute("user", user);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.info("UserInfoResolverFilter destroy().....");
    }
}
