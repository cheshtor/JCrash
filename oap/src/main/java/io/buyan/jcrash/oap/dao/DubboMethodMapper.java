package io.buyan.jcrash.oap.dao;


import io.buyan.jcrash.oap.entity.DubboMethod;

import java.util.List;

/**
 * 
 *
 * @author Pengyu Gan
 * CreateDate 2022/3/7
 */
public interface DubboMethodMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DubboMethod record);

    int batchInsert(List<DubboMethod> methods);

    DubboMethod selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DubboMethod record);
}