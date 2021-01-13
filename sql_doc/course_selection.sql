/*
 Navicat Premium Data Transfer

 Source Server         : anxu
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : localhost:3306
 Source Schema         : course_selection

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 13/01/2021 10:53:25
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='课程类别';

-- ----------------------------
-- Records of tb_category
-- ----------------------------
BEGIN;
INSERT INTO `tb_category` VALUES (1, '自然科学');
INSERT INTO `tb_category` VALUES (2, '体育艺术');
INSERT INTO `tb_category` VALUES (3, '创新创业');
INSERT INTO `tb_category` VALUES (4, '计算机选修');
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
INSERT INTO `tb_course` VALUES ('1', 'Spencer', 0, 3, '周三3-4', 1, 5, 'Lazzy', 1, '1', '羽彤', 1, 97, 97);
INSERT INTO `tb_course` VALUES ('10', 'Janet', 0, 1, '周五7-8', 8, 10, 'Tazzy', 1, '10', '彦军', 3, 95, 95);
INSERT INTO `tb_course` VALUES ('100', 'Jonas', 0, 1, '周五7-8', 5, 14, 'Oyonder', 1, '1', '羽彤', 2, 86, 86);
INSERT INTO `tb_course` VALUES ('11', 'Pat', 0, 3, '周三3-4', 4, 13, 'Mycat', 1, '10', '彦军', 4, 91, 91);
INSERT INTO `tb_course` VALUES ('12', 'Fidela', 0, 2, '周五7-8', 4, 11, 'Vimbo', 0, '10', '彦军', 4, 86, 86);
INSERT INTO `tb_course` VALUES ('123', '分布式系统', 0, 3, '周三3-4', 1, 16, '13-301', 1, '123', 'eddie', 1, 100, 100);
INSERT INTO `tb_course` VALUES ('13', 'Randie', 0, 1, '周三3-4', 2, 9, 'Zoombeat', 1, '13', '美娜', 2, 90, 90);
INSERT INTO `tb_course` VALUES ('14', 'Mychal', 0, 2, '周五7-8', 6, 2, 'Miboo', 0, '14', '昕磊', 2, 83, 83);
INSERT INTO `tb_course` VALUES ('15', 'Margarethe', 0, 2, '周五7-8', 1, 4, 'Thoughtsphere', 0, '15', '雪怡', 3, 95, 95);
INSERT INTO `tb_course` VALUES ('16', 'Christin', 0, 1, '周五7-8', 2, 13, 'Feedbug', 1, '1', '羽彤', 3, 90, 90);
INSERT INTO `tb_course` VALUES ('17', 'Analiese', 0, 1, '周五7-8', 1, 10, 'Tagcat', 0, '1', '羽彤', 4, 82, 82);
INSERT INTO `tb_course` VALUES ('18', 'Angelita', 0, 3, '周三3-4', 3, 13, 'Skilith', 1, '1', '羽彤', 1, 82, 82);
INSERT INTO `tb_course` VALUES ('19', 'Sebastian', 0, 3, '周五7-8', 8, 16, 'Brainverse', 1, '19', '琪煜', 4, 89, 89);
INSERT INTO `tb_course` VALUES ('2', 'Reagen', 0, 3, '周三3-4', 3, 13, 'Browsetype', 1, '2', '浩成', 1, 80, 80);
INSERT INTO `tb_course` VALUES ('20', 'Adrianne', 0, 1, '周五7-8', 5, 16, 'Zoombeat', 0, '1', '羽彤', 1, 98, 98);
INSERT INTO `tb_course` VALUES ('21', 'Bing', 0, 1, '周三3-4', 4, 7, 'Dabfeed', 0, '10', '彦军', 3, 80, 80);
INSERT INTO `tb_course` VALUES ('22', 'Dorian', 0, 1, '周五7-8', 4, 4, 'Buzzster', 0, '10', '彦军', 3, 96, 96);
INSERT INTO `tb_course` VALUES ('23', 'Emmeline', 0, 2, '周五7-8', 4, 15, 'Kayveo', 1, '10', '彦军', 3, 82, 82);
INSERT INTO `tb_course` VALUES ('24', 'Sky', 0, 3, '周五7-8', 8, 6, 'Cogilith', 1, '10', '彦军', 1, 97, 97);
INSERT INTO `tb_course` VALUES ('25', 'Selle', 0, 1, '周五7-8', 6, 15, 'Yodo', 0, '25', '思翰', 2, 85, 85);
INSERT INTO `tb_course` VALUES ('26', 'Biron', 0, 1, '周三3-4', 5, 8, 'Yakidoo', 0, '26', '昱漳', 1, 81, 81);
INSERT INTO `tb_course` VALUES ('27', 'Korney', 0, 1, '周五7-8', 8, 4, 'Buzzbean', 0, '1', '羽彤', 1, 99, 99);
INSERT INTO `tb_course` VALUES ('28', 'Ladonna', 0, 2, '周三3-4', 6, 6, 'Rhynoodle', 0, '10', '彦军', 1, 82, 82);
INSERT INTO `tb_course` VALUES ('29', 'Nickie', 0, 2, '周五7-8', 8, 5, 'Gevee', 1, '10', '彦军', 2, 84, 84);
INSERT INTO `tb_course` VALUES ('3', 'Prescott', 0, 1, '周五7-8', 6, 13, 'Tagfeed', 1, '3', '崇杉', 2, 84, 84);
INSERT INTO `tb_course` VALUES ('30', 'Eustace', 0, 2, '周五7-8', 7, 16, 'Midel', 0, '30', '辰华', 3, 84, 84);
INSERT INTO `tb_course` VALUES ('31', 'Krishnah', 0, 1, '周三3-4', 3, 17, 'Realpoint', 0, '10', '彦军', 3, 94, 94);
INSERT INTO `tb_course` VALUES ('32', 'Rooney', 0, 2, '周五7-8', 8, 11, 'Yodoo', 0, '32', '瀚聪', 1, 86, 86);
INSERT INTO `tb_course` VALUES ('33', 'Jacquelyn', 0, 1, '周五7-8', 5, 10, 'Oba', 1, '1', '羽彤', 1, 92, 92);
INSERT INTO `tb_course` VALUES ('34', 'Corey', 0, 2, '周三3-4', 5, 16, 'Podcat', 1, '1', '羽彤', 1, 98, 98);
INSERT INTO `tb_course` VALUES ('35', 'Bell', 0, 2, '周五7-8', 1, 13, 'Skimia', 1, '35', '清凌', 2, 91, 91);
INSERT INTO `tb_course` VALUES ('36', 'Elsey', 0, 1, '周三3-4', 4, 5, 'Youopia', 0, '36', '彤雨', 4, 81, 81);
INSERT INTO `tb_course` VALUES ('37', 'Domeniga', 0, 3, '周五7-8', 2, 7, 'Feednation', 1, '10', '彦军', 1, 84, 84);
INSERT INTO `tb_course` VALUES ('38', 'Mary', 0, 2, '周三3-4', 3, 10, 'Zoomzone', 1, '1', '羽彤', 2, 85, 85);
INSERT INTO `tb_course` VALUES ('39', 'Hortensia', 0, 2, '周五7-8', 3, 11, 'Lazz', 1, '39', '雪怡', 4, 86, 86);
INSERT INTO `tb_course` VALUES ('4', 'Lyman', 0, 3, '周五7-8', 8, 1, 'Katz', 0, '4', '博裕', 3, 94, 94);
INSERT INTO `tb_course` VALUES ('40', 'Stacia', 0, 3, '周五7-8', 2, 15, 'Gabtype', 1, '10', '彦军', 2, 80, 80);
INSERT INTO `tb_course` VALUES ('41', 'Garwood', 0, 2, '周三3-4', 6, 1, 'Edgeify', 0, '10', '彦军', 3, 80, 80);
INSERT INTO `tb_course` VALUES ('42', 'Audie', 0, 3, '周五7-8', 8, 2, 'LiveZ', 1, '10', '彦军', 4, 98, 98);
INSERT INTO `tb_course` VALUES ('43', 'Pippy', 0, 2, '周五7-8', 6, 9, 'Ailane', 0, '43', '茹雪', 3, 93, 93);
INSERT INTO `tb_course` VALUES ('44', 'Alaine', 0, 2, '周三3-4', 6, 1, 'Oyoyo', 0, '44', '宸瑜', 2, 86, 86);
INSERT INTO `tb_course` VALUES ('45', 'Ado', 0, 3, '周三7-8', 6, 16, 'Lajo', 1, '45', '唯枫', 4, 96, 96);
INSERT INTO `tb_course` VALUES ('46', 'Matilde', 0, 3, '周三3-4', 5, 16, 'Buzzshare', 1, '46', '梦洁', 2, 85, 85);
INSERT INTO `tb_course` VALUES ('47', 'Giusto', 0, 3, '周三7-8', 8, 14, 'Browsedrive', 1, '47', '烨伟', 1, 91, 91);
INSERT INTO `tb_course` VALUES ('48', 'Virgilio', 0, 3, '周一3-4', 6, 13, 'Meetz', 0, '10', '彦军', 1, 99, 99);
INSERT INTO `tb_course` VALUES ('49', 'Cynthy', 0, 1, '周一3-4', 2, 8, 'Meetz', 0, '49', '轩硕', 1, 99, 99);
INSERT INTO `tb_course` VALUES ('5', 'Marney', 0, 2, '周一3-4', 5, 7, 'Trunyx', 1, '5', '云哲', 2, 85, 85);
INSERT INTO `tb_course` VALUES ('50', 'Pam', 0, 2, '周三3-4', 4, 14, 'Dabtype', 0, '50', '梓焓', 2, 96, 96);
INSERT INTO `tb_course` VALUES ('51', 'Hilario', 0, 1, '周一3-4', 2, 3, 'Quaxo', 0, '51', '泰霖', 1, 86, 86);
INSERT INTO `tb_course` VALUES ('52', 'Burt', 0, 2, '周一3-4', 4, 3, 'Lajo', 1, '52', '昕磊', 1, 94, 94);
INSERT INTO `tb_course` VALUES ('53', 'Miran', 0, 2, '周一3-4', 3, 4, 'Jaxbean', 1, '53', '琪煜', 1, 81, 81);
INSERT INTO `tb_course` VALUES ('54', 'Honoria', 0, 2, '周一5-6', 1, 1, 'Lazz', 1, '54', '丰逸', 1, 98, 98);
INSERT INTO `tb_course` VALUES ('55', 'Miles', 0, 1, '周一5-6', 1, 18, 'Skyble', 0, '55', '睿杰', 2, 89, 89);
INSERT INTO `tb_course` VALUES ('56', 'Helen-elizabeth', 0, 1, '周三3-4', 7, 3, 'Mynte', 1, '56', '昕磊', 1, 86, 86);
INSERT INTO `tb_course` VALUES ('57', 'Nadean', 0, 3, '周一5-6', 6, 9, 'Divape', 0, '57', '璟雯', 1, 100, 100);
INSERT INTO `tb_course` VALUES ('58', 'Marlyn', 0, 2, '周一3-4', 1, 15, 'Tekfly', 1, '58', '远帆', 3, 84, 84);
INSERT INTO `tb_course` VALUES ('59', 'Jacenta', 0, 2, '周一5-6', 7, 1, 'Bluezoom', 1, '59', '银含', 3, 87, 87);
INSERT INTO `tb_course` VALUES ('6', 'Jyoti', 0, 2, '周一3-4', 1, 11, 'Bluezoom', 0, '6', '俞凯', 1, 88, 88);
INSERT INTO `tb_course` VALUES ('60', 'Colby', 0, 2, '周一5-6', 6, 10, 'Avamba', 1, '60', '晓烽', 1, 80, 80);
INSERT INTO `tb_course` VALUES ('61', 'Temp', 0, 1, '周一3-4', 7, 18, 'Quatz', 0, '61', '思宏', 4, 93, 93);
INSERT INTO `tb_course` VALUES ('62', 'Nina', 0, 2, '周三7-8', 1, 9, 'LiveZ', 1, '62', '云哲', 4, 98, 98);
INSERT INTO `tb_course` VALUES ('63', 'Trstram', 0, 3, '周一5-6', 8, 6, 'Digitube', 1, '63', '昕磊', 4, 98, 98);
INSERT INTO `tb_course` VALUES ('64', 'Reube', 0, 3, '周五7-8', 8, 6, 'Voonte', 0, '64', '睿杰', 1, 98, 98);
INSERT INTO `tb_course` VALUES ('65', 'Radcliffe', 0, 2, '周五7-8', 6, 4, 'Edgeclub', 1, '65', '梦洁', 1, 80, 80);
INSERT INTO `tb_course` VALUES ('66', 'Charleen', 0, 3, '周一5-6', 2, 5, 'Rhycero', 1, '66', '博豪', 1, 98, 98);
INSERT INTO `tb_course` VALUES ('67', 'Emera', 0, 2, '周一5-6', 4, 7, 'Topicblab', 0, '67', '梓彤', 2, 89, 89);
INSERT INTO `tb_course` VALUES ('68', 'Laurella', 0, 2, '周五7-8', 8, 16, 'InnoZ', 0, '68', '尹智', 3, 94, 94);
INSERT INTO `tb_course` VALUES ('69', 'Deloria', 0, 3, '周五7-8', 3, 13, 'Devify', 1, '69', '晧宇', 4, 96, 96);
INSERT INTO `tb_course` VALUES ('7', 'Ezra', 0, 2, '周五7-8', 5, 2, 'Edgeclub', 0, '7', '哲恒', 1, 85, 85);
INSERT INTO `tb_course` VALUES ('70', 'Lorrie', 0, 2, '周一5-6', 2, 4, 'Fadeo', 1, '70', '睿杰', 2, 94, 94);
INSERT INTO `tb_course` VALUES ('71', 'George', 0, 2, '周一5-6', 5, 17, 'Edgeclub', 0, '71', '博裕', 3, 98, 98);
INSERT INTO `tb_course` VALUES ('72', 'Enoch', 0, 3, '周一5-6', 3, 9, 'Feedmix', 0, '72', '云哲', 1, 99, 99);
INSERT INTO `tb_course` VALUES ('73', 'Trude', 0, 3, '周一5-6', 2, 5, 'Oyondu', 0, '73', '宇涵', 4, 80, 80);
INSERT INTO `tb_course` VALUES ('74', 'Janaye', 0, 3, '周三7-8', 7, 6, 'Babbleopia', 0, '74', '辰华', 4, 81, 81);
INSERT INTO `tb_course` VALUES ('75', 'Ferrel', 0, 3, '周三7-8', 8, 12, 'Yodoo', 1, '75', '辰华', 4, 96, 96);
INSERT INTO `tb_course` VALUES ('76', 'Hesther', 0, 1, '周三7-8', 6, 5, 'Eabox', 0, '76', '思宏', 1, 82, 82);
INSERT INTO `tb_course` VALUES ('77', 'Lyndsie', 0, 1, '周三7-8', 8, 15, 'Kayveo', 1, '77', '军卿', 1, 81, 81);
INSERT INTO `tb_course` VALUES ('78', 'Trude', 0, 2, '周一5-6', 7, 6, 'Jayo', 1, '78', '娅楠', 1, 87, 87);
INSERT INTO `tb_course` VALUES ('79', 'Devin', 0, 3, '周一5-6', 4, 17, 'Jaxspan', 1, '79', '可馨', 2, 90, 90);
INSERT INTO `tb_course` VALUES ('8', 'Donny', 0, 1, '周一5-6', 4, 12, 'Ozu', 0, '8', '睿杰', 3, 80, 80);
INSERT INTO `tb_course` VALUES ('80', 'Derwin', 0, 1, '周三7-8', 8, 7, 'Brainverse', 0, '80', '彦歆', 4, 84, 84);
INSERT INTO `tb_course` VALUES ('81', 'Erek', 0, 1, '周三7-8', 8, 16, 'Quimba', 0, '81', '远帆', 4, 93, 93);
INSERT INTO `tb_course` VALUES ('82', 'Rosana', 0, 1, '周三7-8', 6, 7, 'Chatterbridge', 1, '82', '崇杉', 1, 92, 92);
INSERT INTO `tb_course` VALUES ('83', 'Trude', 0, 3, '周一5-6', 8, 3, 'Gevee', 1, '83', '宇涵', 2, 89, 89);
INSERT INTO `tb_course` VALUES ('84', 'Slade', 0, 3, '周一5-6', 4, 11, 'Quire', 1, '84', '慧妍', 3, 93, 93);
INSERT INTO `tb_course` VALUES ('85', 'Kenon', 0, 2, '周一5-6', 5, 14, 'Blogpad', 0, '85', '云哲', 1, 98, 98);
INSERT INTO `tb_course` VALUES ('86', 'Emelyne', 0, 1, '周三7-8', 8, 9, 'Skinte', 0, '86', '明美', 2, 82, 82);
INSERT INTO `tb_course` VALUES ('87', 'Benedict', 0, 3, '周三7-8', 8, 8, 'Gabcube', 1, '87', '军卿', 3, 96, 96);
INSERT INTO `tb_course` VALUES ('88', 'Celine', 0, 3, '周三7-8', 6, 6, 'Rhybox', 0, '88', '尚贤', 1, 80, 80);
INSERT INTO `tb_course` VALUES ('89', 'Morse', 0, 2, '周一5-6', 3, 17, 'Mita', 1, '89', '冠希', 2, 98, 98);
INSERT INTO `tb_course` VALUES ('9', 'Elfrieda', 0, 3, '周三7-8', 1, 10, 'Rhynoodle', 1, '9', '昱漳', 3, 95, 95);
INSERT INTO `tb_course` VALUES ('90', 'Tabitha', 0, 1, '周一5-6', 8, 9, 'Aibox', 1, '90', '远帆', 1, 93, 93);
INSERT INTO `tb_course` VALUES ('91', 'Aubert', 0, 1, '周三7-8', 5, 14, 'Katz', 0, '91', '晧宇', 3, 82, 82);
INSERT INTO `tb_course` VALUES ('92', 'Meta', 0, 1, '周一5-6', 3, 14, 'Yodel', 1, '92', '琪煜', 1, 81, 81);
INSERT INTO `tb_course` VALUES ('93', 'Aila', 0, 2, '周三7-8', 5, 12, 'Riffwire', 0, '93', '雪丽', 2, 80, 80);
INSERT INTO `tb_course` VALUES ('94', 'Ruddy', 0, 2, '周三7-8', 3, 7, 'Avamm', 0, '94', '梓彤', 3, 97, 97);
INSERT INTO `tb_course` VALUES ('95', 'Valentine', 0, 1, '周三7-8', 3, 13, 'Lazz', 0, '95', '松源', 1, 92, 92);
INSERT INTO `tb_course` VALUES ('96', 'Sammie', 0, 2, '周三7-8', 8, 11, 'Vinte', 0, '96', '彤雨', 3, 82, 82);
INSERT INTO `tb_course` VALUES ('97', 'Wayne', 0, 1, '周一5-6', 5, 15, 'Omba', 0, '97', '永鑫', 1, 81, 81);
INSERT INTO `tb_course` VALUES ('98', 'Reilly', 0, 3, '周一5-6', 4, 7, 'Zoomcast', 0, '98', '哲恒', 1, 83, 83);
INSERT INTO `tb_course` VALUES ('99', 'Humphrey', 0, 2, '周一5-6', 6, 18, 'Jabberstorm', 0, '99', '冠希', 2, 80, 80);
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
INSERT INTO `tb_selection` VALUES ('201841054083', '1', 0, '2021-01-12 16:56:28.9');
INSERT INTO `tb_selection` VALUES ('201841054083', '2', 0, '2021-01-12 16:56:30.0');
INSERT INTO `tb_selection` VALUES ('201841054083', '3', 0, '2021-01-12 16:56:30.6');
INSERT INTO `tb_selection` VALUES ('201841054083', '4', 0, '2021-01-12 15:42:08.2');
COMMIT;

-- ----------------------------
-- Table structure for tb_student
-- ----------------------------
DROP TABLE IF EXISTS `tb_student`;
CREATE TABLE `tb_student` (
  `id` varchar(12) NOT NULL COMMENT '学生表ID，学号',
  `name` varchar(30) DEFAULT NULL COMMENT '学生姓名',
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
INSERT INTO `tb_student` VALUES ('201841054082', '黄志伟', 0, '350181199910091236', '舞蹈学院', '芭蕾舞', '芭蕾1893');
INSERT INTO `tb_student` VALUES ('201841054083', '林峰', 1, '350181199912311739', '中软国际互联网学院', '软件工程', '软件1893');
INSERT INTO `tb_student` VALUES ('201841054084', '林鑫', 1, '350181199910121567', '中软科技学院', '航空制造', '航造1893');
INSERT INTO `tb_student` VALUES ('201841054085', '林一丹', 1, '35062520000707101X', '中软国际互联网学院', '软件工程', '软件1893');
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
-- Records of tb_teacher
-- ----------------------------
BEGIN;
INSERT INTO `tb_teacher` VALUES ('1', '羽彤');
INSERT INTO `tb_teacher` VALUES ('10', '彦军');
INSERT INTO `tb_teacher` VALUES ('13', '美娜');
INSERT INTO `tb_teacher` VALUES ('2', '浩成');
INSERT INTO `tb_teacher` VALUES ('25', '思翰');
COMMIT;

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
INSERT INTO `tb_user` VALUES ('1', '$2a$10$V2jWT4Zs8xKgC2O.BcM85OhQyNkYBFtu8tcSf8JPWcQpJZzvEPM6m', 2);
INSERT INTO `tb_user` VALUES ('10', '$2a$10$TTL0y7MjNZn0dbDYijaGw.mBwqiqqYeZBRW/WdlpxHd7Q5cgWfeY6', 2);
INSERT INTO `tb_user` VALUES ('201841054082', '$2a$10$1Xc1daHznz.4Lh/8KdFSsO/bPDNxsk8pSfL8savS1LSq/xiV6uUZ.', 1);
INSERT INTO `tb_user` VALUES ('201841054083', '$2a$10$knsZf8BvaEnMrj.QREuH2egjGogvZdwSgZ1Kb6sxptcgCNnisSOVC', 1);
INSERT INTO `tb_user` VALUES ('201841054084', '$2a$10$PhfzMPzg0WtOxIrT25Er/uDBfFSDolW17a2iF7fOcUhTEIMXgw1D2', 1);
INSERT INTO `tb_user` VALUES ('201841054085', '$2a$10$knsZf8BvaEnMrj.QREuH2egjGogvZdwSgZ1Kb6sxptcgCNnisSOVC', 0);
INSERT INTO `tb_user` VALUES ('dev', '$2a$10$9jA96S6.QCiW41SYZUxKnOIIuN22bD20mu1ThoFeRKbQgAEf0hTTm', -1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
