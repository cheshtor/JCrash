package io.buyan.jcrash.oap.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Jar 包
 *
 * @author Gan Pengyu
 * CreateDate 2022/2/4
 */
@Data
public class DubboJar implements Serializable {

    /**
     * Jar 包 ID
     */
    private Long id;

    /**
     * 所属项目 ID
     */
    private Long projectId;

    /**
     * 扫描版本
     */
    private Long scanVersion;

    /**
     * Jar 包名称
     */
    private String name;

}
