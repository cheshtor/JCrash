package io.buyan.jcrash.oap.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 接口方法
 *
 * @author Gan Pengyu
 * CreateDate 2022/2/4
 */
@Data
public class DubboMethod implements Serializable {

    /**
     * 方法 ID
     */
    private Long id;

    /**
     * 所在接口 ID
     */
    private Long interfaceId;

    /**
     * 方法名称
     */
    private String methodName;

    /**
     * json 形式返回值类型描述
     */
    private String returnTypeJson;

    /**
     * 字面返回值
     */
    private String literalReturnType;

    /**
     * 字面方法签名
     */
    private String literalMethod;

}
