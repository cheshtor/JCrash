package io.buyan.jcrash.oap.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import io.buyan.jcrash.oap.dao.DubboParamDao;
import io.buyan.jcrash.oap.entity.DubboParam;
import io.buyan.jcrash.oap.service.DubboParamService;
/**
 * 
 *
 * @author Gan Pengyu
 * CreateDate 2022/2/12
 */
@Service
public class DubboParamServiceImpl implements DubboParamService{

    @Resource
    private DubboParamDao dubboParamDao;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return dubboParamDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(DubboParam record) {
//        return dubboParamDao.insert(record);
        return 0;
    }

    @Override
    public int insertSelective(DubboParam record) {
        return dubboParamDao.insertSelective(record);
    }

    @Override
    public DubboParam selectByPrimaryKey(Long id) {
        return dubboParamDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(DubboParam record) {
        return dubboParamDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(DubboParam record) {
        return dubboParamDao.updateByPrimaryKey(record);
    }

}
