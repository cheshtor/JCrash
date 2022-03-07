package io.buyan.jcrash.oap.dao;


import io.buyan.jcrash.oap.entity.Project;

/**
 * 
 *
 * @author Pengyu Gan
 * CreateDate 2022/3/7
 */
public interface ProjectMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Project record);

    Project selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Project record);
}