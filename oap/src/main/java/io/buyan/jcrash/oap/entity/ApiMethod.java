package io.buyan.jcrash.oap.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * 方法定义结构
 *
 * @author Pengyu Gan
 * CreateDate 2022/1/25
 */
@Data
@Document
public class ApiMethod {

    private String methodName;

    private TypeDeclaring returnType;

    private String returnTypeDeclaring;

    private List<MethodParam> params;

    private String methodDeclaring;

}
