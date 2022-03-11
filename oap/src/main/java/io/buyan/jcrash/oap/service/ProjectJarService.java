package io.buyan.jcrash.oap.service;

import io.buyan.jcrash.oap.entity.ProjectJar;

import java.util.List;

/**
 * 项目 Jar 包管理服务
 *
 * @author Pengyu Gan
 * CreateDate 2022/3/10
 */
public interface ProjectJarService {

    /**
     * 查询指定 Hash 值对应的文件
     * @param hashValues 文件 Hash 值
     * @return 匹配文件
     */
    List<ProjectJar> findJarsByHashValue(List<String> hashValues);

    /**
     * 保存 Jar 信息
     * @param jars {@link ProjectJar} Jar 信息
     * @return true 成功 false 失败
     */
    boolean saveJarInfos(List<ProjectJar> jars);

    List<ProjectJar> findJarsByProjectId(Long projectId);

}
