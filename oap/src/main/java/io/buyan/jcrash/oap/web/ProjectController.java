package io.buyan.jcrash.oap.web;

import io.buyan.jcrash.oap.common.JCrashResult;
import io.buyan.jcrash.oap.entity.Project;
import io.buyan.jcrash.oap.service.ProjectService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 项目接口
 *
 * @author Pengyu Gan
 * CreateDate 2022/3/8
 */
@RestController
@RequestMapping(value = "/api/v1/project")
public class ProjectController {

    @Resource
    private ProjectService projectService;

    @PostMapping(value = "/create")
    public JCrashResult<Long> createProject(@RequestBody Project project) {
        long projectId = projectService.createProject(project);
        return JCrashResult.success(projectId);
    }

    @PostMapping(value = "/uploadJar")
    public JCrashResult<Boolean> uploadJars(MultipartFile[] files) {
        return JCrashResult.success(true);
    }


}
