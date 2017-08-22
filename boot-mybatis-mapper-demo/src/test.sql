/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50634
Source Host           : 127.0.0.1:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50634
File Encoding         : 65001

Date: 2017-06-22 14:45:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'p1', '18');
INSERT INTO `user` VALUES ('2', 'p2', '19');
INSERT INTO `user` VALUES ('3', 'p3', '20');
INSERT INTO `user` VALUES ('4', 'p4', '21');
INSERT INTO `user` VALUES ('5', 'p5', '22');
INSERT INTO `user` VALUES ('6', 'p6', '23');
