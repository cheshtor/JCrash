package io.buyan.jcrash.oap.service;

import io.buyan.jcrash.dubbo.scanner.structure.Result;
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

    /**
     * 创建项目
     * @param project {@link Project} 项目信息
     * @return true 成功 false 失败
     */
    long createProject(Project project);

    /**
     * 上传 Jar 包，以文件 hash 值作为标准，重复文件不会重复保存
     * @param files Jar 包文件列表
     * @param projectId 项目 ID
     * @return 上传结果
     */
    List<UploadResult> uploadJars(MultipartFile[] files, Long projectId);

    /**
     * 扫描项目下的 Jar 包
     * @param projectId 项目 ID
     * @return {@link Result} 扫描结果
     */
    Result doScan(Long projectId);

}
