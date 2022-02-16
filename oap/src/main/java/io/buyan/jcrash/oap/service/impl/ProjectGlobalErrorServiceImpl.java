package io.buyan.jcrash.oap.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import io.buyan.jcrash.oap.entity.ProjectGlobalError;
import io.buyan.jcrash.oap.dao.ProjectGlobalErrorDao;
import io.buyan.jcrash.oap.service.ProjectGlobalErrorService;
/**
 * 
 *
 * @author Pengyu Gan
 * CreateDate 2022/2/14
 */
@Service
public class ProjectGlobalErrorServiceImpl implements ProjectGlobalErrorService{

    @Resource
    private ProjectGlobalErrorDao projectGlobalErrorDao;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return projectGlobalErrorDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ProjectGlobalError record) {
//        return projectGlobalErrorDao.insert(record);
        return 0;
    }

    @Override
    public int insertSelective(ProjectGlobalError record) {
        return projectGlobalErrorDao.insertSelective(record);
    }

    @Override
    public ProjectGlobalError selectByPrimaryKey(Long id) {
        return projectGlobalErrorDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ProjectGlobalError record) {
        return projectGlobalErrorDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ProjectGlobalError record) {
        return projectGlobalErrorDao.updateByPrimaryKey(record);
    }

}
