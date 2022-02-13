package io.buyan.jcrash.oap.entity;

import lombok.Data;

/**
 * 项目
 *
 * @author Gan Pengyu
 * CreateDate 2022/2/12
 */
@Data
public class Project {
    /**
    * 项目 ID
    */
    private Long id;

    /**
    * 项目名称
    */
    private String name;
}