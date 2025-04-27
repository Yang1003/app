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


/**
 * @author pengli
 **/
@Slf4j
@Data
@AllArgsConstructor
public class BizPageResponse<T> {
    private String code;
    private String traceId;
    private List<String> msg;
    private List<T> data;
    private Long total;
    private String tenantId;


    public BizPageResponse() {
        msg = new ArrayList<>();
    }

    /**
     * 默认错误应答体 带错误信息
     */
    public static <K> BizPageResponse<K> failure() {
        return failure(RESPONSE_FAIL_MSG);
    }

    /**
     * 带错误信息的错误应答体
     */
    public static <K> BizPageResponse<K> failure(String errorMsg) {
        BizPageResponse<K> resp = new BizPageResponse<>();
        resp.tenantId = ApiHeaderUtil.getTenantId();
        resp.code = RESPONSE_FAIL_CODE;
        resp.traceId = MDC.get(HeaderConstants.TRACE_ID_MDC);
        resp.getMsg().add(errorMsg);

        log.error("请求响应：{}", JSON.toJSONString(resp));
        log.error("=================================================================================//");
        return resp;
    }

    /**
     * 带错误码错误信息的错误应答体
     */
    public static <K> BizPageResponse<K> failure(String code, String... errorMsg) {
        BizPageResponse<K> resp = new BizPageResponse<>();
        resp.tenantId = ApiHeaderUtil.getTenantId();
        resp.code = code;
        resp.traceId = MDC.get(HeaderConstants.TRACE_ID_MDC);
        if (errorMsg.length > 0) {
            for (String s : errorMsg) {
                resp.getMsg().add(s);
            }
        }
        log.error("请求响应：{}", JSON.toJSONString(resp));
        log.error("=================================================================================//");
        return resp;
    }

    public static <K> BizPageResponse<K> success() {
        return success(new ArrayList<>(), 0L);
    }

    /**
     * 成功应答体  带成功返回值 带消息体
     */
    public static <K> BizPageResponse<K> success(List<K> t, Long total) {
        BizPageResponse<K> resp = new BizPageResponse<>();
        resp.tenantId = ApiHeaderUtil.getTenantId();
        resp.code = RESPONSE_OK_CODE;
        resp.getMsg().add(RESPONSE_OK_MSG);
        resp.data = t;
        resp.total = total;

        log.info("请求响应：{}", JSON.toJSONString(resp));
        log.info("=================================================================================//");
        return resp;
    }
}
