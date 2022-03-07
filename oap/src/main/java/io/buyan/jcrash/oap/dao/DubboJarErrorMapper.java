package io.buyan.jcrash.oap.dao;


import io.buyan.jcrash.oap.entity.DubboJarError;

import java.util.List;

/**
 * 
 *
 * @author Pengyu Gan
 * CreateDate 2022/3/7
 */
public interface DubboJarErrorMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DubboJarError record);

    int batchInsert(List<DubboJarError> errors);

    DubboJarError selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DubboJarError record);
}