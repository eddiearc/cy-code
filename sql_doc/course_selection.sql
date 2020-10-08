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

 Date: 08/10/2020 09:35:17
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
-- Records of tb_course
-- ----------------------------
BEGIN;
INSERT INTO `tb_course` VALUES ('1', 'Spencer', 0, 2, NULL, 1, 3, 'Lazzy', 1, '1', '羽彤', NULL, 97, 97);
INSERT INTO `tb_course` VALUES ('10', 'Janet', 0, 1, NULL, 8, 10, 'Tazzy', 1, '10', '彦军', NULL, 95, 95);
INSERT INTO `tb_course` VALUES ('100', 'Jonas', 0, 1, NULL, 5, 14, 'Oyonder', 1, '100', '羽彤', NULL, 86, 86);
INSERT INTO `tb_course` VALUES ('11', 'Pat', 0, 3, NULL, 4, 13, 'Mycat', 1, '11', '志宸', NULL, 91, 91);
INSERT INTO `tb_course` VALUES ('12', 'Fidela', 0, 2, NULL, 4, 11, 'Vimbo', 0, '12', '伟宸', NULL, 86, 86);
INSERT INTO `tb_course` VALUES ('123', '分布式系统', 0, 3, '周三3-4', 1, 16, '13-301', 1, '123', 'eddie', 1, 100, 100);
INSERT INTO `tb_course` VALUES ('13', 'Randie', 0, 1, NULL, 2, 9, 'Zoombeat', 1, '13', '依娜', NULL, 90, 90);
INSERT INTO `tb_course` VALUES ('14', 'Mychal', 0, 2, NULL, 6, 2, 'Miboo', 0, '14', '昕磊', NULL, 83, 83);
INSERT INTO `tb_course` VALUES ('15', 'Margarethe', 0, 2, NULL, 1, 4, 'Thoughtsphere', 0, '15', '雪怡', NULL, 95, 95);
INSERT INTO `tb_course` VALUES ('16', 'Christin', 0, 1, NULL, 2, 13, 'Feedbug', 1, '16', '远帆', NULL, 90, 90);
INSERT INTO `tb_course` VALUES ('17', 'Analiese', 0, 1, NULL, 1, 10, 'Tagcat', 0, '17', '梓焓', NULL, 82, 82);
INSERT INTO `tb_course` VALUES ('18', 'Angelita', 0, 3, NULL, 3, 13, 'Skilith', 1, '18', '泽瀚', NULL, 82, 82);
INSERT INTO `tb_course` VALUES ('19', 'Sebastian', 0, 3, NULL, 8, 16, 'Brainverse', 1, '19', '琪煜', NULL, 89, 89);
INSERT INTO `tb_course` VALUES ('2', 'Reagen', 0, 3, NULL, 3, 13, 'Browsetype', 1, '2', '浩成', NULL, 80, 80);
INSERT INTO `tb_course` VALUES ('20', 'Adrianne', 0, 1, NULL, 5, 16, 'Zoombeat', 0, '20', '秩选', NULL, 98, 98);
INSERT INTO `tb_course` VALUES ('21', 'Bing', 0, 1, NULL, 4, 7, 'Dabfeed', 0, '21', '瀚聪', NULL, 80, 80);
INSERT INTO `tb_course` VALUES ('22', 'Dorian', 0, 1, NULL, 4, 4, 'Buzzster', 0, '22', '欣妍', NULL, 96, 96);
INSERT INTO `tb_course` VALUES ('23', 'Emmeline', 0, 2, NULL, 4, 15, 'Kayveo', 1, '23', '银含', NULL, 82, 82);
INSERT INTO `tb_course` VALUES ('24', 'Sky', 0, 3, NULL, 8, 6, 'Cogilith', 1, '24', '彦军', NULL, 97, 97);
INSERT INTO `tb_course` VALUES ('25', 'Selle', 0, 1, NULL, 6, 15, 'Yodo', 0, '25', '思翰', NULL, 85, 85);
INSERT INTO `tb_course` VALUES ('26', 'Biron', 0, 1, NULL, 5, 8, 'Yakidoo', 0, '26', '昱漳', NULL, 81, 81);
INSERT INTO `tb_course` VALUES ('27', 'Korney', 0, 1, NULL, 8, 4, 'Buzzbean', 0, '27', '剑波', NULL, 99, 99);
INSERT INTO `tb_course` VALUES ('28', 'Ladonna', 0, 2, NULL, 6, 6, 'Rhynoodle', 0, '28', '彦军', NULL, 82, 82);
INSERT INTO `tb_course` VALUES ('29', 'Nickie', 0, 2, NULL, 8, 5, 'Gevee', 1, '29', '展博', NULL, 84, 84);
INSERT INTO `tb_course` VALUES ('3', 'Prescott', 0, 1, NULL, 6, 13, 'Tagfeed', 1, '3', '崇杉', NULL, 84, 84);
INSERT INTO `tb_course` VALUES ('30', 'Eustace', 0, 2, NULL, 7, 16, 'Midel', 0, '30', '辰华', NULL, 84, 84);
INSERT INTO `tb_course` VALUES ('31', 'Krishnah', 0, 1, NULL, 3, 17, 'Realpoint', 0, '31', '清凌', NULL, 94, 94);
INSERT INTO `tb_course` VALUES ('32', 'Rooney', 0, 2, NULL, 8, 11, 'Yodoo', 0, '32', '瀚聪', NULL, 86, 86);
INSERT INTO `tb_course` VALUES ('33', 'Jacquelyn', 0, 1, NULL, 5, 10, 'Oba', 1, '33', '松源', NULL, 92, 92);
INSERT INTO `tb_course` VALUES ('34', 'Corey', 0, 2, NULL, 5, 16, 'Podcat', 1, '34', '婧琪', NULL, 98, 98);
INSERT INTO `tb_course` VALUES ('35', 'Bell', 0, 2, NULL, 1, 13, 'Skimia', 1, '35', '清凌', NULL, 91, 91);
INSERT INTO `tb_course` VALUES ('36', 'Elsey', 0, 1, NULL, 4, 5, 'Youopia', 0, '36', '彤雨', NULL, 81, 81);
INSERT INTO `tb_course` VALUES ('37', 'Domeniga', 0, 3, NULL, 2, 7, 'Feednation', 1, '37', '军卿', NULL, 84, 84);
INSERT INTO `tb_course` VALUES ('38', 'Mary', 0, 2, NULL, 3, 10, 'Zoomzone', 1, '38', '海程', NULL, 85, 85);
INSERT INTO `tb_course` VALUES ('39', 'Hortensia', 0, 2, NULL, 3, 11, 'Lazz', 1, '39', '雪怡', NULL, 86, 86);
INSERT INTO `tb_course` VALUES ('4', 'Lyman', 0, 3, NULL, 8, 1, 'Katz', 0, '4', '博裕', NULL, 94, 94);
INSERT INTO `tb_course` VALUES ('40', 'Stacia', 0, 3, NULL, 2, 15, 'Gabtype', 1, '40', '思宏', NULL, 80, 80);
INSERT INTO `tb_course` VALUES ('41', 'Garwood', 0, 2, NULL, 6, 1, 'Edgeify', 0, '41', '娅楠', NULL, 80, 80);
INSERT INTO `tb_course` VALUES ('42', 'Audie', 0, 3, NULL, 8, 2, 'LiveZ', 1, '42', '品逸', NULL, 98, 98);
INSERT INTO `tb_course` VALUES ('43', 'Pippy', 0, 2, NULL, 6, 9, 'Ailane', 0, '43', '茹雪', NULL, 93, 93);
INSERT INTO `tb_course` VALUES ('44', 'Alaine', 0, 2, NULL, 6, 1, 'Oyoyo', 0, '44', '宸瑜', NULL, 86, 86);
INSERT INTO `tb_course` VALUES ('45', 'Ado', 0, 3, NULL, 6, 16, 'Lajo', 1, '45', '唯枫', NULL, 96, 96);
INSERT INTO `tb_course` VALUES ('46', 'Matilde', 0, 3, NULL, 5, 16, 'Buzzshare', 1, '46', '梦洁', NULL, 85, 85);
INSERT INTO `tb_course` VALUES ('47', 'Giusto', 0, 3, NULL, 8, 14, 'Browsedrive', 1, '47', '烨伟', NULL, 91, 91);
INSERT INTO `tb_course` VALUES ('48', 'Virgilio', 0, 3, NULL, 6, 13, 'Meetz', 0, '48', '彦军', NULL, 99, 99);
INSERT INTO `tb_course` VALUES ('49', 'Cynthy', 0, 1, NULL, 2, 8, 'Meetz', 0, '49', '轩硕', NULL, 99, 99);
INSERT INTO `tb_course` VALUES ('5', 'Marney', 0, 2, NULL, 5, 7, 'Trunyx', 1, '5', '云哲', NULL, 85, 85);
INSERT INTO `tb_course` VALUES ('50', 'Pam', 0, 2, NULL, 4, 14, 'Dabtype', 0, '50', '梓焓', NULL, 96, 96);
INSERT INTO `tb_course` VALUES ('51', 'Hilario', 0, 1, NULL, 2, 3, 'Quaxo', 0, '51', '泰霖', NULL, 86, 86);
INSERT INTO `tb_course` VALUES ('52', 'Burt', 0, 2, NULL, 4, 3, 'Lajo', 1, '52', '昕磊', NULL, 94, 94);
INSERT INTO `tb_course` VALUES ('53', 'Miran', 0, 2, NULL, 3, 4, 'Jaxbean', 1, '53', '琪煜', NULL, 81, 81);
INSERT INTO `tb_course` VALUES ('54', 'Honoria', 0, 2, NULL, 1, 1, 'Lazz', 1, '54', '丰逸', NULL, 98, 98);
INSERT INTO `tb_course` VALUES ('55', 'Miles', 0, 1, NULL, 1, 18, 'Skyble', 0, '55', '睿杰', NULL, 89, 89);
INSERT INTO `tb_course` VALUES ('56', 'Helen-elizabeth', 0, 1, NULL, 7, 3, 'Mynte', 1, '56', '昕磊', NULL, 86, 86);
INSERT INTO `tb_course` VALUES ('57', 'Nadean', 0, 3, NULL, 6, 9, 'Divape', 0, '57', '璟雯', NULL, 100, 100);
INSERT INTO `tb_course` VALUES ('58', 'Marlyn', 0, 2, NULL, 1, 15, 'Tekfly', 1, '58', '远帆', NULL, 84, 84);
INSERT INTO `tb_course` VALUES ('59', 'Jacenta', 0, 2, NULL, 7, 1, 'Bluezoom', 1, '59', '银含', NULL, 87, 87);
INSERT INTO `tb_course` VALUES ('6', 'Jyoti', 0, 2, NULL, 1, 11, 'Bluezoom', 0, '6', '俞凯', NULL, 88, 88);
INSERT INTO `tb_course` VALUES ('60', 'Colby', 0, 2, NULL, 6, 10, 'Avamba', 1, '60', '晓烽', NULL, 80, 80);
INSERT INTO `tb_course` VALUES ('61', 'Temp', 0, 1, NULL, 7, 18, 'Quatz', 0, '61', '思宏', NULL, 93, 93);
INSERT INTO `tb_course` VALUES ('62', 'Nina', 0, 2, NULL, 1, 9, 'LiveZ', 1, '62', '云哲', NULL, 98, 98);
INSERT INTO `tb_course` VALUES ('63', 'Trstram', 0, 3, NULL, 8, 6, 'Digitube', 1, '63', '昕磊', NULL, 98, 98);
INSERT INTO `tb_course` VALUES ('64', 'Reube', 0, 3, NULL, 8, 6, 'Voonte', 0, '64', '睿杰', NULL, 98, 98);
INSERT INTO `tb_course` VALUES ('65', 'Radcliffe', 0, 2, NULL, 6, 4, 'Edgeclub', 1, '65', '梦洁', NULL, 80, 80);
INSERT INTO `tb_course` VALUES ('66', 'Charleen', 0, 3, NULL, 2, 5, 'Rhycero', 1, '66', '博豪', NULL, 98, 98);
INSERT INTO `tb_course` VALUES ('67', 'Emera', 0, 2, NULL, 4, 7, 'Topicblab', 0, '67', '梓彤', NULL, 89, 89);
INSERT INTO `tb_course` VALUES ('68', 'Laurella', 0, 2, NULL, 8, 16, 'InnoZ', 0, '68', '尹智', NULL, 94, 94);
INSERT INTO `tb_course` VALUES ('69', 'Deloria', 0, 3, NULL, 3, 13, 'Devify', 1, '69', '晧宇', NULL, 96, 96);
INSERT INTO `tb_course` VALUES ('7', 'Ezra', 0, 2, NULL, 5, 2, 'Edgeclub', 0, '7', '哲恒', NULL, 85, 85);
INSERT INTO `tb_course` VALUES ('70', 'Lorrie', 0, 2, NULL, 2, 4, 'Fadeo', 1, '70', '睿杰', NULL, 94, 94);
INSERT INTO `tb_course` VALUES ('71', 'George', 0, 2, NULL, 5, 17, 'Edgeclub', 0, '71', '博裕', NULL, 98, 98);
INSERT INTO `tb_course` VALUES ('72', 'Enoch', 0, 3, NULL, 3, 9, 'Feedmix', 0, '72', '云哲', NULL, 99, 99);
INSERT INTO `tb_course` VALUES ('73', 'Trude', 0, 3, NULL, 2, 5, 'Oyondu', 0, '73', '宇涵', NULL, 80, 80);
INSERT INTO `tb_course` VALUES ('74', 'Janaye', 0, 3, NULL, 7, 6, 'Babbleopia', 0, '74', '辰华', NULL, 81, 81);
INSERT INTO `tb_course` VALUES ('75', 'Ferrel', 0, 3, NULL, 8, 12, 'Yodoo', 1, '75', '辰华', NULL, 96, 96);
INSERT INTO `tb_course` VALUES ('76', 'Hesther', 0, 1, NULL, 6, 5, 'Eabox', 0, '76', '思宏', NULL, 82, 82);
INSERT INTO `tb_course` VALUES ('77', 'Lyndsie', 0, 1, NULL, 8, 15, 'Kayveo', 1, '77', '军卿', NULL, 81, 81);
INSERT INTO `tb_course` VALUES ('78', 'Trude', 0, 2, NULL, 7, 6, 'Jayo', 1, '78', '娅楠', NULL, 87, 87);
INSERT INTO `tb_course` VALUES ('79', 'Devin', 0, 3, NULL, 4, 17, 'Jaxspan', 1, '79', '可馨', NULL, 90, 90);
INSERT INTO `tb_course` VALUES ('8', 'Donny', 0, 1, NULL, 4, 12, 'Ozu', 0, '8', '睿杰', NULL, 80, 80);
INSERT INTO `tb_course` VALUES ('80', 'Derwin', 0, 1, NULL, 8, 7, 'Brainverse', 0, '80', '彦歆', NULL, 84, 84);
INSERT INTO `tb_course` VALUES ('81', 'Erek', 0, 1, NULL, 8, 16, 'Quimba', 0, '81', '远帆', NULL, 93, 93);
INSERT INTO `tb_course` VALUES ('82', 'Rosana', 0, 1, NULL, 6, 7, 'Chatterbridge', 1, '82', '崇杉', NULL, 92, 92);
INSERT INTO `tb_course` VALUES ('83', 'Trude', 0, 3, NULL, 8, 3, 'Gevee', 1, '83', '宇涵', NULL, 89, 89);
INSERT INTO `tb_course` VALUES ('84', 'Slade', 0, 3, NULL, 4, 11, 'Quire', 1, '84', '慧妍', NULL, 93, 93);
INSERT INTO `tb_course` VALUES ('85', 'Kenon', 0, 2, NULL, 5, 14, 'Blogpad', 0, '85', '云哲', NULL, 98, 98);
INSERT INTO `tb_course` VALUES ('86', 'Emelyne', 0, 1, NULL, 8, 9, 'Skinte', 0, '86', '明美', NULL, 82, 82);
INSERT INTO `tb_course` VALUES ('87', 'Benedict', 0, 3, NULL, 8, 8, 'Gabcube', 1, '87', '军卿', NULL, 96, 96);
INSERT INTO `tb_course` VALUES ('88', 'Celine', 0, 3, NULL, 6, 6, 'Rhybox', 0, '88', '尚贤', NULL, 80, 80);
INSERT INTO `tb_course` VALUES ('89', 'Morse', 0, 2, NULL, 3, 17, 'Mita', 1, '89', '冠希', NULL, 98, 98);
INSERT INTO `tb_course` VALUES ('9', 'Elfrieda', 0, 3, NULL, 1, 10, 'Rhynoodle', 1, '9', '昱漳', NULL, 95, 95);
INSERT INTO `tb_course` VALUES ('90', 'Tabitha', 0, 1, NULL, 8, 9, 'Aibox', 1, '90', '远帆', NULL, 93, 93);
INSERT INTO `tb_course` VALUES ('91', 'Aubert', 0, 1, NULL, 5, 14, 'Katz', 0, '91', '晧宇', NULL, 82, 82);
INSERT INTO `tb_course` VALUES ('92', 'Meta', 0, 1, NULL, 3, 14, 'Yodel', 1, '92', '琪煜', NULL, 81, 81);
INSERT INTO `tb_course` VALUES ('93', 'Aila', 0, 2, NULL, 5, 12, 'Riffwire', 0, '93', '雪丽', NULL, 80, 80);
INSERT INTO `tb_course` VALUES ('94', 'Ruddy', 0, 2, NULL, 3, 7, 'Avamm', 0, '94', '梓彤', NULL, 97, 97);
INSERT INTO `tb_course` VALUES ('95', 'Valentine', 0, 1, NULL, 3, 13, 'Lazz', 0, '95', '松源', NULL, 92, 92);
INSERT INTO `tb_course` VALUES ('96', 'Sammie', 0, 2, NULL, 8, 11, 'Vinte', 0, '96', '彤雨', NULL, 82, 82);
INSERT INTO `tb_course` VALUES ('97', 'Wayne', 0, 1, NULL, 5, 15, 'Omba', 0, '97', '永鑫', NULL, 81, 81);
INSERT INTO `tb_course` VALUES ('98', 'Reilly', 0, 3, NULL, 4, 7, 'Zoomcast', 0, '98', '哲恒', NULL, 83, 83);
INSERT INTO `tb_course` VALUES ('99', 'Humphrey', 0, 2, NULL, 6, 18, 'Jabberstorm', 0, '99', '冠希', NULL, 80, 80);
COMMIT;

-- ----------------------------
-- Table structure for tb_curr_term
-- ----------------------------
DROP TABLE IF EXISTS `tb_curr_term`;
CREATE TABLE `tb_curr_term` (
  `term` int(2) NOT NULL COMMENT '当前学期',
  PRIMARY KEY (`term`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_curr_term
-- ----------------------------
BEGIN;
INSERT INTO `tb_curr_term` VALUES (0);
COMMIT;

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
-- Records of tb_selection
-- ----------------------------
BEGIN;
INSERT INTO `tb_selection` VALUES ('201841054085', '1', 0, '2020-09-29 21:09:36.3');
INSERT INTO `tb_selection` VALUES ('201841054085', '5', 0, '2020-09-29 21:10:18.4');
INSERT INTO `tb_selection` VALUES ('201841054085', '6', 0, '2020-09-29 21:10:22.2');
COMMIT;

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

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` varchar(16) NOT NULL COMMENT '学生ID、老师ID、管理员ID',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `role` int(2) NOT NULL COMMENT '角色 0:管理员 1:学生 2:老师',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
BEGIN;
INSERT INTO `tb_user` VALUES ('201841054085', '$2a$10$knsZf8BvaEnMrj.QREuH2egjGogvZdwSgZ1Kb6sxptcgCNnisSOVC', 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
