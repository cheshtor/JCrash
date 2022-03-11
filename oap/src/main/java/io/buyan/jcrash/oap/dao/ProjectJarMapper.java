package io.buyan.jcrash.oap.dao;

import io.buyan.jcrash.oap.entity.ProjectJar;

import java.util.List;

/**
 * 
 *
 * @author Pengyu Gan
 * CreateDate 2022/3/10
 */
public interface ProjectJarMapper {

    int deleteByPrimaryKey(String hashValue);

    int insert(ProjectJar record);

    int batchInsert(List<ProjectJar> jars);

    List<ProjectJar> findByHashValue(List<String> hashes);

    List<ProjectJar> findByProjectId(Long projectId);

    ProjectJar selectByPrimaryKey(String hashValue);

    int updateByPrimaryKeySelective(ProjectJar record);
}