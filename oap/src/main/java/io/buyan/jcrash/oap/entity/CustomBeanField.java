package io.buyan.jcrash.oap.entity;

import lombok.Data;

/**
 * 
 *
 * @author Gan Pengyu
 * CreateDate 2022/2/12
 */
@Data
public class CustomBeanField {
    /**
    * 自定义类型字段 ID
    */
    private Long id;

    /**
    * 关联自定义类型 ID
    */
    private Long beanId;

    /**
    * 字段名称
    */
    private String fieldName;

    /**
    * 字段类型
    */
    private String fieldType;
}