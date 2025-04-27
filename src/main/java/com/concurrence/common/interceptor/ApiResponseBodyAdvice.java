/*
 * Project Name: OMP
 *
 * File Name: BizResponseBodyAdvice.java
 *
 * Create Date: 2019-7-18
 *
 * Copyright(c) 2019 CMIM Network Co.,Ltd. All Rights Reserved.
 */
package com.concurrence.common.interceptor;

import com.concurrence.common.exception.BizPageResponse;
import com.concurrence.common.exception.BizResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

/**
 * @author pengli
 * 平台统一返回结构体 通知
 **/
@ControllerAdvice(annotations = RestController.class)
@Slf4j
public class ApiResponseBodyAdvice implements ResponseBodyAdvice {

    @Resource
    private ObjectMapper mapper;

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    /**
     * 在Response返回之前执行的动作
     */
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o == null) {
            if (isJsonResponse(mediaType)) {
                return BizResponse.success();
            } else {
                return null;
            }
        } else {
            // 文件上传下载要做处理，不能封装，直接返回
            if (o instanceof Resource) {
                return o;
            } else if (o instanceof String) {
                try {
                    if (BizResponse.isBizResponseJson((String) o)) {
                        return o;
                    } else {
                        return mapper.writeValueAsString(BizResponse.success(o));
                    }
                } catch (JsonProcessingException e) {
                    log.warn("json serialize error", e);
                    // 因为 API返回值为String，
                    // 所以不会抛异常，此处在业务上 是进不来的...
                    return o;
                }
            } else {
                if (o instanceof BizResponse) {
                    //如果已经封装成BizResponse,直接return
                    return o;
                } else if (o instanceof BizPageResponse) {
                    //如果已经封装成BizPageResponse,直接return
                    return o;
                } else {
                    //封装成BizResponse
                    return BizResponse.success(o);
                }
            }
        }
    }

    private boolean isJsonResponse(MediaType mediaType) {
        return mediaType.equals(MediaType.APPLICATION_JSON) ||
                mediaType.equals(MediaType.APPLICATION_JSON_UTF8);
    }

}
