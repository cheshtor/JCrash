package io.buyan.jcrash.oap.util.upload;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传
 *
 * @author Pengyu Gan
 * CreateDate 2022/3/8
 */
@Slf4j
public abstract class FileUploader {

    /**
     * 单个文件大小上限 1MB
     */
    protected static final long fileMaxSize = 1024 * 1024 * 1024;

    /**
     * 上传指定文件
     *
     * @param file 待上传文件
     * @return 上传失败的文件及其失败原因。如果全部上传成功，返回 empty map
     */
    public UploadResult uploadFile(MultipartFile file) {
        String filename = file.getOriginalFilename();
        if (file.isEmpty() || file.getSize() <= 0) {
            return UploadResult.error(filename, "文件为空");
        }
        if (file.getSize() > fileMaxSize) {
            return UploadResult.error(filename, "文件大小超过限制");
        }
        try {
            return doUpload(file.getBytes(), filename);
        } catch (Exception e) {
            return UploadResult.error(filename, e.getMessage());
        }
    }

    /**
     * 上传逻辑
     * @param bytes 当前要上传文件的二进制数据
     * @return true 成功 false 失败
     */
    protected abstract UploadResult doUpload(byte[] bytes, String filename);


}
