package io.buyan.jcrash.oap.service;

import io.buyan.jcrash.oap.entity.DubboMethod;
    /**
 * 
 *
 * @author Gan Pengyu
 * CreateDate 2022/2/12
 */
public interface DubboMethodService{


    int deleteByPrimaryKey(Long id);

    int insert(DubboMethod record);

    int insertSelective(DubboMethod record);

    DubboMethod selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DubboMethod record);

    int updateByPrimaryKey(DubboMethod record);

}
