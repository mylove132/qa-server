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

 Date: 15/11/2019 19:04:50
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
-- Table structure for entity_catalog
-- ----------------------------
DROP TABLE IF EXISTS `entity_catalog`;
CREATE TABLE `entity_catalog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(255) DEFAULT NULL,
  `icon` varchar(255) NOT NULL DEFAULT 'el-icon-folder-opened',
  `parent_id` int(11) DEFAULT NULL,
  `type` varchar(255) NOT NULL DEFAULT 'interface',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of entity_catalog
-- ----------------------------
BEGIN;
INSERT INTO `entity_catalog` VALUES (1, '首页目录', 'el-icon-folder-opened', NULL, 'interface');
INSERT INTO `entity_catalog` VALUES (2, '语文目录', 'el-icon-folder-opened', NULL, 'interface');
INSERT INTO `entity_catalog` VALUES (3, '数学目录', 'el-icon-folder-opened', NULL, 'interface');
INSERT INTO `entity_catalog` VALUES (4, '课程表', 'el-icon-folder-opened', 1, 'interface');
INSERT INTO `entity_catalog` VALUES (5, '课外作业', 'el-icon-folder-opened', 1, 'interface');
INSERT INTO `entity_catalog` VALUES (6, '学生课程表', 'el-icon-folder-opened', 4, 'interface');
INSERT INTO `entity_catalog` VALUES (7, '老师课程表', 'el-icon-folder-opened', 4, 'interface');
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
-- Table structure for entity_web_case
-- ----------------------------
DROP TABLE IF EXISTS `entity_web_case`;
CREATE TABLE `entity_web_case` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '用例名称',
  `cases` text,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `depend` varchar(255) DEFAULT NULL COMMENT '依赖的用例',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of entity_web_case
-- ----------------------------
BEGIN;
INSERT INTO `entity_web_case` VALUES (1, '全职登陆用例', '[{\"type\":\"page\",\"id\":1,\"index\":1},{\"type\":\"element\",\"id\":1,\"index\":2},{\"type\":\"element\",\"id\":2,\"index\":3},{\"type\":\"element\",\"id\":3,\"index\":4},{\"type\":\"element\",\"id\":4,\"index\":5}]', '2019-11-13 11:07:30', NULL, NULL);
INSERT INTO `entity_web_case` VALUES (2, '搜索功能', '[{\"type\":\"element\",\"id\":5,\"index\":1},{\"type\":\"assert\",\"id\":1,\"index\":2},{\"type\":\"element\",\"id\":6,\"index\":3}]', '2019-11-13 14:36:52', NULL, '[{\"index\":1,\"id\":1}]');
INSERT INTO `entity_web_case` VALUES (3, '进入校园招聘', '[{\"type\":\"element\",\"id\":7,\"index\":1},{\"type\":\"assert\",\"id\":2,\"index\":2},{\"type\":\"element\",\"id\":8,\"index\":3}]', '2019-11-13 18:04:33', NULL, '[{\"index\":1,\"id\":2}]');
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

SET FOREIGN_KEY_CHECKS = 1;
