package com.concurrence.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author pengli
 * @Email pengli@fiberhome.com
 * @date 2023/3/16 14:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeyValuePair {
    private String key;
    private String value;
}
