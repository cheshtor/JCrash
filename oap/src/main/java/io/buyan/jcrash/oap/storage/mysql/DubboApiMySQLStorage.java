package io.buyan.jcrash.oap.storage.mysql;

import io.buyan.jcrash.dubbo.scanner.structure.Result;
import io.buyan.jcrash.oap.storage.DubboApiStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Dubbo API 扫描结果存储 - MySQL 实现
 *
 * @author Gan Pengyu
 * CreateDate 2022/2/4
 */
@Slf4j
@Service("mysql")
public class DubboApiMySQLStorage implements DubboApiStorage {

    @Override
    public void saveScanResult(Result result) {

    }
}
