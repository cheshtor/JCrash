package io.buyan.jcrash.oap.dao;

import io.buyan.jcrash.oap.entity.DubboInterface;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 *
 * @author Gan Pengyu
 * CreateDate 2022/2/12
 */
@Mapper
public interface DubboInterfaceDao {
    int deleteByPrimaryKey(Long id);

    int insert(DubboInterface record);

    int insertSelective(DubboInterface record);

    DubboInterface selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DubboInterface record);

    int updateByPrimaryKey(DubboInterface record);
}