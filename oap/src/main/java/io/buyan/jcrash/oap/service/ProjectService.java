package io.buyan.jcrash.oap.service;

import io.buyan.jcrash.oap.entity.Project;
import io.buyan.jcrash.oap.util.upload.UploadResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 项目服务
 *
 * @author Pengyu Gan
 * CreateDate 2022/3/7
 */
public interface ProjectService {

    long createProject(Project project);

    List<UploadResult> uploadJars(MultipartFile[] files, Long projectId);

}
