/*
 Navicat Premium Data Transfer

 Source Server         : local-MySQL
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : course_selection

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 24/09/2020 12:55:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_category`;
CREATE TABLE `tb_category` (
  `id` int(6) NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `name` varchar(30) DEFAULT NULL COMMENT '课程类别名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='课程类别';

-- ----------------------------
-- Table structure for tb_course
-- ----------------------------
DROP TABLE IF EXISTS `tb_course`;
CREATE TABLE `tb_course` (
  `id` varchar(15) NOT NULL COMMENT '选修的班级号',
  `name` varchar(50) NOT NULL COMMENT '选修课名称',
  `term` int(2) unsigned DEFAULT '0' COMMENT '学期标识',
  `credit` int(2) NOT NULL COMMENT '学分',
  `time` varchar(50) DEFAULT NULL COMMENT '上课具体时间',
  `duration_start` int(2) DEFAULT NULL COMMENT '开始的周数',
  `duration_end` int(2) DEFAULT NULL COMMENT '结束的周数',
  `place` varchar(50) DEFAULT NULL COMMENT '上课地点',
  `online` int(1) DEFAULT NULL COMMENT '是否是网课，0:是， 1:不是',
  `teacher_id` varchar(12) DEFAULT NULL COMMENT '上课老师的工号',
  `teacher_name` varchar(30) DEFAULT NULL COMMENT '上课老师的姓名，冗余字段',
  `category_id` int(2) DEFAULT NULL COMMENT '课程类别Id',
  `stock` int(3) NOT NULL COMMENT '课程剩余数量',
  `total` int(3) NOT NULL COMMENT '课程总数',
  PRIMARY KEY (`id`),
  KEY `idx_term_online` (`term`,`online`) COMMENT '学期与是否线上课索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='选修课表';

-- ----------------------------
-- Table structure for tb_curr_term
-- ----------------------------
DROP TABLE IF EXISTS `tb_curr_term`;
CREATE TABLE `tb_curr_term` (
  `term` int(2) NOT NULL COMMENT '当前学期',
  PRIMARY KEY (`term`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tb_selection
-- ----------------------------
DROP TABLE IF EXISTS `tb_selection`;
CREATE TABLE `tb_selection` (
  `student_id` varchar(12) NOT NULL COMMENT '学生ID',
  `course_id` varchar(15) NOT NULL COMMENT '课程ID',
  `term` int(2) unsigned DEFAULT '0' COMMENT '哪一个学期',
  `create_time` datetime(1) DEFAULT NULL COMMENT '选课时间',
  PRIMARY KEY (`student_id`,`course_id`),
  KEY `idx_stuId` (`student_id`) COMMENT '以学生ID为索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='选课情况';

-- ----------------------------
-- Table structure for tb_student
-- ----------------------------
DROP TABLE IF EXISTS `tb_student`;
CREATE TABLE `tb_student` (
  `id` varchar(12) NOT NULL COMMENT '学生表ID，学号',
  `name` varchar(30) DEFAULT NULL COMMENT '学生姓名',
  `password` varchar(20) DEFAULT NULL COMMENT '密码',
  `sex` int(1) DEFAULT NULL COMMENT '0:女 1:男',
  `id_number` varchar(18) DEFAULT NULL COMMENT '居民身份证',
  `college` varchar(30) DEFAULT NULL COMMENT '学院/系',
  `major` varchar(30) DEFAULT NULL COMMENT '专业',
  `class` varchar(30) DEFAULT NULL COMMENT '专业班级',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='学生表';

-- ----------------------------
-- Table structure for tb_teacher
-- ----------------------------
DROP TABLE IF EXISTS `tb_teacher`;
CREATE TABLE `tb_teacher` (
  `id` varchar(15) NOT NULL COMMENT '教师表ID',
  `name` varchar(30) DEFAULT NULL COMMENT '教师名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='教师表';

SET FOREIGN_KEY_CHECKS = 1;
