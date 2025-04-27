package com.concurrence.common.interceptor.pojo;

import java.io.Serializable;

import lombok.Data;

/**
 * @author pengli
 * @Email pengli@fiberhome.com
 * @date 2022/7/22 15:11
 * <p>
 * cookie数据模型
 */
@Data
public class HeaderCookieModel implements Serializable {

    private String syscode;
    private String GUEST_LANGUAGE_ID;
    private String language;
    private String oauthsys;
    private String SESSION;

}
