package io.buyan.jcrash.oap.dao;


import io.buyan.jcrash.oap.entity.DubboJar;

import java.util.List;

/**
 * 
 *
 * @author Pengyu Gan
 * CreateDate 2022/3/7
 */
public interface DubboJarMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DubboJar record);

    int batchInsert(List<DubboJar> jars);

    DubboJar selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DubboJar record);
}