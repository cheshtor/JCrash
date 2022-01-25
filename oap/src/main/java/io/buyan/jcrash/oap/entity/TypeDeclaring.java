package io.buyan.jcrash.oap.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * 参数类型定义结构
 *
 * @author Pengyu Gan
 * CreateDate 2022/1/25
 */
@Data
@Document
public class TypeDeclaring {

    private String typeName;

    private List<TypeDeclaring> genericTypes;

}
