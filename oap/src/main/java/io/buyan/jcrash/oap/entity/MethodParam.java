package io.buyan.jcrash.oap.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 方法参数类型定义结构
 * @author Pengyu Gan
 * CreateDate 2022/1/25
 */
@Data
@Document
public class MethodParam {

    private String name;

    private TypeDeclaring typeDeclaring;

    private String paramDeclaring;

}
