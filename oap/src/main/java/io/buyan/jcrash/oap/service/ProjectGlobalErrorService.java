package io.buyan.jcrash.oap.service;

import io.buyan.jcrash.oap.entity.ProjectGlobalError;
    /**
 * 
 *
 * @author Pengyu Gan
 * CreateDate 2022/2/14
 */
public interface ProjectGlobalErrorService{


    int deleteByPrimaryKey(Long id);

    int insert(ProjectGlobalError record);

    int insertSelective(ProjectGlobalError record);

    ProjectGlobalError selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProjectGlobalError record);

    int updateByPrimaryKey(ProjectGlobalError record);

}
