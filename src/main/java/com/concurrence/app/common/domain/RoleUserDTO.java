package com.concurrence.app.common.domain;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chenqw
 * @date 2023/9/4 11:40
 */
@NoArgsConstructor
@Data
public class RoleUserDTO implements Serializable {
    private Object modelscope;
    private String modifiedUserName;
    private String createUserId;
    private String code;
    private Object userstatus;
    private String createUserName;
    private String language;
    private Integer authtype;
    private Object englishname;
    private Object password;
    private String confirmablity;
    private Object nickname;
    private Object identitycard;
    private String model;
    private String passworddate;
    private String id;
    private String email;
    private String tenant;
    private Object createDate;
    private String generalTenant;
    private String openid;
    private String mobile;
    private String usertype;
    private Object passwordsalt;
    private Object extemail;
    private Object createBy;
    private String modifiedUserId;
    private Object userlevel;
    private String service;
    private String modifiedDate;
    private String name;
    private Object oauthuser;
    private String username;
}
