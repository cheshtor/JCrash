package io.buyan.jcrash.oap;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 启动类
 *
 * @author Pengyu Gan
 * CreateDate 2022/1/25
 */
@Slf4j
@SpringBootApplication
@EnableAspectJAutoProxy
@MapperScan(basePackages = {"io.buyan.jcrash.oap.dao"})
public class JCrashOapApplication {

    public static void main(String[] args) {
        SpringApplication.run(JCrashOapApplication.class, args);
        log.info("JCrashOapApplication 启动成功");
    }

}
