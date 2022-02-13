package io.buyan.jcrash.oap.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import io.buyan.jcrash.oap.entity.DubboJar;
import io.buyan.jcrash.oap.dao.DubboJarDao;
import io.buyan.jcrash.oap.service.DubboJarService;
/**
 * 
 *
 * @author Gan Pengyu
 * CreateDate 2022/2/12
 */
@Service
public class DubboJarServiceImpl implements DubboJarService{

    @Resource
    private DubboJarDao dubboJarDao;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return dubboJarDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(DubboJar record) {
        return dubboJarDao.insert(record);
    }

    @Override
    public int insertSelective(DubboJar record) {
        return dubboJarDao.insertSelective(record);
    }

    @Override
    public DubboJar selectByPrimaryKey(Long id) {
        return dubboJarDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(DubboJar record) {
        return dubboJarDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(DubboJar record) {
        return dubboJarDao.updateByPrimaryKey(record);
    }

}
