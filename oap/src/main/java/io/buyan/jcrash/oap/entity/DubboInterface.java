package io.buyan.jcrash.oap.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Jar 包接口
 *
 * @author Gan Pengyu
 * CreateDate 2022/2/4
 */
@Data
public class DubboInterface implements Serializable {

    /**
     * 接口 ID
     */
    private Long id;

    /**
     * Jar 包 ID
     */
    private Long jarId;

    /**
     * 接口全类名
     */
    private String classname;

}
