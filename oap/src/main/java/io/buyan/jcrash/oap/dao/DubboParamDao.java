package io.buyan.jcrash.oap.dao;

import io.buyan.jcrash.oap.entity.DubboParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 *
 * @author Gan Pengyu
 * CreateDate 2022/2/12
 */
@Mapper
public interface DubboParamDao {
    int deleteByPrimaryKey(Long id);

    int insert(List<DubboParam> params);

    int insertSelective(DubboParam record);

    DubboParam selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DubboParam record);

    int updateByPrimaryKey(DubboParam record);
}