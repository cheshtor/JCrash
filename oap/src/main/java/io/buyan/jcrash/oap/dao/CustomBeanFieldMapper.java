package io.buyan.jcrash.oap.dao;


import io.buyan.jcrash.oap.entity.CustomBeanField;

import java.util.List;

/**
 * 
 *
 * @author Pengyu Gan
 * CreateDate 2022/3/7
 */
public interface CustomBeanFieldMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CustomBeanField record);

    int batchInsert(List<CustomBeanField> fields);

    CustomBeanField selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustomBeanField record);
}