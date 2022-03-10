package io.buyan.jcrash.oap.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 项目下的 Jar 包
 *
 * @author Pengyu Gan
 * CreateDate 2022/3/10
 */
@Data
public class ProjectJar {

    /**
    * 文件 Hash 值
    */
    private String hashValue;

    /**
    * 关联项目 ID
    */
    private Long projectId;

    /**
    * Jar 文件原名
    */
    private String originalName;

    /**
    * Jar 文件存储地址
    */
    private String url;

    /**
    * 存储时间
    */
    private LocalDateTime createDate;
}