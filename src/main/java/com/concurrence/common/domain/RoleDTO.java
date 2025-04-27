package com.concurrence.common.domain;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chenqw
 * @date 2023/9/4 11:32
 */
@NoArgsConstructor
@Data
public class RoleDTO implements Serializable {
    private Long id;
    private Object modelscope;
    private String code;
    private String description;
    private String title;
    private Object applicationcode;
}
