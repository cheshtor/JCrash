package io.buyan.jcrash.oap.service.impl;

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
        for (MultipartFile file : files) {
            try {
                String hashValue = DigestUtils.md5DigestAsHex(file.getInputStream());
                hashValues.add(hashValue);
                hashMap.put(hashValue, file);
            } catch (Exception e) {
                log.error("获取文件 {} Hash 值异常。", file.getOriginalFilename(), e);
                return uploadResults;
            }
        }
        List<ProjectJar> preStoreJar = new ArrayList<>();
        List<ProjectJar> jars = projectJarService.findJarsByHashValue(hashValues);
        if (!jars.isEmpty()) {
            for (ProjectJar jar : jars) {
                String existingHashValue = jar.getHashValue();
                MultipartFile file = hashMap.get(existingHashValue);
                UploadResult uploadResult = UploadResult.success(file.getOriginalFilename(), jar.getUrl());
                uploadResults.add(uploadResult);
                if (jar.getOriginalName().equals(file.getOriginalFilename())
                        && jar.getProjectId().equals(projectId)) {
                    hashMap.remove(existingHashValue);
                    continue;
                } else {
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
        if (!hashMap.isEmpty()) {
            for (Map.Entry<String, MultipartFile> entry : hashMap.entrySet()) {
                String hashValue = entry.getKey();
                MultipartFile file = entry.getValue();
                ProjectJar projectJar = new ProjectJar();
                projectJar.setProjectId(projectId);
                projectJar.setOriginalName(file.getOriginalFilename());
                projectJar.setHashValue(hashValue);
                UploadResult uploadResult = fileUploader.uploadFile(file);
                uploadResults.add(uploadResult);
                projectJar.setUrl(uploadResult.getFinalName());
                preStoreJar.add(projectJar);
            }
        }
        if (!preStoreJar.isEmpty()) {
            projectJarService.saveJarInfos(preStoreJar);
        }
        return uploadResults;
    }
}
