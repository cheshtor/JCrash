DROP DATABASE IF EXISTS `jcrash`;
CREATE DATABASE `jcrash`;

DROP TABLE IF EXISTS `project`;
CREATE TABLE `project`
(
    `id` BIGINT COMMENT '项目 ID',
    `name` VARCHAR(64) NOT NULL COMMENT '项目名称',
    PRIMARY KEY `pk_project_id` (`id`)
);

DROP TABLE IF EXISTS `dubbo_jar`;
CREATE TABLE `dubbo_jar`
(
    `id` BIGINT COMMENT 'Jar 包 ID',
    `project_id`BIGINT NOT NULL COMMENT '关联项目 ID',
    `scan_version` BIGINT NOT NULL COMMENT '扫描版本',
    `name` VARCHAR(128) NOT NULL COMMENT 'Jar 包名称',
    PRIMARY KEY `pk_jar_id` (`id`)
);

DROP TABLE IF EXISTS `dubbo_jar_error`;
CREATE TABLE `dubbo_jar_error`
(
    `id` BIGINT COMMENT 'Jar 包解析错误信息 ID',
    `jar_id` BIGINT NOT NULL COMMENT '关联 Jar ID',
    `classname` VARCHAR(256) NOT NULL COMMENT '异常类名',
    `message` VARCHAR(256) NOT NULL COMMENT '异常信息',
    PRIMARY KEY `pk_jar_error_id` (`id`)
);

DROP TABLE IF EXISTS `dubbo_interface`;
CREATE TABLE `dubbo_interface`
(
    `id` BIGINT COMMENT 'Dubbo 接口 ID',
    `jar_id` BIGINT NOT NULL COMMENT 'Jar ID',
    `classname` VARCHAR(256) NOT NULL COMMENT '接口名称',
    PRIMARY KEY `pk_itf_id` (`id`)
);

DROP TABLE IF EXISTS `dubbo_method`;
CREATE TABLE `dubbo_method`
(
    `id` BIGINT COMMENT 'Dubbo 方法 ID',
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
    `id` BIGINT COMMENT 'Dubbo 方法参数 ID',
    `method_id` BIGINT NOT NULL COMMENT 'Dubbo 方法 ID',
    `name` VARCHAR(32) NOT NULL COMMENT '形参名称',
    `param_type_json` VARCHAR(512) NOT NULL COMMENT '参数类型描述',
    `literal_param` VARCHAR(512) NOT NULL COMMENT '文本形式参数',
    PRIMARY KEY `pk_param_id` (`id`)
);

DROP TABLE IF EXISTS `custom_bean`;
CREATE TABLE `custom_bean`
(
    `id` BIGINT COMMENT '自定义类型 ID',
    `project_id`BIGINT NOT NULL COMMENT '关联项目 ID',
    `classname` VARCHAR(256) NOT NULL COMMENT '自定义类型名称',
    PRIMARY KEY `pk_id` (`id`)
);

DROP TABLE IF EXISTS `custom_bean_field`;
CREATE TABLE `custom_bean_field`
(
    `id` BIGINT COMMENT '自定义类型字段 ID',
    `bean_id` BIGINT NOT NULL COMMENT '关联自定义类型 ID',
    `field_name` VARCHAR(32) NOT NULL COMMENT '字段名称',
    `field_type` VARCHAR(256) NOT NULL COMMENT '字段类型',
    PRIMARY KEY `pk_id` (`id`)
);

DROP TABLE IF EXISTS `project_global_error`;
CREATE TABLE `project_global_error`
(
    `id` BIGINT COMMENT '全局异常 ID',
    `project_id`BIGINT NOT NULL COMMENT '关联项目 ID',
    `item_name` VARCHAR(256) NOT NULL COMMENT '异常项目名称',
    `message` VARCHAR(256) NOT NULL COMMENT '异常信息',
    PRIMARY KEY `pk_id` (`id`)
);

DROP TABLE IF EXISTS `project_jar`;
CREATE TABLE `project_jar`
(
    `project_id`BIGINT NOT NULL COMMENT '关联项目 ID',
    `original_name` VARCHAR(256) NOT NULL COMMENT 'Jar 文件原名',
    `url` VARCHAR(256) NOT NULL COMMENT 'Jar 文件存储地址',
    `hash_value` VARCHAR(32) NOT NULL COMMENT '文件 Hash 值',
    `create_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP() COMMENT '存储时间',
    PRIMARY KEY `pk_projectId_originalName` (`project_id`, `original_name`)
);