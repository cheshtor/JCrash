package io.buyan.jcrash.oap.dao;


import io.buyan.jcrash.oap.entity.DubboParam;

import java.util.List;

/**
 * 
 *
 * @author Pengyu Gan
 * CreateDate 2022/3/7
 */
public interface DubboParamMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DubboParam record);

    int batchInsert(List<DubboParam> params);

    DubboParam selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DubboParam record);
}