package com.concurrence.app.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.concurrence.app.common.utils.ApiHeaderUtil;
import com.concurrence.app.common.constants.HeaderConstants;
import com.concurrence.app.common.interceptor.pojo.HeaderCookieModel;
import com.concurrence.app.common.interceptor.pojo.RequestWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jodd.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author chenqw
 * @date 2022/9/28 8:26
 */
@Slf4j
@Component
public class WebRequestHandlerInterceptor implements HandlerInterceptor {

    @Resource
    private ObjectMapper mapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 解析header 获取Cookie
        parseHeaderCookie(request);
        // 解析header 读取用户信息
        parseHeaderAuthorization(request);
        // 设置轨迹ID
        setHeadTraceId(request);
        // 打印请求日志
        printRequestLog(request);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ApiHeaderUtil.removeHeaders();
        MDC.remove(HeaderConstants.TRACE_ID_MDC);
    }

    private void parseHeaderCookie(HttpServletRequest request) throws java.io.IOException {
        String cookieStr = request.getHeader(HeaderConstants.COOKIE_HEADER);
        if (!StringUtils.hasText(cookieStr)) {
            return;
        }

        try {
            HeaderCookieModel userModel = mapper.readValue(cookieStr, HeaderCookieModel.class);
            if (userModel != null) {
                ApiHeaderUtil.setCookie(userModel);
            }
        } catch (JsonProcessingException ignore) {
        }
    }

    private void parseHeaderAuthorization(HttpServletRequest request) {
        String authorization = request.getHeader(HeaderConstants.AUTHORIZATION);
        if (StringUtil.isNotEmpty(authorization)) {
            ApiHeaderUtil.setAuthorization(authorization);
        }
    }

    private void setHeadTraceId(HttpServletRequest request) {
        String traceId = request.getHeader(HeaderConstants.TRACE_ID_HEADER);
        if (StringUtil.isEmpty(traceId)) {
            traceId = UUID.randomUUID().toString();
        }
        MDC.put(HeaderConstants.TRACE_ID_MDC, traceId);
    }

    private void printRequestLog(HttpServletRequest request) {
        log.info("//=================================================================================");
        log.info("请求接口：{}", request.getRequestURI());
        log.info("请求类型：{}", request.getMethod());
        String parameter;
        if (HeaderConstants.METHOD_TYPE_POST.equals(request.getMethod())) {
            parameter = new RequestWrapper(request).getBody();
        } else {
            parameter = JSON.toJSONString(request.getParameterMap());
        }
        log.info("请求参数：{}", parameter);
    }

}
