package io.buyan.jcrash.oap.entity;

import lombok.Data;

/**
 * 
 *
 * @author Pengyu Gan
 * CreateDate 2022/2/14
 */
@Data
public class ProjectGlobalError {
    /**
    * 全局异常 ID
    */
    private Long id;

    /**
    * 关联项目 ID
    */
    private Long projectId;

    /**
    * 异常项目名称
    */
    private String itemName;

    /**
    * 异常信息
    */
    private String message;
}