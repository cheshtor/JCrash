package io.buyan.jcrash.oap.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Pengyu Gan
 * CreateDate 2022/3/8
 */
@Slf4j
public abstract class FileUploader {

    /**
     * 单个文件大小上限 1MB
     */
    protected static final long fileMaxSize = 1024 * 1024 * 1024;

    public Map<String, String> uploadFiles(MultipartFile[] files) {
        Map<String, String> uploadError = new HashMap<>();
        for (MultipartFile file : files) {
            String filename = file.getName();
            if (file.isEmpty() || file.getSize() <= 0) {
                uploadError.put(filename, "文件为空");
                continue;
            }
            if (file.getSize() > fileMaxSize) {
                uploadError.put(filename, "文件大小超过限制");
                continue;
            }
            try {
                upload(file.getInputStream());
            } catch (Exception e) {
                uploadError.put(filename, e.getMessage());
                log.error("文件上传失败。文件名 {}", file.getName(), e);
            }
        }
        return uploadError;
    }

    protected abstract boolean upload(InputStream inputStream);

}
