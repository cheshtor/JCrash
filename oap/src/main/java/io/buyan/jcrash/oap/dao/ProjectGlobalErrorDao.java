package io.buyan.jcrash.oap.dao;

import io.buyan.jcrash.oap.entity.ProjectGlobalError;

import java.util.List;

/**
 * 
 *
 * @author Pengyu Gan
 * CreateDate 2022/2/14
 */
public interface ProjectGlobalErrorDao {
    int deleteByPrimaryKey(Long id);

    int insert(List<ProjectGlobalError> errors);

    int insertSelective(ProjectGlobalError record);

    ProjectGlobalError selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProjectGlobalError record);

    int updateByPrimaryKey(ProjectGlobalError record);
}