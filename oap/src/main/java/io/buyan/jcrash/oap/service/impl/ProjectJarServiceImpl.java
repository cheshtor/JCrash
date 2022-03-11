package io.buyan.jcrash.oap.service.impl;

import io.buyan.jcrash.oap.dao.ProjectJarMapper;
import io.buyan.jcrash.oap.entity.ProjectJar;
import io.buyan.jcrash.oap.service.ProjectJarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 项目 Jar 包管理服务 - 实现
 *
 * @author Pengyu Gan
 * CreateDate 2022/3/10
 */
@Slf4j
@Service
public class ProjectJarServiceImpl implements ProjectJarService {

    @Resource
    private ProjectJarMapper projectJarMapper;

    @Override
    public List<ProjectJar> findJarsByHashValue(List<String> hashValues) {
        return projectJarMapper.findByHashValue(hashValues);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean saveJarInfos(List<ProjectJar> jars) {
        int rows = projectJarMapper.batchInsert(jars);
        return rows == jars.size();
    }

    @Override
    public List<ProjectJar> findJarsByProjectId(Long projectId) {
        return projectJarMapper.findByProjectId(projectId);
    }
}
