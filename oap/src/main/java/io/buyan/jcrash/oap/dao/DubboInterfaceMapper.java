package io.buyan.jcrash.oap.dao;


import io.buyan.jcrash.oap.entity.DubboInterface;

import java.util.List;

/**
 * 
 *
 * @author Pengyu Gan
 * CreateDate 2022/3/7
 */
public interface DubboInterfaceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DubboInterface record);

    int batchInsert(List<DubboInterface> interfaces);

    DubboInterface selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DubboInterface record);
}