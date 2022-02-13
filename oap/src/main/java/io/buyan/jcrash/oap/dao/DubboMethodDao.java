package io.buyan.jcrash.oap.dao;

import io.buyan.jcrash.oap.entity.DubboMethod;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 *
 * @author Gan Pengyu
 * CreateDate 2022/2/12
 */
@Mapper
public interface DubboMethodDao {
    int deleteByPrimaryKey(Long id);

    int insert(DubboMethod record);

    int insertSelective(DubboMethod record);

    DubboMethod selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DubboMethod record);

    int updateByPrimaryKey(DubboMethod record);
}