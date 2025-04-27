package com.concurrence.common.constants;

import lombok.experimental.UtilityClass;

/**
 * @author pengli
 */
@UtilityClass
public class HeaderConstants {

    public static final String TRACE_ID_HEADER = "TraceId_Header";
    public static final String TRACE_ID_MDC = "TraceId";
    public static final String COOKIE_HEADER = "Cookie";
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";
    public static final String LOGIN_AUTH_SWITCH = "login.auth.switch";

    public static final String TOKEN_URL = "token.url";
    public static final String BASIC_USER = "token.basic.client.username";
    public static final String BASIC_PASSWORD = "token.basic.client.password";

    public static final String AUTH_USER_URL = "auth.url.user";

    public static final String DATA = "data";
    public static final String MSG = "msg";
    public static final String CODE = "code";
    public static final String PAGE = "page";
    public static final String TOTAL = "total";
    public static final String MEDIA_TYPE_JSON = "application/json;charset=UTF-8";
    public static final String METHOD_TYPE_POST = "POST";
}
