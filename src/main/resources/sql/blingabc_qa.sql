/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : blingabc_qa

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 26/11/2019 17:51:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for assert_type
-- ----------------------------
DROP TABLE IF EXISTS `assert_type`;
CREATE TABLE `assert_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of assert_type
-- ----------------------------
BEGIN;
INSERT INTO `assert_type` VALUES (1, 'title');
INSERT INTO `assert_type` VALUES (2, 'pagesource');
INSERT INTO `assert_type` VALUES (3, 'element');
COMMIT;

-- ----------------------------
-- Table structure for element_locator_type
-- ----------------------------
DROP TABLE IF EXISTS `element_locator_type`;
CREATE TABLE `element_locator_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) NOT NULL DEFAULT 'xpath' COMMENT '元素定位方式',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of element_locator_type
-- ----------------------------
BEGIN;
INSERT INTO `element_locator_type` VALUES (1, 'xpath');
INSERT INTO `element_locator_type` VALUES (2, 'name');
INSERT INTO `element_locator_type` VALUES (3, 'id');
INSERT INTO `element_locator_type` VALUES (4, 'classname');
INSERT INTO `element_locator_type` VALUES (5, 'linktext');
INSERT INTO `element_locator_type` VALUES (6, 'partialLinktext');
INSERT INTO `element_locator_type` VALUES (7, 'css');
INSERT INTO `element_locator_type` VALUES (8, 'tagname');
COMMIT;

-- ----------------------------
-- Table structure for element_operate
-- ----------------------------
DROP TABLE IF EXISTS `element_operate`;
CREATE TABLE `element_operate` (
  `id` int(11) NOT NULL,
  `operate` varchar(255) NOT NULL COMMENT '元素操作方法',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of element_operate
-- ----------------------------
BEGIN;
INSERT INTO `element_operate` VALUES (1, 'click');
INSERT INTO `element_operate` VALUES (2, 'setValue');
INSERT INTO `element_operate` VALUES (3, 'swipe');
COMMIT;

-- ----------------------------
-- Table structure for entity_assert
-- ----------------------------
DROP TABLE IF EXISTS `entity_assert`;
CREATE TABLE `entity_assert` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '断言名称',
  `assert_type_id` int(11) DEFAULT NULL COMMENT '断言类型id',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `val` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `assert_type_id` (`assert_type_id`),
  CONSTRAINT `entity_assert_ibfk_1` FOREIGN KEY (`assert_type_id`) REFERENCES `assert_type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of entity_assert
-- ----------------------------
BEGIN;
INSERT INTO `entity_assert` VALUES (1, '判断搜索按钮出现', 3, NULL, NULL, '6');
INSERT INTO `entity_assert` VALUES (2, '判断校招title', 1, '2019-11-13 18:02:48', NULL, '校园招聘');
COMMIT;

-- ----------------------------
-- Table structure for entity_case
-- ----------------------------
DROP TABLE IF EXISTS `entity_case`;
CREATE TABLE `entity_case` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '用例名称',
  `cases` text,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `depend` varchar(255) DEFAULT NULL COMMENT '依赖的用例',
  `case_type_id` int(11) NOT NULL DEFAULT '1',
  `catalog_id` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `case_type_id` (`case_type_id`),
  KEY `catalog_id` (`catalog_id`),
  CONSTRAINT `entity_case_ibfk_1` FOREIGN KEY (`case_type_id`) REFERENCES `entity_case_type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `entity_case_ibfk_2` FOREIGN KEY (`catalog_id`) REFERENCES `entity_catalog` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of entity_case
-- ----------------------------
BEGIN;
INSERT INTO `entity_case` VALUES (1, '全职登陆用例', '[{\"type\":\"page\",\"id\":1,\"index\":1},{\"type\":\"element\",\"id\":1,\"index\":2},{\"type\":\"element\",\"id\":2,\"index\":3},{\"type\":\"element\",\"id\":3,\"index\":4},{\"type\":\"element\",\"id\":4,\"index\":5}]', '2019-11-13 11:07:30', NULL, NULL, 2, 1);
INSERT INTO `entity_case` VALUES (2, '搜索功能', '[{\"type\":\"element\",\"id\":5,\"index\":1},{\"type\":\"assert\",\"id\":1,\"index\":2},{\"type\":\"element\",\"id\":6,\"index\":3}]', '2019-11-13 14:36:52', NULL, '[{\"index\":1,\"id\":1}]', 2, 1);
INSERT INTO `entity_case` VALUES (3, '进入校园招聘', '[{\"type\":\"element\",\"id\":7,\"index\":1},{\"type\":\"assert\",\"id\":2,\"index\":2},{\"type\":\"element\",\"id\":8,\"index\":3}]', '2019-11-13 18:04:33', NULL, '[{\"index\":1,\"id\":2}]', 2, 1);
COMMIT;

-- ----------------------------
-- Table structure for entity_case_type
-- ----------------------------
DROP TABLE IF EXISTS `entity_case_type`;
CREATE TABLE `entity_case_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `case_type` varchar(255) NOT NULL DEFAULT 'interface',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of entity_case_type
-- ----------------------------
BEGIN;
INSERT INTO `entity_case_type` VALUES (1, 'interface');
INSERT INTO `entity_case_type` VALUES (2, 'web');
INSERT INTO `entity_case_type` VALUES (3, 'app');
COMMIT;

-- ----------------------------
-- Table structure for entity_catalog
-- ----------------------------
DROP TABLE IF EXISTS `entity_catalog`;
CREATE TABLE `entity_catalog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(255) DEFAULT NULL,
  `icon` varchar(255) NOT NULL DEFAULT 'el-icon-folder-opened',
  `parent_id` int(11) DEFAULT NULL,
  `type` varchar(255) NOT NULL DEFAULT 'interface',
  `env_id` int(11) NOT NULL DEFAULT '1',
  `user_id` int(11) NOT NULL DEFAULT '1',
  `case_type_id` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `env_id` (`env_id`),
  KEY `user_id` (`user_id`),
  KEY `case_type_id` (`case_type_id`),
  CONSTRAINT `entity_catalog_ibfk_1` FOREIGN KEY (`env_id`) REFERENCES `entity_env` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `entity_catalog_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `entity_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `entity_catalog_ibfk_3` FOREIGN KEY (`case_type_id`) REFERENCES `entity_case_type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of entity_catalog
-- ----------------------------
BEGIN;
INSERT INTO `entity_catalog` VALUES (1, '首页目录', 'el-icon-folder-opened', NULL, 'interface', 1, 1, 1);
INSERT INTO `entity_catalog` VALUES (2, '语文目录', 'el-icon-folder-opened', NULL, 'interface', 1, 1, 1);
INSERT INTO `entity_catalog` VALUES (3, '数学目录', 'el-icon-folder-opened', NULL, 'interface', 1, 1, 1);
INSERT INTO `entity_catalog` VALUES (4, '课程表', 'el-icon-folder-opened', 1, 'interface', 1, 1, 1);
INSERT INTO `entity_catalog` VALUES (5, '课外作业', 'el-icon-folder-opened', 1, 'interface', 1, 1, 1);
INSERT INTO `entity_catalog` VALUES (6, '学生课程表', 'el-icon-folder-opened', 4, 'interface', 1, 1, 1);
INSERT INTO `entity_catalog` VALUES (7, '老师课程表', 'el-icon-folder-opened', 4, 'interface', 1, 1, 1);
INSERT INTO `entity_catalog` VALUES (8, '英语作业', 'el-icon-folder-opened', NULL, 'interface', 1, 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for entity_element
-- ----------------------------
DROP TABLE IF EXISTS `entity_element`;
CREATE TABLE `entity_element` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '元素名称',
  `locator_type_id` int(11) DEFAULT NULL COMMENT '定位方式',
  `locator` varchar(255) DEFAULT NULL COMMENT '元素定位值',
  `operation_id` int(11) DEFAULT NULL COMMENT '操作',
  `val` varchar(255) DEFAULT NULL COMMENT '操作值',
  `time_out` int(11) DEFAULT NULL COMMENT '元素超时时间',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `locator_type_id` (`locator_type_id`),
  KEY `operation_id` (`operation_id`),
  CONSTRAINT `entity_element_ibfk_1` FOREIGN KEY (`locator_type_id`) REFERENCES `element_locator_type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `entity_element_ibfk_2` FOREIGN KEY (`operation_id`) REFERENCES `element_operate` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of entity_element
-- ----------------------------
BEGIN;
INSERT INTO `entity_element` VALUES (1, '求职者登陆按钮', 1, '//*[@id=\"jq_header\"]/div/div[2]/div[1]/a[1]', 1, NULL, 10, '2019-11-13 11:02:15', NULL);
INSERT INTO `entity_element` VALUES (2, '用户名输入框', 1, '/html/body/div/div[2]/div/div/div/div[2]/form/ul/li[1]/input', 2, '15910401369', 10, '2019-11-13 11:03:47', NULL);
INSERT INTO `entity_element` VALUES (3, '登陆密码输入框', 1, '/html/body/div/div[2]/div/div/div/div[2]/form/ul/li[3]/input', 2, 'plm806', 10, '2019-11-13 11:04:46', NULL);
INSERT INTO `entity_element` VALUES (4, '登陆按钮', 1, '/html/body/div/div[2]/div/div/div/div[2]/form/div/button', 1, NULL, 10, '2019-11-13 11:05:27', NULL);
INSERT INTO `entity_element` VALUES (5, '找工作按钮', 1, '//*[@id=\"nav\"]/ul/li[2]/a', 1, NULL, 10, '2019-11-13 17:15:31', NULL);
INSERT INTO `entity_element` VALUES (6, '搜索按钮', 1, '//*[@id=\"searchForm\"]/ul/li[11]/button', 1, NULL, 10, '2019-11-13 17:18:28', NULL);
INSERT INTO `entity_element` VALUES (7, '校园招聘按钮', 1, '//*[@id=\"nav\"]/ul/li[3]/a', 1, NULL, 10, '2019-11-13 18:00:24', NULL);
INSERT INTO `entity_element` VALUES (8, '校招按钮', 1, '/html/body/div[2]/div/a[2]', 1, NULL, 10, '2019-11-13 18:01:52', NULL);
COMMIT;

-- ----------------------------
-- Table structure for entity_env
-- ----------------------------
DROP TABLE IF EXISTS `entity_env`;
CREATE TABLE `entity_env` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `env` varchar(255) NOT NULL DEFAULT 'dev',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of entity_env
-- ----------------------------
BEGIN;
INSERT INTO `entity_env` VALUES (1, 'dev');
INSERT INTO `entity_env` VALUES (2, 'hotfix');
COMMIT;

-- ----------------------------
-- Table structure for entity_env_variable
-- ----------------------------
DROP TABLE IF EXISTS `entity_env_variable`;
CREATE TABLE `entity_env_variable` (
  `id` int(11) NOT NULL,
  `c_key` varchar(255) DEFAULT NULL,
  `c_value` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `env_id` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `env_id` (`env_id`),
  CONSTRAINT `entity_env_variable_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `entity_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `entity_env_variable_ibfk_2` FOREIGN KEY (`env_id`) REFERENCES `entity_env` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for entity_interface_case
-- ----------------------------
DROP TABLE IF EXISTS `entity_interface_case`;
CREATE TABLE `entity_interface_case` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL,
  `protocol_id` int(11) NOT NULL DEFAULT '1',
  `catalog_id` int(11) NOT NULL DEFAULT '1',
  `param_type` varchar(255) NOT NULL DEFAULT 'form',
  `param` text,
  `header` text,
  `cookie` text,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `request_way_id` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `protocol_id` (`protocol_id`),
  KEY `catalog_id` (`catalog_id`),
  KEY `reqyest_way_id` (`request_way_id`),
  CONSTRAINT `entity_interface_case_ibfk_1` FOREIGN KEY (`protocol_id`) REFERENCES `entity_protocol` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `entity_interface_case_ibfk_2` FOREIGN KEY (`catalog_id`) REFERENCES `entity_catalog` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `entity_interface_case_ibfk_3` FOREIGN KEY (`request_way_id`) REFERENCES `request_way` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of entity_interface_case
-- ----------------------------
BEGIN;
INSERT INTO `entity_interface_case` VALUES (1, '百度首页', 'http://www.baidu.com', 1, 1, '', NULL, '{\"name\":\"zhangsan\"}', NULL, '2019-11-21 02:08:32', NULL, 1);
INSERT INTO `entity_interface_case` VALUES (2, '百度首页', 'http://127.0.0.1:9999/api/interface/case', 1, 4, 'form', '{\"url\":\"http://www.baidu.com\",\"name\":\"百度首页\",\"protocolId\":\"1\",\"catalogId\":\"1\",\"paramType\":\"\",\"header\":\"{\\\"name\\\":\\\"zhangsan\\\"}\"}', '{}', NULL, '2019-11-21 02:39:37', NULL, 1);
INSERT INTO `entity_interface_case` VALUES (3, '百度测试post首页', 'http://www.baidu.com', 1, 6, 'form', '{\"name\":\"zhangsan\",\"value\":\"129389123\"}', '{\"requestid\":\"qa_00900901\"}', NULL, '2019-11-21 02:49:29', NULL, 1);
INSERT INTO `entity_interface_case` VALUES (4, '测试首页', 'http://www.baidu.com', 1, 3, 'form', '{\"name\":\"zhangsan\",\"value\":\"129389123\"}', '{\"requestid\":\"qa_00900901\"}', NULL, '2019-11-21 02:50:39', NULL, 1);
INSERT INTO `entity_interface_case` VALUES (5, '测试首页关闭', 'http://www.baidu.com', 1, 1, 'form', '{\"name\":\"zhangsan\",\"password\":\"qa_09201901\"}', '{\"request_id\":\"qa_090901\",\"Content-Type\":\"json\"}', NULL, '2019-11-21 05:36:00', NULL, 1);
INSERT INTO `entity_interface_case` VALUES (6, 'testerhome', 'http://testerhome.baidu.com', 1, 1, 'form', '{\"id\":\"109091\",\"name\":\"zhanshan\"}', '{\"requestid\":\"qa_090901\"}', NULL, '2019-11-21 05:40:51', NULL, 1);
COMMIT;

-- ----------------------------
-- Table structure for entity_page
-- ----------------------------
DROP TABLE IF EXISTS `entity_page`;
CREATE TABLE `entity_page` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `val` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `page_operate_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `page_operate_id` (`page_operate_id`),
  CONSTRAINT `entity_page_ibfk_1` FOREIGN KEY (`page_operate_id`) REFERENCES `page_operate` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of entity_page
-- ----------------------------
BEGIN;
INSERT INTO `entity_page` VALUES (1, '打开全职网站', 'http://www.quanzhi.com/', '2019-11-13 10:59:54', NULL, 1);
INSERT INTO `entity_page` VALUES (2, '退出登陆用例', NULL, '2019-11-13 13:45:17', NULL, 5);
COMMIT;

-- ----------------------------
-- Table structure for entity_protocol
-- ----------------------------
DROP TABLE IF EXISTS `entity_protocol`;
CREATE TABLE `entity_protocol` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `protocol` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of entity_protocol
-- ----------------------------
BEGIN;
INSERT INTO `entity_protocol` VALUES (1, 'http');
INSERT INTO `entity_protocol` VALUES (2, 'websocket');
COMMIT;

-- ----------------------------
-- Table structure for entity_request_exception
-- ----------------------------
DROP TABLE IF EXISTS `entity_request_exception`;
CREATE TABLE `entity_request_exception` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message` text NOT NULL COMMENT '异常信息',
  `happend_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '异常产生时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for entity_request_log
-- ----------------------------
DROP TABLE IF EXISTS `entity_request_log`;
CREATE TABLE `entity_request_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL,
  `type` int(11) NOT NULL DEFAULT '1' COMMENT '1(普通请求)2（ajax）',
  `way` varchar(255) NOT NULL DEFAULT 'get' COMMENT '请求方式',
  `class_path` varchar(255) NOT NULL COMMENT '请求类的路径',
  `method_name` varchar(255) NOT NULL COMMENT '请求方法名',
  `param` text NOT NULL COMMENT '请求参数',
  `operation` varchar(255) DEFAULT NULL COMMENT '操作类型',
  `sessionid` varchar(200) NOT NULL COMMENT '请求接口唯一session标示',
  `start_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '请求开始时间',
  `finish_time` int(11) NOT NULL COMMENT '请求结束时间',
  `return_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '接口返回时间',
  `return_data` text NOT NULL COMMENT '返回结果',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for entity_user
-- ----------------------------
DROP TABLE IF EXISTS `entity_user`;
CREATE TABLE `entity_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of entity_user
-- ----------------------------
BEGIN;
INSERT INTO `entity_user` VALUES (1, 'liuzhanhui', '123456', NULL, '1');
COMMIT;

-- ----------------------------
-- Table structure for page_operate
-- ----------------------------
DROP TABLE IF EXISTS `page_operate`;
CREATE TABLE `page_operate` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `operate` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of page_operate
-- ----------------------------
BEGIN;
INSERT INTO `page_operate` VALUES (1, 'open');
INSERT INTO `page_operate` VALUES (2, 'swipe');
INSERT INTO `page_operate` VALUES (3, 'js');
INSERT INTO `page_operate` VALUES (4, 'switch');
INSERT INTO `page_operate` VALUES (5, 'close');
INSERT INTO `page_operate` VALUES (6, 'quit');
COMMIT;

-- ----------------------------
-- Table structure for request_way
-- ----------------------------
DROP TABLE IF EXISTS `request_way`;
CREATE TABLE `request_way` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `way` varchar(255) NOT NULL DEFAULT 'get',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of request_way
-- ----------------------------
BEGIN;
INSERT INTO `request_way` VALUES (1, 'get');
INSERT INTO `request_way` VALUES (2, 'post');
INSERT INTO `request_way` VALUES (3, 'delete');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
