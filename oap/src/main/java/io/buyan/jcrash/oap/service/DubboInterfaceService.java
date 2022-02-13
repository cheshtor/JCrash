package io.buyan.jcrash.oap.service;

import io.buyan.jcrash.oap.entity.DubboInterface;
    /**
 * 
 *
 * @author Gan Pengyu
 * CreateDate 2022/2/12
 */
public interface DubboInterfaceService{


    int deleteByPrimaryKey(Long id);

    int insert(DubboInterface record);

    int insertSelective(DubboInterface record);

    DubboInterface selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DubboInterface record);

    int updateByPrimaryKey(DubboInterface record);

}
