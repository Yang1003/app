package com.concurrence.app.common.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @author chenqw
 * @date 2022/10/25 15:58
 */
@Data
public class BaseResponse implements Serializable {

    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    private Long createUserId;

    private String createUserName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date modifiedDate;

    private Long modifiedUserId;

    private String modifiedUserName;

    public static <T extends BaseResponse> T reset(T response) {
        return reset(response, false);
    }

    public static <T extends BaseResponse> T reset(T response, Boolean resetId) {
        if (resetId) {
            response.setId(null);
        }
        response.setCreateDate(null);
        response.setCreateUserId(null);
        response.setCreateUserName(null);
        response.setModifiedDate(null);
        response.setModifiedUserId(null);
        response.setModifiedUserName(null);
        return response;
    }

}
