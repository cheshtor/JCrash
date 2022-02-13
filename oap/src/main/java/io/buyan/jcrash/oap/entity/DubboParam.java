package io.buyan.jcrash.oap.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 方法参数
 *
 * @author Gan Pengyu
 * CreateDate 2022/2/5
 */
@Data
public class DubboParam implements Serializable {

    /**
     * 参数 ID
     */
    private Long id;

    /**
     * 所在方法 ID
     */
    private Long methodId;

    /**
     * 参数名称
     */
    private String name;

    /**
     * json 形式参数类型描述
     */
    private String paramTypeJson;

    /**
     * 字面参数
     */
    private String literalParam;

}
