package io.buyan.jcrash.oap.service.impl;

import io.buyan.jcrash.dubbo.scanner.structure.Result;
import io.buyan.jcrash.oap.JCrashException;
import io.buyan.jcrash.oap.common.id.SnowflakeIDGenerator;
import io.buyan.jcrash.oap.dao.ProjectMapper;
import io.buyan.jcrash.oap.entity.Project;
import io.buyan.jcrash.oap.entity.ProjectJar;
import io.buyan.jcrash.oap.service.ProjectJarService;
import io.buyan.jcrash.oap.service.ProjectService;
import io.buyan.jcrash.oap.util.upload.FileUploader;
import io.buyan.jcrash.oap.util.upload.UploadResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.*;

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

    @Resource
    @Qualifier("diskFileUploader")
    private FileUploader fileUploader;

    @Resource
    private ProjectJarService projectJarService;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public long createProject(Project project) {
        long projectId = idGenerator.getId();
        project.setId(projectId);
        projectMapper.insert(project);
        return projectId;
    }

    @Override
    public List<UploadResult> uploadJars(MultipartFile[] files, Long projectId) {
        List<UploadResult> uploadResults = new ArrayList<>();
        if (null == files || files.length == 0) {
            return uploadResults;
        }
        // TODO 这里用 Map 会有问题。当上传两个文件，只是文件名不同，但 hash 相同时，key 会覆盖，最后返回给前端只有一个文件的信息
        Map<String, MultipartFile> hashMap = new HashMap<>();
        List<String> hashValues = new ArrayList<>();
        // 计算本次上传的所有文件的 hash 值
        for (MultipartFile file : files) {
            try {
                String hashValue = DigestUtils.md5DigestAsHex(file.getInputStream());
                hashValues.add(hashValue);
                // 文件 hash 值与文件对象映射
                hashMap.put(hashValue, file);
            } catch (Exception e) {
                log.error("获取文件 {} Hash 值异常。", file.getOriginalFilename(), e);
                return uploadResults;
            }
        }
        // 本次要保存文件的信息
        List<ProjectJar> preStoreJar = new ArrayList<>();
        // 查询与本次上传的文件一样的已上传文件
        List<ProjectJar> jars = projectJarService.findJarsByHashValue(hashValues);
        // 文件存在重复，分情况入库上传记录，但不执行保存操作
        if (!jars.isEmpty()) {
            for (ProjectJar jar : jars) {
                // 已保存文件的 hash 值
                String existingHashValue = jar.getHashValue();
                // 取出本次上传文件中 hash 值一致的文件
                MultipartFile file = hashMap.get(existingHashValue);
                // 直接创建上传结果，默认为上传成功
                UploadResult uploadResult = UploadResult.success(file.getOriginalFilename(), jar.getUrl());
                uploadResults.add(uploadResult);
                // 如果重复文件的文件名和 hash 值都重复，则不入库上传记录
                if (jar.getOriginalName().equals(file.getOriginalFilename())
                        && jar.getProjectId().equals(projectId)) {
                    hashMap.remove(existingHashValue);
                    continue;
                } else { // 入库上传记录
                    ProjectJar projectJar = new ProjectJar();
                    projectJar.setProjectId(projectId);
                    projectJar.setOriginalName(file.getOriginalFilename());
                    projectJar.setHashValue(existingHashValue);
                    projectJar.setUrl(jar.getUrl());
                    preStoreJar.add(projectJar);
                }
                hashMap.remove(existingHashValue);
            }
        }
        // 排除完重复文件后，剩下的就是需要保存的文件
        if (!hashMap.isEmpty()) {
            for (Map.Entry<String, MultipartFile> entry : hashMap.entrySet()) {
                String hashValue = entry.getKey();
                MultipartFile file = entry.getValue();
                // 创建上传记录
                ProjectJar projectJar = new ProjectJar();
                projectJar.setProjectId(projectId);
                projectJar.setOriginalName(file.getOriginalFilename());
                projectJar.setHashValue(hashValue);
                // 保存文件
                UploadResult uploadResult = fileUploader.uploadFile(file);
                uploadResults.add(uploadResult);
                projectJar.setUrl(uploadResult.getFinalName());
                preStoreJar.add(projectJar);
            }
        }
        // 入库
        if (!preStoreJar.isEmpty()) {
            projectJarService.saveJarInfos(preStoreJar);
        }
        return uploadResults;
    }

    @Override
    public Result doScan(Long projectId) {
        List<ProjectJar> jars = projectJarService.findJarsByProjectId(projectId);
        if (jars.isEmpty()) {
            throw new JCrashException("项目没有可扫描的 Jar 包。");
        }

        return null;
    }
}
