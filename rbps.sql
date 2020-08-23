/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.5.20 : Database - rbps
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`rbps` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `rbps`;

/*Table structure for table `attachment` */

DROP TABLE IF EXISTS `attachment`;

CREATE TABLE `attachment` (
  `attachmentId` int(11) NOT NULL AUTO_INCREMENT COMMENT '附件',
  `attachmentName` varchar(100) DEFAULT NULL COMMENT '名称',
  `attachmentPath` varchar(100) DEFAULT NULL COMMENT '路径',
  `attachmentTime` varchar(30) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`attachmentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `attachment` */

/*Table structure for table `log` */

DROP TABLE IF EXISTS `log`;

CREATE TABLE `log` (
  `logId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志',
  `userName` varchar(30) DEFAULT NULL COMMENT '操作人',
  `createTime` varchar(30) DEFAULT NULL COMMENT '时间',
  `content` text COMMENT '详细',
  `operation` varchar(300) DEFAULT NULL COMMENT '操作类型（增删改）',
  `ip` varchar(60) DEFAULT NULL COMMENT 'IP地址',
  `module` varchar(40) DEFAULT NULL COMMENT '所属模块',
  PRIMARY KEY (`logId`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8 COMMENT='操作日志记录';

/*Data for the table `log` */

insert  into `log`(`logId`,`userName`,`createTime`,`content`,`operation`,`ip`,`module`) values (1,'java','2015-08-14 15:13:46',NULL,'登录','127.0.0.1',NULL),(2,'java','2015-08-14 15:19:54',NULL,'登录','127.0.0.1',NULL),(3,'java','2015-08-14 15:50:09',NULL,'登录','127.0.0.1',NULL),(4,'java','2015-08-14 15:52:08',NULL,'退出','127.0.0.1',NULL),(5,'java','2015-08-14 15:52:16',NULL,'登录','127.0.0.1',NULL),(6,'java','2015-08-14 15:55:32',NULL,'登录','127.0.0.1',NULL),(7,'java','2015-08-14 17:01:31',NULL,'登录','127.0.0.1',NULL),(8,'java','2015-08-14 17:09:07',NULL,'登录','127.0.0.1',NULL),(9,'java','2015-08-15 10:37:42',NULL,'登录','127.0.0.1',NULL),(10,'java','2015-08-15 10:38:54',NULL,'登录','127.0.0.1',NULL),(11,'java','2015-08-15 10:41:42',NULL,'登录','127.0.0.1',NULL),(12,'java','2015-08-15 11:13:03',NULL,'登录','127.0.0.1',NULL),(13,'java','2015-08-15 11:18:11',NULL,'登录','127.0.0.1',NULL),(14,'java','2015-08-15 11:28:50',NULL,'登录','127.0.0.1',NULL),(15,'java','2015-08-15 11:29:07',NULL,'登录','127.0.0.1',NULL),(16,'java','2015-08-15 11:29:17',NULL,'登录','127.0.0.1',NULL),(17,'java','2015-08-15 11:32:08',NULL,'登录','127.0.0.1',NULL),(18,'java','2015-08-15 11:32:24',NULL,'登录','127.0.0.1',NULL),(19,'java','2015-08-15 12:19:17',NULL,'登录','127.0.0.1',NULL),(20,'java','2015-08-15 12:19:41','修改Menu 属性名和值： Seq-->1   MenuId-->8   MenuName-->学生信息搜索   IconCls-->icon-1   MenuUrl-->   MenuDescription-->这个暂时没有用到。  ','修改','127.0.0.1','menu'),(21,'java','2015-08-15 12:20:02',NULL,'登录','127.0.0.1',NULL),(22,'java','2015-08-15 12:28:31',NULL,'登录','127.0.0.1',NULL),(23,'A','2015-08-15 12:29:34',NULL,'登录','127.0.0.1',NULL),(24,'A','2015-08-15 12:29:41',NULL,'退出','127.0.0.1',NULL),(25,'A','2015-08-15 12:29:41',NULL,'登录','127.0.0.1',NULL),(26,'A','2015-08-15 12:29:44',NULL,'退出','127.0.0.1',NULL),(27,'A','2015-08-15 12:29:45',NULL,'登录','127.0.0.1',NULL),(28,'A','2015-08-15 12:29:51',NULL,'退出','127.0.0.1',NULL),(29,'A','2015-08-15 12:29:51',NULL,'登录','127.0.0.1',NULL),(30,'A','2015-08-15 12:29:56',NULL,'退出','127.0.0.1',NULL),(31,'A','2015-08-15 12:29:56',NULL,'登录','127.0.0.1',NULL),(32,'A','2015-08-15 12:36:54',NULL,'登录','127.0.0.1',NULL),(33,'A','2015-08-15 12:37:14',NULL,'退出','127.0.0.1',NULL),(34,'java','2015-08-15 12:37:27',NULL,'登录','127.0.0.1',NULL),(35,'java','2015-08-15 12:37:43',NULL,'登录','127.0.0.1',NULL),(36,'java','2015-08-15 12:43:12',NULL,'登录','127.0.0.1',NULL),(37,'java','2015-08-15 12:43:25',NULL,'登录','127.0.0.1',NULL),(38,'java','2015-08-15 12:43:38',NULL,'登录','127.0.0.1',NULL),(39,'java','2015-08-15 12:44:04',NULL,'登录','127.0.0.1',NULL),(40,'java','2015-08-15 12:49:16',NULL,'登录','127.0.0.1',NULL),(41,'java','2015-08-15 12:49:37',NULL,'登录','127.0.0.1',NULL),(42,'java','2015-08-15 12:49:51',NULL,'登录','127.0.0.1',NULL),(43,'java','2015-08-15 12:50:40',NULL,'登录','127.0.0.1',NULL),(44,'java','2015-08-15 12:50:48',NULL,'登录','127.0.0.1',NULL),(45,'java','2015-08-15 12:50:52',NULL,'退出','127.0.0.1',NULL),(46,'java','2015-08-15 12:51:08',NULL,'登录','127.0.0.1',NULL),(47,'java','2015-08-15 12:51:31',NULL,'登录','127.0.0.1',NULL),(48,'java','2015-08-15 13:00:30',NULL,'登录','127.0.0.1',NULL),(49,'java','2015-08-15 13:05:08',NULL,'登录','127.0.0.1',NULL),(50,'java','2015-08-15 13:05:27',NULL,'登录','127.0.0.1',NULL),(51,'java','2015-08-15 13:05:39',NULL,'登录','127.0.0.1',NULL),(52,'java','2015-08-15 13:05:40',NULL,'登录','127.0.0.1',NULL),(53,'java','2015-08-15 13:05:47',NULL,'退出','127.0.0.1',NULL),(54,'java','2015-08-15 14:07:53',NULL,'登录','127.0.0.1',NULL),(55,'java','2015-08-15 14:08:06',NULL,'登录','127.0.0.1',NULL),(56,'java','2015-08-15 14:08:07',NULL,'登录','127.0.0.1',NULL),(57,'java','2015-08-15 14:08:26',NULL,'登录','127.0.0.1',NULL),(58,'java','2015-08-15 14:32:23',NULL,'登录','127.0.0.1',NULL),(59,'java','2015-08-15 14:32:30',NULL,'退出','127.0.0.1',NULL),(60,'java','2015-08-15 14:32:30',NULL,'登录','127.0.0.1',NULL),(61,'java','2015-08-15 14:32:30',NULL,'登录','127.0.0.1',NULL),(62,'java','2015-08-15 14:32:34',NULL,'登录','127.0.0.1',NULL),(63,'java','2015-08-15 14:32:36',NULL,'退出','127.0.0.1',NULL),(64,'java','2015-08-15 14:32:36',NULL,'登录','127.0.0.1',NULL),(65,'java','2015-08-15 14:32:36',NULL,'登录','127.0.0.1',NULL),(66,'java','2015-08-15 14:32:45',NULL,'登录','127.0.0.1',NULL),(67,'java','2015-08-15 14:32:48',NULL,'退出','127.0.0.1',NULL),(68,'java','2015-08-15 14:32:48',NULL,'登录','127.0.0.1',NULL),(69,'java','2015-08-15 14:32:48',NULL,'登录','127.0.0.1',NULL),(70,'java','2015-08-15 14:32:52',NULL,'登录','127.0.0.1',NULL),(71,'java','2015-08-15 14:32:54',NULL,'退出','127.0.0.1',NULL),(72,'java','2015-08-15 14:32:55',NULL,'登录','127.0.0.1',NULL),(73,'java','2015-08-15 14:32:55',NULL,'登录','127.0.0.1',NULL),(74,'java','2015-08-15 14:35:58',NULL,'登录','127.0.0.1',NULL);

/*Table structure for table `menu` */

DROP TABLE IF EXISTS `menu`;

CREATE TABLE `menu` (
  `menuId` int(10) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menuName` varchar(50) DEFAULT NULL COMMENT '名称',
  `menuUrl` varchar(100) DEFAULT NULL COMMENT '方法',
  `parentId` int(11) DEFAULT NULL COMMENT '父ID',
  `menuDescription` varchar(200) DEFAULT NULL COMMENT '描述',
  `state` varchar(20) DEFAULT NULL COMMENT '状态/OPEN/CLOSED',
  `iconCls` varchar(50) DEFAULT NULL COMMENT '图标',
  `seq` int(11) DEFAULT NULL COMMENT '顺序排序',
  PRIMARY KEY (`menuId`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

/*Data for the table `menu` */

insert  into `menu`(`menuId`,`menuName`,`menuUrl`,`parentId`,`menuDescription`,`state`,`iconCls`,`seq`) values (1,'某系统','',-1,'主菜单','closed','icon-home',1),(2,'系统管理','',1,'','closed','icon-permission',1),(3,'学生管理','',1,'','closed','icon-student',2),(4,'课程管理','',1,'','open','icon-course',3),(5,'菜单管理','menu/menuIndex.htm',2,'','open','icon-menuManage',4),(6,'用户管理','user/userIndex.htm',2,'','open','icon-489',2),(7,'角色管理','role/roleIndex.htm',2,'','open','icon-486',3),(8,'学生信息搜索','',3,'这个暂时没有用到。','open','icon-1',1),(15,'修改密码','',1,'','open','icon-modifyPassword',4),(16,'安全退出','',1,'','open','icon-exit',5),(17,'成绩管理','',3,'1','open','icon-3',1),(26,'日志管理','log/logIndex.htm',2,'','open','icon-513',4);

/*Table structure for table `operation` */

DROP TABLE IF EXISTS `operation`;

CREATE TABLE `operation` (
  `operationId` int(11) NOT NULL AUTO_INCREMENT COMMENT '具体的方法',
  `operationName` varchar(100) DEFAULT NULL COMMENT '方法名',
  `menuId` int(11) DEFAULT NULL COMMENT '所属菜单',
  `menuName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`operationId`),
  KEY `menuId` (`menuId`),
  CONSTRAINT `operation_ibfk_1` FOREIGN KEY (`menuId`) REFERENCES `menu` (`menuId`)
) ENGINE=InnoDB AUTO_INCREMENT=10016 DEFAULT CHARSET=utf8 COMMENT='具体的页面按钮上的方法\r\n（此自增ID至少从10000开始）';

/*Data for the table `operation` */

insert  into `operation`(`operationId`,`operationName`,`menuId`,`menuName`) values (10000,'添加',5,'菜单管理'),(10001,'修改',5,'菜单管理'),(10002,'删除',5,'菜单管理'),(10003,'添加',6,'用户管理'),(10004,'修改',6,'用户管理'),(10005,'删除',6,'用户管理'),(10006,'添加',7,'角色管理'),(10007,'修改',7,'角色管理'),(10008,'删除',7,'角色管理'),(10009,'授权',7,'角色管理'),(10011,'下载后台日志（log4j）',26,'日志管理'),(10012,'手动备份（业务操作）',26,'日志管理'),(10013,'删除',26,'日志管理'),(10014,'按钮管理',5,'菜单管理'),(10015,'下载操作日志',26,'日志管理');

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `roleName` varchar(20) DEFAULT NULL COMMENT '角色名称',
  `menuIds` varchar(200) DEFAULT NULL COMMENT '菜单IDs',
  `operationIds` varchar(200) DEFAULT NULL COMMENT '按钮IDS',
  `roleDescription` varchar(200) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`roleId`,`roleName`,`menuIds`,`operationIds`,`roleDescription`) values (1,'超级管理员','1,2,6,7,5,26,3,8,17,4,24,9,15,16','10003,10004,10005,10006,10007,10008,10009,10000,10001,10002,10014,10011,10012,10013,10015','拥有全部权限的超级管理员角色'),(2,'普通管理员A','1,2,6,7,5,26,3,8,17,15,16','10003,10004,10006,10007,10000,10011,10012,10015','测试'),(3,'超级管理员B','1,2,26,3,8,17,4,24,9,15,16','10011,10012,10015','1');

/*Table structure for table `token` */

DROP TABLE IF EXISTS `token`;

CREATE TABLE `token` (
  `tokenId` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL COMMENT '用户ID',
  `userAgent` varchar(50) DEFAULT NULL COMMENT '用户（md5）',
  `token` varchar(100) DEFAULT NULL COMMENT 'md5(username+md5(useragent))',
  `createTime` varchar(30) DEFAULT NULL COMMENT '创建时间',
  `expireTime` varchar(30) DEFAULT NULL COMMENT '到期时间',
  PRIMARY KEY (`tokenId`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='用户登录信息（用于自动登录）';

/*Data for the table `token` */

insert  into `token`(`tokenId`,`userId`,`userAgent`,`token`,`createTime`,`expireTime`) values (16,1,'9dc4e31772dec12e5718abffbafdee35','45b00f0469c9eb148d9879c926041fe1','2015-08-15 14:32:23','2015-08-29 14:32:23');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `userName` varchar(20) DEFAULT NULL COMMENT '用户名',
  `password` varchar(20) DEFAULT NULL COMMENT '密码',
  `userType` tinyint(4) DEFAULT NULL COMMENT '用户类型',
  `roleId` int(11) DEFAULT NULL COMMENT '角色ID',
  `userDescription` varchar(200) DEFAULT NULL COMMENT '描述信息',
  PRIMARY KEY (`userId`),
  KEY `roleId` (`roleId`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `role` (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`userId`,`userName`,`password`,`userType`,`roleId`,`userDescription`) values (1,'java','1',1,1,'1'),(4,'A','A',2,2,'A'),(6,'B','B',2,3,'B'),(46,'w','1',2,2,'1');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
