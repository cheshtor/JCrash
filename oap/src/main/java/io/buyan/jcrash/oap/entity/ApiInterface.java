package io.buyan.jcrash.oap.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * 接口定义结构
 * @author Pengyu Gan
 * CreateDate 2022/1/25
 */
@Data
@Document
public class ApiInterface {

    private String classname;

    private List<ApiMethod> methods;

}
