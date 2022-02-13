package io.buyan.jcrash.oap.service;

import io.buyan.jcrash.oap.entity.DubboParam;
    /**
 * 
 *
 * @author Gan Pengyu
 * CreateDate 2022/2/12
 */
public interface DubboParamService{


    int deleteByPrimaryKey(Long id);

    int insert(DubboParam record);

    int insertSelective(DubboParam record);

    DubboParam selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DubboParam record);

    int updateByPrimaryKey(DubboParam record);

}
