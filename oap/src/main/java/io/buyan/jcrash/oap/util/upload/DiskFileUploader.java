package io.buyan.jcrash.oap.util.upload;

import io.buyan.jcrash.oap.common.id.SnowflakeIDGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;

/**
 * 写入磁盘的文件上传方式
 *
 * @author Pengyu Gan
 * CreateDate 2022/3/8
 */
@Service("diskFileUploader")
public class DiskFileUploader extends FileUploader {

    @Resource
    private SnowflakeIDGenerator idGenerator;

    @Value("${jcrash.uploader.disk.path}")
    private String path;

    @Override
    protected UploadResult doUpload(byte[] bytes, String filename) {
        String finalName =  idGenerator.getId() + "-" + filename;
        String filePath = path + File.separator + finalName;
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            fos.write(bytes);
        } catch (Exception e) {
            return UploadResult.error(filename, e.getMessage());
        }
        return UploadResult.success(filename, filePath);
    }
}
