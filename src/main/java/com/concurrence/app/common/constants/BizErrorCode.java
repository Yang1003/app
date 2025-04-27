package com.concurrence.app.common.constants;

import java.io.Serializable;

/**
 * @author chenqw
 */
public enum BizErrorCode implements Serializable {

    /**执行成功*/
    SUCCESS("200", "执行成功"),

    USER_NOT_EXIST("401", "用户不存在"),
    LOGIN_INVALID("402", "登录状态已失效"),
    NO_AUTHORIZE("403", "无访问权限"),

    FAILURE("500", "执行失败"),
    REMOTE_CALL_FAILURE("501", "远程调用失败"),
    SYSTEM_EXCEPTION("502", "系统异常"),
    UNKNOWN_EXCEPTION("503", "未知异常"),
    DATA_EXCEPTION("504", "数据异常"),
    PARAM_ERROR("505", "请求参数错误"),
    DATA_FORMAT_ERROR("506", "数据格式错误"),
    OPERATE_ERROR("507", "操作错误"),
    CONFIG_EXCEPTION("508", "系统配置异常"),

    NESTED_ERROR("510", "致命错误"),
    MAJOR_EXCEPTION("511", "重要错误"),

    ERROR_MESSAGE_CLIENT_UNCONNECTED("5004", "客户端未连接"),
    ERROR_MESSAGE_MSG_SEND_FAILURE("5005", "消息发送失败"),
    ERROR_MESSAGE_NO_CLIENT_CONNECTED("5006", "没有客户端连接服务器");

    private final String code;
    private final String message;

    BizErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }


    public static BizErrorCode getStatusCode(String status) {
        for (BizErrorCode unit : BizErrorCode.values()) {
            if (unit.getCode().equals(status)) {
                return unit;
            }
        }
        return null;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "{code:'" + code + '\'' +
                ", message:'" + message + '\'' +
                '}';
    }

}
