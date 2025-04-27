package com.concurrence.common.exception;

import com.alibaba.fastjson.JSON;
import com.concurrence.common.constants.HeaderConstants;
import com.concurrence.common.utils.ApiHeaderUtil;

import org.slf4j.MDC;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import static com.concurrence.common.constants.MessageConstants.RESPONSE_FAIL_CODE;
import static com.concurrence.common.constants.MessageConstants.RESPONSE_FAIL_MSG;
import static com.concurrence.common.constants.MessageConstants.RESPONSE_OK_CODE;
import static com.concurrence.common.constants.MessageConstants.RESPONSE_OK_MSG;

@Slf4j
@Data
@AllArgsConstructor
public class BizResponse<T> {
    private String code;
    private String traceId;
    private List<String> msg;
    private T data;
    private String tenantId;

    public BizResponse() {
        msg = new ArrayList<>();
    }

    public BizResponse(String code, T data) {
        msg = new ArrayList<>();
        this.code = code;
        this.data = data;
    }

    /**
     * 默认错误应答体 带错误信息
     */
    public static <K> BizResponse<K> failure() {
        return failure(RESPONSE_FAIL_MSG);
    }

    /**
     * 带错误信息的错误应答体
     */
    public static <K> BizResponse<K> failure(String errorMsg) {
        BizResponse<K> resp = new BizResponse<>();
        resp.tenantId = ApiHeaderUtil.getTenantId();
        resp.code = RESPONSE_FAIL_CODE;
        resp.traceId = MDC.get(HeaderConstants.TRACE_ID_MDC);
        resp.getMsg().add(errorMsg);

        log.error("请求响应：{}", JSON.toJSONString(resp));
        return resp;
    }

    /**
     * 带错误码错误信息的错误应答体
     */
    public static <K> BizResponse<K> failure(String code, String... errorMsg) {
        BizResponse<K> resp = new BizResponse<>();
        resp.tenantId = ApiHeaderUtil.getTenantId();
        resp.code = code;
        resp.traceId = MDC.get(HeaderConstants.TRACE_ID_MDC);
        if (errorMsg.length > 0) {
            for (int i = 0; i < errorMsg.length; i++) {
                resp.getMsg().add(errorMsg[i]);
            }
        }

        log.error("请求响应：{}", JSON.toJSONString(resp));
        return resp;
    }

    /**
     * 带返回值的错误应答体
     */
    public static <K> BizResponse<K> failure(String code, String errorMsg, K t) {
        BizResponse<K> resp = new BizResponse<>();
        resp.tenantId = ApiHeaderUtil.getTenantId();
        resp.code = code;
        resp.traceId = MDC.get(HeaderConstants.TRACE_ID_MDC);
        resp.getMsg().add(errorMsg);
        resp.setData(t);

        log.error("请求响应：{}", JSON.toJSONString(resp));
        return resp;
    }


    /**
     * 默认成功应答体
     */
    public static <K> BizResponse<K> success() {
        return success(null);
    }

    /**
     * 成功应答体  带成功返回值
     */
    public static <K> BizResponse<K> success(List<String> msgs, K t) {
        BizResponse<K> resp = new BizResponse<>();
        resp.tenantId = ApiHeaderUtil.getTenantId();
        resp.code = RESPONSE_OK_CODE;
        resp.getMsg().addAll(msgs);
        resp.data = t;

        log.info("请求响应：{}", JSON.toJSONString(resp));
        log.info("=================================================================================//");
        return resp;
    }

    /**
     * 成功应答体  带成功返回值 带多条Msg
     */
    public static <K> BizResponse<K> success(String msg, K t) {
        BizResponse<K> resp = new BizResponse<>();
        resp.tenantId = ApiHeaderUtil.getTenantId();
        resp.code = RESPONSE_OK_CODE;
        resp.getMsg().add(msg);
        resp.data = t;

        log.info("请求响应：{}", JSON.toJSONString(resp));
        log.info("=================================================================================//");
        return resp;
    }

    /**
     * 成功应答体  带成功返回值 带消息体
     */
    public static <K> BizResponse<K> success(K t) {
        BizResponse<K> resp = new BizResponse<>();
        resp.tenantId = ApiHeaderUtil.getTenantId();
        resp.code = RESPONSE_OK_CODE;
        resp.getMsg().add(RESPONSE_OK_MSG);
        resp.data = t;

        log.info("请求响应：{}", JSON.toJSONString(resp));
        log.info("=================================================================================//");
        return resp;
    }

    /**
     * 判断字符串是否已经是 BizResponse返回格式
     */
    public static boolean isBizResponseJson(String json) {
        return json != null && json.contains("\"code\":")
                && json.contains("\"msg\":")
                && json.contains("\"data\":");
    }
}
