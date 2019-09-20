/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost
 Source Database       : auto_web_db

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : utf-8

 Date: 09/20/2019 18:19:57 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `entity_case`
-- ----------------------------
DROP TABLE IF EXISTS `entity_case`;
CREATE TABLE `entity_case` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '用例名称',
  `url` varchar(255) DEFAULT NULL COMMENT '用例url',
  `protocol_id` int(11) NOT NULL DEFAULT '1' COMMENT '用例协议id',
  `case_type_id` int(11) NOT NULL DEFAULT '1' COMMENT '用例类型ID',
  `param_id` int(11) DEFAULT NULL COMMENT '接口参数外键id',
  `header_id` int(11) DEFAULT NULL,
  `cookie_id` int(11) DEFAULT NULL,
  `time_out` int(11) NOT NULL DEFAULT '1000' COMMENT '请求超时时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `protocol_id` (`protocol_id`),
  KEY `case_type_id` (`case_type_id`),
  KEY `header_id` (`header_id`),
  KEY `param_id` (`param_id`),
  KEY `cookie_id` (`cookie_id`),
  CONSTRAINT `entity_case_ibfk_1` FOREIGN KEY (`protocol_id`) REFERENCES `entity_protocol` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `entity_case_ibfk_2` FOREIGN KEY (`case_type_id`) REFERENCES `entity_case_type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `entity_case_ibfk_3` FOREIGN KEY (`header_id`) REFERENCES `entity_header` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `entity_case_ibfk_4` FOREIGN KEY (`param_id`) REFERENCES `entity_param` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `entity_case_ibfk_5` FOREIGN KEY (`cookie_id`) REFERENCES `entity_cookie` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `entity_case_type`
-- ----------------------------
DROP TABLE IF EXISTS `entity_case_type`;
CREATE TABLE `entity_case_type` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL DEFAULT 'normal' COMMENT '用例类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `entity_case_type`
-- ----------------------------
BEGIN;
INSERT INTO `entity_case_type` VALUES ('1', 'normal'), ('2', 'exception');
COMMIT;

-- ----------------------------
--  Table structure for `entity_cookie`
-- ----------------------------
DROP TABLE IF EXISTS `entity_cookie`;
CREATE TABLE `entity_cookie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` text COMMENT 'cookie值',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `entity_env`
-- ----------------------------
DROP TABLE IF EXISTS `entity_env`;
CREATE TABLE `entity_env` (
  `id` int(11) NOT NULL,
  `env` varchar(255) NOT NULL COMMENT '项目环境',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `entity_env`
-- ----------------------------
BEGIN;
INSERT INTO `entity_env` VALUES ('1', 'dev'), ('2', 'hotfix'), ('3', 'online');
COMMIT;

-- ----------------------------
--  Table structure for `entity_header`
-- ----------------------------
DROP TABLE IF EXISTS `entity_header`;
CREATE TABLE `entity_header` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` text COMMENT '接口header值',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `entity_module`
-- ----------------------------
DROP TABLE IF EXISTS `entity_module`;
CREATE TABLE `entity_module` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '模块名称',
  `project_id` int(11) NOT NULL COMMENT '项目外键id',
  `env_id` int(11) NOT NULL COMMENT '模块环境外键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `project_id` (`project_id`),
  KEY `env_id` (`env_id`),
  CONSTRAINT `entity_module_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `entity_project` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `entity_module_ibfk_2` FOREIGN KEY (`env_id`) REFERENCES `entity_env` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `entity_module`
-- ----------------------------
BEGIN;
INSERT INTO `entity_module` VALUES ('1', '用户模块', '1', '1', '2019-09-20 12:49:52'), ('2', '题库模块', '1', '1', '2019-09-20 12:50:20'), ('3', '用户模块', '2', '1', '2019-09-20 12:51:08');
COMMIT;

-- ----------------------------
--  Table structure for `entity_param`
-- ----------------------------
DROP TABLE IF EXISTS `entity_param`;
CREATE TABLE `entity_param` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `param` text COMMENT '接口参数',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `entity_project`
-- ----------------------------
DROP TABLE IF EXISTS `entity_project`;
CREATE TABLE `entity_project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '项目名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `entity_project`
-- ----------------------------
BEGIN;
INSERT INTO `entity_project` VALUES ('4', 'crm'), ('6', 'orm'), ('5', '商城'), ('3', '学生pad'), ('2', '教师pad'), ('1', '教师空间');
COMMIT;

-- ----------------------------
--  Table structure for `entity_protocol`
-- ----------------------------
DROP TABLE IF EXISTS `entity_protocol`;
CREATE TABLE `entity_protocol` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL DEFAULT 'http',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `entity_role`
-- ----------------------------
DROP TABLE IF EXISTS `entity_role`;
CREATE TABLE `entity_role` (
  `id` int(11) NOT NULL,
  `role` varchar(255) NOT NULL DEFAULT '普通用户' COMMENT '用户角色',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `entity_role`
-- ----------------------------
BEGIN;
INSERT INTO `entity_role` VALUES ('1', '普通用户'), ('2', 'VIP'), ('3', '管理员');
COMMIT;

-- ----------------------------
--  Table structure for `entity_token`
-- ----------------------------
DROP TABLE IF EXISTS `entity_token`;
CREATE TABLE `entity_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `token` varchar(200) COLLATE utf8_bin NOT NULL,
  `update_time` datetime(6) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `token` (`token`),
  UNIQUE KEY `user_id` (`user_id`),
  CONSTRAINT `entity_token_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `entity_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `entity_user`
-- ----------------------------
DROP TABLE IF EXISTS `entity_user`;
CREATE TABLE `entity_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `role_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `entity_user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `entity_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
