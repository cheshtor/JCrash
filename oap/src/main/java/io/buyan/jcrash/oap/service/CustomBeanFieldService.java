package io.buyan.jcrash.oap.service;

import io.buyan.jcrash.oap.entity.CustomBeanField;
    /**
 * 
 *
 * @author Gan Pengyu
 * CreateDate 2022/2/12
 */
public interface CustomBeanFieldService{


    int deleteByPrimaryKey(Long id);

    int insert(CustomBeanField record);

    int insertSelective(CustomBeanField record);

    CustomBeanField selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustomBeanField record);

    int updateByPrimaryKey(CustomBeanField record);

}
