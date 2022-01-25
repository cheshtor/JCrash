package io.buyan.jcrash.oap.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 请求统一返回模型
 *
 * @author Pengyu Gan
 * CreateDate 2022/1/25
 */
@Data
public class JCrashResult<T> implements Serializable {

    private boolean success;

    private String message;

    private T data;

    public JCrashResult(T data) {
        this.success = true;
        this.data = data;
    }

    public JCrashResult(String message) {
        this.success = false;
        this.message = message;
    }

    public static <T> JCrashResult<T> success(T data) {
        return new JCrashResult<>(data);
    }

    public static <T> JCrashResult<T> error(String message) {
        return new JCrashResult<>(message);
    }
}
