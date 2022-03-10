package io.buyan.jcrash.oap.util.upload;

import lombok.Data;

/**
 * 文件上传结果
 *
 * @author Pengyu Gan
 * CreateDate 2022/3/10
 */
@Data
public class UploadResult {

    private boolean success;

    private String originalName;

    private String finalName;

    private String exception;

    private UploadResult(boolean success, String originalName, String finalName, String exception) {
        this.success = success;
        this.originalName = originalName;
        this.finalName = finalName;
        this.exception = exception;
    }

    public static UploadResult success(String originalName, String finalName) {
        return new UploadResult(true, originalName, finalName, null);
    }

    public static UploadResult error(String originalName, String exception) {
        return new UploadResult(false, originalName, null, exception);
    }
}
