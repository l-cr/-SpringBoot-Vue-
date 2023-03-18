/*
 Navicat Premium Data Transfer

 Source Server         : windows
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : library

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 16/04/2021 20:15:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '管理员编号',
  `username` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', 'admin');

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `isbn` varchar(13) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图书号',
  `book_name` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书名',
  `author` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者',
  `publisher` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出版社',
  `intro` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简介',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '是否借阅（已借阅1，未借阅0）',
  PRIMARY KEY (`isbn`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '图书表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('12367812368', '三国', 'xxx', 'xxx', 'xxx', '0');
INSERT INTO `book` VALUES ('1238921739798', '红楼梦1', 'xxx', 'xxx', 'xxx', '0');
INSERT INTO `book` VALUES ('93247324797', '西游记', 'xxx', 'xxx', 'xxx', '0');
INSERT INTO `book` VALUES ('9787115527929', 'Spring实战 第5版', '克雷格·沃斯', '人民邮电出版社', '本书是一本经典而实用的畅销Spring 学习指南。 第5 版涵盖了Spring 5.0 和Spring Boot 2.0 里程碑式的更新。全书分为5 个部分，共19 章。*1 部分（*1～5 章）涵盖了构建Spring 应用的基础话题。*2 部分（第6～9章）讨论如何将Spring 应用与其他应用进行集成。第3 部分（*10～12 章）探讨Spring对反应式编程提供的全新支持。第4 部分（*13～15 章）拆分单体应用模型，介绍SpringCloud 和微服务开发。第5 部分（*16～19 章）讨论如何为应用投入生产环境做准备以及如何进行部署。 本书既适合刚开始学习Spring Boot 和Spring 框架的Java 开发人员快速上手，也适合经验丰富的Spring 开发人员学习Spring 的新特性，尤其适用于企业级Java 开发人员。', '1');
INSERT INTO `book` VALUES ('9787121360398', 'Spring Boot编程思想(核心篇)', '小马哥', '电子工业出版社', '本书是《Spring Boot 编程思想》的核心篇，开篇总览Spring Boot核心特性，接着讨论自动装配（Auto-Configuration）与SpringApplication。全书的讨论以Spring Boot为中心，议题发散至Spring技术栈、JSR及Java。希望透过全局的视角，帮助读者了解Spring Boot变迁的历程；经过多方的比较，帮助读者理解Spring Boot特性的原理；整合标准的规范，帮助读者掌握Spring Boot设计的哲学。', '1');
INSERT INTO `book` VALUES ('9787121386251', 'Spring Cloud微服务架构实战派', '龙中华', '电子工业出版社', '本书针对Spring Cloud 2.0及以上版本。在编写过程中，不仅考虑到企业任职所需的技能，还考虑到求职面试时可能会遇到的知识点。本书采用“知识点+实例”形式编写，共有“39个基于知识点的实例 + 1个综合性项目”，深入讲解了Spring Cloud的各类组件、微服务架构的解决方案和开发实践，以及容器、Kubernetes和Jenkins等DevOps（开发运维一体化）相关知识。本书的主脉络是：是什么，为什么用，怎样用，为什么要这样用，如何用得更好，有什么*佳的实践。', '1');

-- ----------------------------
-- Table structure for lend_record
-- ----------------------------
DROP TABLE IF EXISTS `lend_record`;
CREATE TABLE `lend_record`  (
  `reader_id` bigint(20) NOT NULL COMMENT '读者号',
  `isbn` varchar(13) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图书号',
  `lend_date` datetime NULL DEFAULT NULL COMMENT '借阅日期',
  `return_date` datetime NULL DEFAULT NULL COMMENT '归还日期',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '是否归还（未归还0，已归还1）'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '借阅记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lend_record
-- ----------------------------
INSERT INTO `lend_record` VALUES (1, '9787121386251', '2021-04-16 18:55:00', '2021-04-16 18:58:02', '0');
INSERT INTO `lend_record` VALUES (1, '12367812368', '2021-04-16 19:22:59', '2021-04-16 19:23:12', '1');
INSERT INTO `lend_record` VALUES (1, '1238921739798', '2021-04-16 19:23:19', '2021-04-16 19:26:33', '1');

-- ----------------------------
-- Table structure for reader
-- ----------------------------
DROP TABLE IF EXISTS `reader`;
CREATE TABLE `reader`  (
  `reader_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '读者号',
  `username` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `password` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `gender` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别（0男，1女，2未知）',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话号码',
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`reader_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '读者表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of reader
-- ----------------------------
INSERT INTO `reader` VALUES (1, 'reader', 'reader', '1', '18700665543', '测试地址');
INSERT INTO `reader` VALUES (2, '张山', '123456', '0', '13012345678', '江西省南昌市青山湖区');
INSERT INTO `reader` VALUES (3, '李思', '123456', '1', '13027240211', '江西省南昌市红谷滩区');

SET FOREIGN_KEY_CHECKS = 1;
