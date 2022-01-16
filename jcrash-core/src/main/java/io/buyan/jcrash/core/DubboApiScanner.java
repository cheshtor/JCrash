package io.buyan.jcrash.core;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Dubbo 接口扫描器
 *
 * @author Gan Pengyu
 * CreateDate 2022/1/16
 */
public class DubboApiScanner {

    public void scanApi(List<File> jarFiles) {
        if (null == jarFiles || jarFiles.isEmpty()) {
            return;
        }
        List<URL> urls = jarFiles.stream().map(f -> {
            try {
                return f.toURI().toURL();
            } catch (MalformedURLException e) {
                return null;
            }
        }).collect(Collectors.toList());
        ApiClassLoader classLoader = new ApiClassLoader(urls.toArray(new URL[urls.size()]));

    }

}
