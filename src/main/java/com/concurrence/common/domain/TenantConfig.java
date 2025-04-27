package com.concurrence.common.domain;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lombok.Data;

/**
 * @author chenqw
 * @date 2024/3/13 8:46
 */
@Data
@Component
@ConfigurationProperties(prefix = "tenant")
public class TenantConfig {

    private Boolean enable;

    private String admin;

    private List<Config> configs;

    private List<String> ignoreTable;

    private Map<Long, String> tenantMap = new ConcurrentHashMap<>();

    @Data
    public static class Config {
        String id;
        List<String> scope;
    }

}
