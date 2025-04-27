package com.concurrence.app.common.interceptor.pojo;

import java.io.Serializable;

import lombok.Data;

/**
 * @author pengli
 * @Email pengli@fiberhome.com
 * @date 2022/8/27 11:42
 * 用户信息
 */
@Data
public class User implements Serializable {
    private String token;
    private String id;
    private Long userId;
    private String userName;
    private String name;
    private String email;
    private String mobile;
    private String userCode;
    private String userAccount;
    private String deptId;
    private String deptName;
    private String deptId1;
    private String deptName1;
    private String deptId2;
    private String deptName2;
    private String department;
    private String parentDeptId;
    private String deptPath;
    private String post;
    private String[] roleList;
    private String[] userGroup;

    private CurrentUser currentUser;

}
