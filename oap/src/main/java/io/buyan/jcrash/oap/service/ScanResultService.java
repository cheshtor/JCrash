package io.buyan.jcrash.oap.service;

import io.buyan.jcrash.dubbo.scanner.structure.Result;

/**
 * 扫描结果服务
 *
 * @author Pengyu Gan
 * CreateDate 2022/3/8
 */
public interface ScanResultService {

    /**
     * 保存扫描结果。扫描结果是一个多层嵌套结构，下层数据需要上层数据的数据库主键 ID，如果
     * 逐层解析入库耗时会非常长，所以使用第三方 ID 生成器来主动生成主键 ID，将所有层级的
     * 数据解析完成后批量入库。
     *
     * @param scanResult {@link Result} 扫描结果
     * @param projectId 项目 ID
     */
    void saveScanResult(Result scanResult, Long projectId);

}
