package io.buyan.jcrash.oap.dao;

import io.buyan.jcrash.oap.entity.CustomBean;

/**
 * 
 *
 * @author Gan Pengyu
 * CreateDate 2022/2/12
 */
public interface CustomBeanDao {
    int deleteByPrimaryKey(Long id);

    int insert(CustomBean record);

    int insertSelective(CustomBean record);

    CustomBean selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustomBean record);

    int updateByPrimaryKey(CustomBean record);
}