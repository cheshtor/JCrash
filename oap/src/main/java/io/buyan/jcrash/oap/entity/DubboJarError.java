package io.buyan.jcrash.oap.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Jar 包解析异常信息
 *
 * @author Gan Pengyu
 * CreateDate 2022/2/4
 */
@Data
public class DubboJarError implements Serializable {

    /**
     * 异常 ID
     */
    private Long id;

    /**
     * Jar 包 ID
     */
    private Long jarId;

    /**
     * 出现异常的类的全类名
     */
    private String classname;

    /**
     * 异常信息
     */
    private String message;

}
