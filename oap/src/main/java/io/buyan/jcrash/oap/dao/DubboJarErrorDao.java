package io.buyan.jcrash.oap.dao;

import io.buyan.jcrash.oap.entity.DubboJarError;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 *
 * @author Gan Pengyu
 * CreateDate 2022/2/12
 */
@Mapper
public interface DubboJarErrorDao {
    int deleteByPrimaryKey(Long id);

    int insert(List<DubboJarError> errors);

    int insertSelective(DubboJarError record);

    DubboJarError selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DubboJarError record);

    int updateByPrimaryKey(DubboJarError record);
}