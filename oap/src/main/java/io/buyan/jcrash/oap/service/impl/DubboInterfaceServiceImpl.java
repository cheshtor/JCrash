package io.buyan.jcrash.oap.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import io.buyan.jcrash.oap.dao.DubboInterfaceDao;
import io.buyan.jcrash.oap.entity.DubboInterface;
import io.buyan.jcrash.oap.service.DubboInterfaceService;
/**
 * 
 *
 * @author Gan Pengyu
 * CreateDate 2022/2/12
 */
@Service
public class DubboInterfaceServiceImpl implements DubboInterfaceService{

    @Resource
    private DubboInterfaceDao dubboInterfaceDao;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return dubboInterfaceDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(DubboInterface record) {
        return dubboInterfaceDao.insert(record);
    }

    @Override
    public int insertSelective(DubboInterface record) {
        return dubboInterfaceDao.insertSelective(record);
    }

    @Override
    public DubboInterface selectByPrimaryKey(Long id) {
        return dubboInterfaceDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(DubboInterface record) {
        return dubboInterfaceDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(DubboInterface record) {
        return dubboInterfaceDao.updateByPrimaryKey(record);
    }

}
