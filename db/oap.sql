DROP DATABASE IF EXISTS `jcrash`;
CREATE DATABASE `jcrash`;

DROP TABLE IF EXISTS `project`;
CREATE TABLE `project`
(
    `id` BIGINT AUTO_INCREMENT COMMENT '项目 ID',
    `name` VARCHAR(64) NOT NULL COMMENT '项目名称',
    PRIMARY KEY `pk_project_id` (`id`)
);

DROP TABLE IF EXISTS `dubbo_jar`;
CREATE TABLE `dubbo_jar`
(
    `id` BIGINT AUTO_INCREMENT COMMENT 'Jar 包 ID',
    `project_id`BIGINT NOT NULL COMMENT '关联项目 ID',
    `name` VARCHAR(128) NOT NULL COMMENT 'Jar 包名称',
    PRIMARY KEY `pk_jar_id` (`id`)
);

DROP TABLE IF EXISTS `dubbo_jar_error`;
CREATE TABLE `dubbo_jar_error`
(
    `id` BIGINT AUTO_INCREMENT COMMENT 'Jar 包解析错误信息 ID',
    `jar_id` BIGINT NOT NULL COMMENT '关联 Jar ID',
    `classname` VARCHAR(256) NOT NULL COMMENT '异常类名',
    `message` VARCHAR(256) NOT NULL COMMENT '异常信息',
    PRIMARY KEY `pk_jar_error_id` (`id`)
);

DROP TABLE IF EXISTS `dubbo_interface`;
CREATE TABLE `dubbo_interface`
(
    `id` BIGINT AUTO_INCREMENT COMMENT 'Dubbo 接口 ID',
    `jar_id` BIGINT NOT NULL COMMENT 'Jar ID',
    `classname` VARCHAR(256) NOT NULL COMMENT '接口名称',
    PRIMARY KEY `pk_itf_id` (`id`)
);

DROP TABLE IF EXISTS `dubbo_method`;
CREATE TABLE `dubbo_method`
(
    `id` BIGINT AUTO_INCREMENT COMMENT 'Dubbo 方法 ID',
    `interface_id` BIGINT NOT NULL COMMENT 'Dubbo 接口 ID',
    `method_name` VARCHAR(128) NOT NULL COMMENT '方法名',
    `return_type_json` VARCHAR(512) NOT NULL COMMENT '返回值类型描述',
    `literal_return_type` VARCHAR(128) NOT NULL COMMENT '文本形式的返回值',
    `literal_method` VARCHAR(512) NOT NULL COMMENT '文本形式方法签名',
    PRIMARY KEY `pk_method_id` (`id`)
);

DROP TABLE IF EXISTS `dubbo_param`;
CREATE TABLE `dubbo_param`
(
    `id` BIGINT AUTO_INCREMENT COMMENT 'Dubbo 方法参数 ID',
    `method_id` BIGINT NOT NULL COMMENT 'Dubbo 方法 ID',
    `name` VARCHAR(32) NOT NULL COMMENT '形参名称',
    `param_type_json` VARCHAR(512) NOT NULL COMMENT '参数类型描述',
    `literal_param` VARCHAR(512) NOT NULL COMMENT '文本形式参数',
    PRIMARY KEY `pk_param_id` (`id`)
);

DROP TABLE IF EXISTS `custom_bean`;
CREATE TABLE `custom_bean`
(
    `id` BIGINT AUTO_INCREMENT COMMENT '自定义类型 ID',
    `project_id`BIGINT NOT NULL COMMENT '关联项目 ID',
    `classname` VARCHAR(256) NOT NULL COMMENT '自定义类型名称',
    PRIMARY KEY `pk_id` (`id`)
);

DROP TABLE IF EXISTS `custom_bean_field`;
CREATE TABLE `custom_bean_field`
(
    `id` BIGINT AUTO_INCREMENT COMMENT '自定义类型字段 ID',
    `bean_id` BIGINT NOT NULL COMMENT '关联自定义类型 ID',
    `field_name` VARCHAR(32) NOT NULL COMMENT '字段名称',
    `field_type` VARCHAR(256) NOT NULL COMMENT '字段类型',
    PRIMARY KEY `pk_id` (`id`)
);