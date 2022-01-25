package io.buyan.jcrash.oap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 启动类
 *
 * @author Pengyu Gan
 * CreateDate 2022/1/25
 */
@SpringBootApplication
@EnableAspectJAutoProxy
public class JCrashOapApplication {

    public static void main(String[] args) {
        SpringApplication.run(JCrashOapApplication.class, args);
    }

}
