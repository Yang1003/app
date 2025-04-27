package com.concurrence.common.exception;


import com.concurrence.common.constants.BizErrorCode;

import org.springframework.util.CollectionUtils;

import java.util.List;

import lombok.Data;

import static com.concurrence.common.constants.MessageConstants.RESPONSE_FAIL_CODE;

/**
 * @author chenqw
 */
@Data
public class BizException extends RuntimeException {
    private String code;
    private String msg;
    private String data;

    public BizException(BizErrorCode code, String... messages) {
        this.code = code.getCode();
        if (messages != null) {
            StringBuilder sb = new StringBuilder();
            for (String msg : messages) {
                sb.append(msg);
            }
            this.msg = sb.toString();
        } else {
            this.msg = code.getMessage();
        }
    }

    public BizException(BizErrorCode code, List<String> messages) {
        this.code = code.getCode();
        if (!CollectionUtils.isEmpty(messages)) {
            StringBuilder sb = new StringBuilder();
            for (String msg : messages) {
                sb.append(msg);
            }
            this.msg = sb.toString();
        } else {
            this.msg = code.getMessage();
        }
    }

    public BizException(BizErrorCode code) {
        this.code = code.getCode();
        this.msg = code.getMessage();
    }

    public BizException(String msg) {
        this.code = RESPONSE_FAIL_CODE;
        this.msg = msg;
    }

}
