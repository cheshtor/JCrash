package io.buyan.jcrash.oap.service.impl;

import io.buyan.jcrash.oap.common.id.SnowflakeIDGenerator;
import io.buyan.jcrash.oap.dao.ProjectMapper;
import io.buyan.jcrash.oap.entity.Project;
import io.buyan.jcrash.oap.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author Pengyu Gan
 * CreateDate 2022/3/7
 */
@Slf4j
@Service
public class ProjectServiceImpl implements ProjectService {

    @Resource
    private SnowflakeIDGenerator idGenerator;

    @Resource
    private ProjectMapper projectMapper;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public long createProject(Project project) {
        long projectId = idGenerator.getId();
        project.setId(projectId);
        projectMapper.insert(project);
        return projectId;
    }

}
