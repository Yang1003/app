package com.concurrence.app.common.constants;

import lombok.experimental.UtilityClass;

/**
 * @author chenqw
 **/
@UtilityClass
public class MessageConstants {

    public static final String RESPONSE_OK_CODE = "200";
    public static final String RESPONSE_OK_MSG = "执行成功";

    public static final String RESPONSE_FAIL_CODE = "500";
    public static final String RESPONSE_FAIL_MSG = "执行失败";

    public static final String RESPONSE_COMMON_PARAMETER_ERROR_CODE = "401";
    public static final String RESPONSE_COMMON_PARAMETER_ERROR_MSG = "请求参数错误";

    public static final String COMMON_FEIGN_FAIL_CODE = "000_0000_0500";
    public static final String COMMON_FEIGN_FAIL_MSG = "feign调用,未知错误";

    public static final String RESPONSE_NO_AUTHORIZE_CODE = "403";
    public static final String RESPONSE_NO_AUTHORIZE_MSG = "无权限访问";

    public static final String RESPONSE_DATA_AUTHORIZE_CODE = "402";
    public static final String RESPONSE_DATA_AUTHORIZE_MSG = "无数据权限访问";

    public static final String COMMON_STRING_TO_TIME_CODE = "503";
    public static final String COMMON_STRING_TO_TIME_MSG = "时间转换失败";


}
