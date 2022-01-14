package io.buyan.jcrash.core;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;

/**
 * @author Pengyu Gan
 * CreateDate 2022/1/14
 */
public interface DubboCaller {

    ApplicationConfig applicationConfig();

    RegistryConfig registryConfig();

}
