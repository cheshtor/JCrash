package io.buyan.jcrash.oap.dao;

import io.buyan.jcrash.oap.entity.CustomBeanField;

import java.util.List;

/**
 * 
 *
 * @author Gan Pengyu
 * CreateDate 2022/2/12
 */
public interface CustomBeanFieldDao {
    int deleteByPrimaryKey(Long id);

    int insert(List<CustomBeanField> fields);

    int insertSelective(CustomBeanField record);

    CustomBeanField selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustomBeanField record);

    int updateByPrimaryKey(CustomBeanField record);
}