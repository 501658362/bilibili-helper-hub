package io.cruii.bilibili.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author cruii
 * Created on 2021/6/9
 */
@Data
public class ContainerCardVO implements Serializable {
    private static final long serialVersionUID = 2174825591123340862L;

    private String containerName;

    private String dedeuserid;

    private String username;

    private String avatar;

    private String coins;

    private Integer level;

    private Integer currentExp;

    private Integer diffExp;

    private Boolean isVip;

    private String key;

    private Boolean isLogin;
}
