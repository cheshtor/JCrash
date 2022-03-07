package io.buyan.jcrash.oap.dao;


import io.buyan.jcrash.oap.entity.CustomBean;

import java.util.List;

/**
 * 
 *
 * @author Pengyu Gan
 * CreateDate 2022/3/7
 */
public interface CustomBeanMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CustomBean record);

    int batchInsert(List<CustomBean> beans);

    CustomBean selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustomBean record);
}