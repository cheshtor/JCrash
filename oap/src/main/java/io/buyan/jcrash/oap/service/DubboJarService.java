package io.buyan.jcrash.oap.service;

import io.buyan.jcrash.oap.entity.DubboJar;
    /**
 * 
 *
 * @author Gan Pengyu
 * CreateDate 2022/2/12
 */
public interface DubboJarService{

    int deleteByPrimaryKey(Long id);

    int insert(DubboJar record);

    int insertSelective(DubboJar record);

    DubboJar selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DubboJar record);

    int updateByPrimaryKey(DubboJar record);

}
