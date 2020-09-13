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

 Date: 13/09/2020 14:25:58
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
-- Records of tb_category
-- ----------------------------
BEGIN;
INSERT INTO `tb_category` VALUES (1, '自然科学');
INSERT INTO `tb_category` VALUES (2, '体育艺术');
INSERT INTO `tb_category` VALUES (3, '创新创业');
COMMIT;

-- ----------------------------
-- Table structure for tb_course
-- ----------------------------
DROP TABLE IF EXISTS `tb_course`;
CREATE TABLE `tb_course` (
  `id` varchar(15) NOT NULL COMMENT '选修的班级号',
  `name` varchar(50) NOT NULL COMMENT '选修课名称',
  `term` int(2) DEFAULT NULL COMMENT '学期标识',
  `credit` int(2) NOT NULL COMMENT '学分',
  `time` varchar(50) DEFAULT NULL COMMENT '上课具体时间',
  `duration_start` int(2) DEFAULT NULL COMMENT '开始的周数',
  `duration_end` int(2) DEFAULT NULL COMMENT '结束的周数',
  `place` varchar(50) DEFAULT NULL COMMENT '上课地点',
  `online` int(1) DEFAULT NULL COMMENT '是否是网课，0:是， 1:不是',
  `teacher_id` varchar(12) DEFAULT NULL COMMENT '上课老师的工号',
  `teacher_name` varchar(30) DEFAULT NULL COMMENT '上课老师的姓名，冗余字段',
  `category_id` int(2) DEFAULT NULL COMMENT '课程类别Id',
  `count` int(3) DEFAULT NULL COMMENT '课程数',
  PRIMARY KEY (`id`),
  KEY `idx_term_online` (`term`,`online`) COMMENT '学期与是否线上课索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='选修课表';

-- ----------------------------
-- Records of tb_course
-- ----------------------------
BEGIN;
INSERT INTO `tb_course` VALUES ('123', '分布式系统', 0, 3, '周三3-4', 1, 16, '13-301', 1, '123', 'eddie', 1, 100);
COMMIT;

-- ----------------------------
-- Table structure for tb_selection
-- ----------------------------
DROP TABLE IF EXISTS `tb_selection`;
CREATE TABLE `tb_selection` (
  `id` varchar(18) NOT NULL COMMENT '选课信息ID',
  `student_id` varchar(12) NOT NULL COMMENT '学生ID',
  `course_id` varchar(15) NOT NULL COMMENT '课程ID',
  `term` int(2) DEFAULT NULL COMMENT '哪一个学期',
  `stage` int(1) DEFAULT NULL COMMENT '选课阶段',
  PRIMARY KEY (`id`),
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
-- Records of tb_student
-- ----------------------------
BEGIN;
INSERT INTO `tb_student` VALUES ('201841054085', '林一丹', '111111', 1, '35062520000707101X', '中软国际互联网学院', '软件工程', '软件1893');
COMMIT;

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
