package com.concurrence.common.interceptor.pojo;

import java.io.Serializable;

import lombok.Data;

/**
 * @author pengli
 * @Email pengli@fiberhome.com
 * @date 2022/8/27 11:42
 * 用户信息
 */
@Data
public class UserInfo implements Serializable {
    private String general_tenant;
    private String name;
    private String language;
    private String id;
    private Long userid;
    private String email;
    private String username;
}
