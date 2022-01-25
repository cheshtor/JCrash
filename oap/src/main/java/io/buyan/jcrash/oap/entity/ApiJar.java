package io.buyan.jcrash.oap.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

/**
 * Jar 包定义结构
 *
 * @author Pengyu Gan
 * CreateDate 2022/1/25
 */
@Data
@Document
public class ApiJar {

    private String jarName;

    private List<ApiInterface> interfaces;

    private Map<String, String> errors;

}
