package com.concurrence.common.domain;

import java.io.Serializable;

import lombok.Data;

/**
 * @auther pengli
 **/
@Data
public class CustomProtocol implements Serializable {

    private Byte commandType;
    private String ip;
    private String mac;
    private Short contentLength;
    private String content;
    private int softwareCount;

}
