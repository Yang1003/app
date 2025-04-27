package com.concurrence.app.common.interceptor.pojo;

import java.io.Serializable;

import lombok.Data;

/**
 * @author pengli
 * @Email pengli@fiberhome.com
 * @date 2022/8/27 11:29
 */
@Data
public class UserInfoGroup implements Serializable {
    private String access_token;
    private CurrentUser currentUser;
    private Extauth extauth;
    private String tenantId;
    private String[] usergroup;
    private String language;
    private String user;
    private UserInfo userinfo;
    private String tenant;
    private String[] authorities;

}
