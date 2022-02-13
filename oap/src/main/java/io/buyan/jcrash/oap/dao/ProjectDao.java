package io.buyan.jcrash.oap.dao;

import io.buyan.jcrash.oap.entity.Project;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 *
 * @author Gan Pengyu
 * CreateDate 2022/2/12
 */
@Mapper
public interface ProjectDao {
    int deleteByPrimaryKey(Long id);

    int insert(Project record);

    int insertSelective(Project record);

    Project selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);
}