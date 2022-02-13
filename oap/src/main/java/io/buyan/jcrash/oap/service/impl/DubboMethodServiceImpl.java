package io.buyan.jcrash.oap.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import io.buyan.jcrash.oap.dao.DubboMethodDao;
import io.buyan.jcrash.oap.entity.DubboMethod;
import io.buyan.jcrash.oap.service.DubboMethodService;
/**
 * 
 *
 * @author Gan Pengyu
 * CreateDate 2022/2/12
 */
@Service
public class DubboMethodServiceImpl implements DubboMethodService{

    @Resource
    private DubboMethodDao dubboMethodDao;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return dubboMethodDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(DubboMethod record) {
        return dubboMethodDao.insert(record);
    }

    @Override
    public int insertSelective(DubboMethod record) {
        return dubboMethodDao.insertSelective(record);
    }

    @Override
    public DubboMethod selectByPrimaryKey(Long id) {
        return dubboMethodDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(DubboMethod record) {
        return dubboMethodDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(DubboMethod record) {
        return dubboMethodDao.updateByPrimaryKey(record);
    }

}
