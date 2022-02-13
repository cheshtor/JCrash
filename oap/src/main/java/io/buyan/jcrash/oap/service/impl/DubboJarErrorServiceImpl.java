package io.buyan.jcrash.oap.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import io.buyan.jcrash.oap.dao.DubboJarErrorDao;
import io.buyan.jcrash.oap.entity.DubboJarError;
import io.buyan.jcrash.oap.service.DubboJarErrorService;
/**
 * 
 *
 * @author Gan Pengyu
 * CreateDate 2022/2/12
 */
@Service
public class DubboJarErrorServiceImpl implements DubboJarErrorService{

    @Resource
    private DubboJarErrorDao dubboJarErrorDao;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return dubboJarErrorDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(DubboJarError record) {
//        return dubboJarErrorDao.insert(record);
        return 0;
    }

    @Override
    public int insertSelective(DubboJarError record) {
        return dubboJarErrorDao.insertSelective(record);
    }

    @Override
    public DubboJarError selectByPrimaryKey(Long id) {
        return dubboJarErrorDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(DubboJarError record) {
        return dubboJarErrorDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(DubboJarError record) {
        return dubboJarErrorDao.updateByPrimaryKey(record);
    }

}
