package io.buyan.jcrash.oap.storage;

import io.buyan.jcrash.dubbo.scanner.structure.Result;

/**
 * Dubbo API 解析结构存储接口
 *
 * @author Pengyu Gan
 * CreateDate 2022/1/26
 */
public interface DubboApiStorage {

    /**
     * 保存 Dubbo API 扫描结构化结构
     * @param result {@link Result} Dubbo API 扫描结构化结构
     */
    void saveScanResult(Result result);

}
