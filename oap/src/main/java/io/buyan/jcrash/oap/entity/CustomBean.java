package io.buyan.jcrash.oap.entity;

import lombok.Data;

/**
 * 
 *
 * @author Gan Pengyu
 * CreateDate 2022/2/12
 */
@Data
public class CustomBean {
    /**
    * 自定义类型 ID
    */
    private Long id;

    /**
    * 关联项目 ID
    */
    private Long projectId;

    /**
    * 自定义类型名称
    */
    private String classname;
}