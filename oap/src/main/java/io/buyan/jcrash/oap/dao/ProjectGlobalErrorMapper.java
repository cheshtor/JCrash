package io.buyan.jcrash.oap.dao;


import io.buyan.jcrash.oap.entity.ProjectGlobalError;

import java.util.List;

/**
 * 
 *
 * @author Pengyu Gan
 * CreateDate 2022/3/7
 */
public interface ProjectGlobalErrorMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProjectGlobalError record);

    int batchInsert(List<ProjectGlobalError> errors);

    ProjectGlobalError selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProjectGlobalError record);
}