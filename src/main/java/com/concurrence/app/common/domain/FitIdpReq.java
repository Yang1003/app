package com.concurrence.app.common.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author pengli
 * @Email pengli@fiberhome.com
 * @date 2022/11/9 10:29
 */
@Getter
@Setter
public class FitIdpReq implements Serializable {

    private String field;
    private String txtValue;
    private Boolean value;
    private String operator;
    private String fieldDataType;
    private String andOr;

    public FitIdpReq() {
    }

    public FitIdpReq(String field, String txtValue) {
        this.field = field;
        this.txtValue = txtValue;
        this.operator = "eq";
        this.andOr = "and";
        this.fieldDataType = "String";
    }

    public FitIdpReq(String field, String txtValue, String operator, String fieldDataType) {
        this.field = field;
        this.txtValue = txtValue;
        this.operator = operator;
        this.fieldDataType = fieldDataType;
        this.andOr = "and";
    }

    public FitIdpReq(String field, String txtValue, String operator, String fieldDataType, String andOr) {
        this.field = field;
        this.txtValue = txtValue;
        this.operator = operator;
        this.fieldDataType = fieldDataType;
        this.andOr = andOr;
    }
}
