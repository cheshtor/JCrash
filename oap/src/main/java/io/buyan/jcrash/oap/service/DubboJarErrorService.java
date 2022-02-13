package io.buyan.jcrash.oap.service;

import io.buyan.jcrash.oap.entity.DubboJarError;
    /**
 * 
 *
 * @author Gan Pengyu
 * CreateDate 2022/2/12
 */
public interface DubboJarErrorService{


    int deleteByPrimaryKey(Long id);

    int insert(DubboJarError record);

    int insertSelective(DubboJarError record);

    DubboJarError selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DubboJarError record);

    int updateByPrimaryKey(DubboJarError record);

}
