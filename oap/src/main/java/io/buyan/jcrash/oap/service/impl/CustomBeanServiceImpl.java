package io.buyan.jcrash.oap.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import io.buyan.jcrash.oap.entity.CustomBean;
import io.buyan.jcrash.oap.dao.CustomBeanDao;
import io.buyan.jcrash.oap.service.CustomBeanService;
/**
 * 
 *
 * @author Gan Pengyu
 * CreateDate 2022/2/12
 */
@Service
public class CustomBeanServiceImpl implements CustomBeanService{

    @Resource
    private CustomBeanDao customBeanDao;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return customBeanDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CustomBean record) {
        return customBeanDao.insert(record);
    }

    @Override
    public int insertSelective(CustomBean record) {
        return customBeanDao.insertSelective(record);
    }

    @Override
    public CustomBean selectByPrimaryKey(Long id) {
        return customBeanDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CustomBean record) {
        return customBeanDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CustomBean record) {
        return customBeanDao.updateByPrimaryKey(record);
    }

}
