package io.buyan.jcrash.oap.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import io.buyan.jcrash.oap.dao.CustomBeanFieldDao;
import io.buyan.jcrash.oap.entity.CustomBeanField;
import io.buyan.jcrash.oap.service.CustomBeanFieldService;
/**
 * 
 *
 * @author Gan Pengyu
 * CreateDate 2022/2/12
 */
@Service
public class CustomBeanFieldServiceImpl implements CustomBeanFieldService{

    @Resource
    private CustomBeanFieldDao customBeanFieldDao;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return customBeanFieldDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CustomBeanField record) {
//        return customBeanFieldDao.insert(record);
        return 0;
    }

    @Override
    public int insertSelective(CustomBeanField record) {
        return customBeanFieldDao.insertSelective(record);
    }

    @Override
    public CustomBeanField selectByPrimaryKey(Long id) {
        return customBeanFieldDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CustomBeanField record) {
        return customBeanFieldDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CustomBeanField record) {
        return customBeanFieldDao.updateByPrimaryKey(record);
    }

}
