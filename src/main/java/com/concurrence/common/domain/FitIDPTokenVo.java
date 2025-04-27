package com.concurrence.common.domain;

import lombok.Data;

/**
 * @author pengli
 * @Email pengli@fiberhome.com
 * @date 2022/7/28 14:23
 */
@Data
public class FitIDPTokenVo {

    //"fd294e09-cde3-41a2-8cf6-fe3331e4b9ec",
    private String access_token;
    //"bearer",
    private String token_type;
    //"bdf2b443-cd3b-4bde-b227-526a87948cd6",
    private String refresh_token;
    //14203,
    private Integer expires_in;
    //"webclient"
    private String scope;

}
