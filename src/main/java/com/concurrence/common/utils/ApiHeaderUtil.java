package com.concurrence.common.utils;

import com.concurrence.common.interceptor.pojo.HeaderCookieModel;

import lombok.experimental.UtilityClass;

/**
 * @author pengli
 */
@UtilityClass
public class ApiHeaderUtil {

    private static final ThreadLocal<HeaderCookieModel> COOKIE_HEADER = new ThreadLocal<>();
    private static final ThreadLocal<String> AUTHORIZATION_HEADER = new ThreadLocal<>();
    private static final ThreadLocal<String> TENANT_ID = new ThreadLocal<>();
    private static final ThreadLocal<Boolean> TENANT_IGNORE = new ThreadLocal<>();

    public static String getAuthorization() {
        return AUTHORIZATION_HEADER.get();
    }

    public static void setAuthorization(String authorization) {
        AUTHORIZATION_HEADER.set(authorization);
    }

    public static String getTenantId() {
        return TENANT_ID.get();
    }

    public static void setTenantId(String tenantId) {
        TENANT_ID.set(tenantId);
        TENANT_IGNORE.set(false);
    }

    public static Boolean getTenantIgnore() {
        return TENANT_IGNORE.get();
    }

    public static void setTenantIgnore(Boolean ignore) {
        TENANT_IGNORE.set(ignore);
    }

    public static HeaderCookieModel getCookie() {
        return COOKIE_HEADER.get();
    }

    public static void setCookie(HeaderCookieModel headerModel) {
        COOKIE_HEADER.set(headerModel);
    }

    public static void removeHeaders() {
        AUTHORIZATION_HEADER.remove();
        COOKIE_HEADER.remove();
        TENANT_ID.remove();
        TENANT_IGNORE.remove();
    }

}
