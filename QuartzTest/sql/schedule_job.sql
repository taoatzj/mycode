/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50611
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50611
File Encoding         : 65001

Date: 2017-07-13 13:26:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for schedule_job
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job`;
CREATE TABLE `schedule_job` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `method_name` varchar(100) DEFAULT NULL COMMENT '方法名',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `cron_expression` varchar(100) DEFAULT NULL COMMENT 'cron表达式',
  `status` tinyint(4) DEFAULT NULL COMMENT '任务状态  0：正常  1：暂停',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='定时任务';

-- ----------------------------
-- Records of schedule_job
-- ----------------------------
INSERT INTO `schedule_job` VALUES ('3', 'taskTest', 'run', '', '0/2 * * * * ?', '1', '无参数测试', '2017-07-12 10:02:16');
INSERT INTO `schedule_job` VALUES ('4', 'testJob', 'test', '11111111111111', '0/2 * * * * ?', '1', '有参', '2017-07-12 18:04:11');
INSERT INTO `schedule_job` VALUES ('5', 'testJob', 'test', '12222222222', '0/3 * * * * ?', '1', '有参', '2017-07-13 09:38:28');
INSERT INTO `schedule_job` VALUES ('6', 'taskTest', 'run1', '', '0/3 * * * * ?', '1', '', '2017-07-13 11:13:59');
