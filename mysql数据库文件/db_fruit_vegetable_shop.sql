/*
Navicat MySQL Data Transfer

Source Server         : 本地连接
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : db_fruit_vegetable_shop

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2020-11-19 15:06:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `bms_admin`
-- ----------------------------
DROP TABLE IF EXISTS `bms_admin`;
CREATE TABLE `bms_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员ID',
  `role_id` int(11) NOT NULL DEFAULT '0' COMMENT '管理员对应角色ID；默认0：无',
  `head_pic` varchar(256) DEFAULT 'common/default_img.jpg' COMMENT '管理员头像',
  `password` varchar(16) NOT NULL DEFAULT '123456' COMMENT '管理员密码',
  `name` varchar(16) NOT NULL COMMENT '管理员姓名',
  `sex` int(11) DEFAULT '3' COMMENT '管理员性别：1：男；2：女；3：未知',
  `address` varchar(128) DEFAULT NULL COMMENT '管理员地址',
  `mobile` bigint(20) NOT NULL COMMENT '管理员电话',
  `state` int(11) NOT NULL DEFAULT '1' COMMENT '管理员状态：1：启用；2：冻结',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '管理员创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '管理员更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bms_admin
-- ----------------------------
INSERT INTO `bms_admin` VALUES ('1', '1', '20200807/1596731167923.jpg', '1234567', '杨杨吖', '1', '福建省厦门市湖里区水晶公寓1座402', '13774559485', '1', '2020-08-02 16:27:44', '2020-08-07 00:27:42');
INSERT INTO `bms_admin` VALUES ('9', '5', '20200803/1596444752079.jpg', '123456', '测试人员', '1', '福建省福州市鼓楼区金山佳园4座101', '13983776256', '1', '2020-08-03 15:46:46', '2020-08-07 00:01:31');
INSERT INTO `bms_admin` VALUES ('21', '5', '20200806/1596709959740.jpg', '123456', '开发人员', '2', '福建省莆田市秀屿区碧水公寓5座202', '14328448375', '1', '2020-08-06 18:33:22', '2020-08-06 18:33:53');

-- ----------------------------
-- Table structure for `bms_announcement`
-- ----------------------------
DROP TABLE IF EXISTS `bms_announcement`;
CREATE TABLE `bms_announcement` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `content` varchar(256) NOT NULL COMMENT '公告内容',
  `admin_id` int(11) NOT NULL COMMENT '公告发布所属管理员',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '公告创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '公告更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bms_announcement
-- ----------------------------
INSERT INTO `bms_announcement` VALUES ('7', '测试人员快把任务给我做了', '1', '2020-08-07 18:23:30', '2020-08-07 18:23:30');
INSERT INTO `bms_announcement` VALUES ('8', '开发人员也速度点', '1', '2020-08-07 18:23:47', '2020-08-07 18:23:47');

-- ----------------------------
-- Table structure for `bms_attachment`
-- ----------------------------
DROP TABLE IF EXISTS `bms_attachment`;
CREATE TABLE `bms_attachment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '附件ID',
  `sender_id` int(11) NOT NULL COMMENT '发件人ID',
  `url` varchar(256) NOT NULL COMMENT '附件保存路径',
  `name` varchar(256) NOT NULL COMMENT '附件名称',
  `size` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '附件大小：单位为KB；默认为0.00',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '附件创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '附件更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bms_attachment
-- ----------------------------
INSERT INTO `bms_attachment` VALUES ('1', '1', '20200807/1596812677479.docx', '感谢文档.docx', '10.89', '2020-08-07 23:04:37', '2020-08-07 23:04:37');
INSERT INTO `bms_attachment` VALUES ('3', '1', '20200808/1596822984571.docx', '感谢文档.docx', '10.89', '2020-08-08 01:56:24', '2020-08-08 01:56:24');

-- ----------------------------
-- Table structure for `bms_authority`
-- ----------------------------
DROP TABLE IF EXISTS `bms_authority`;
CREATE TABLE `bms_authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `menu_id` int(11) NOT NULL COMMENT '权限对应的菜单ID',
  `role_id` int(11) NOT NULL COMMENT '权限对应的角色ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '权限创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '权限更改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=489 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bms_authority
-- ----------------------------
INSERT INTO `bms_authority` VALUES ('99', '11', '5', '2020-08-06 18:40:25', '2020-08-06 18:40:25');
INSERT INTO `bms_authority` VALUES ('100', '15', '5', '2020-08-06 18:40:25', '2020-08-06 18:40:25');
INSERT INTO `bms_authority` VALUES ('101', '16', '5', '2020-08-06 18:40:25', '2020-08-06 18:40:25');
INSERT INTO `bms_authority` VALUES ('102', '17', '5', '2020-08-06 18:40:25', '2020-08-06 18:40:25');
INSERT INTO `bms_authority` VALUES ('103', '18', '5', '2020-08-06 18:40:25', '2020-08-06 18:40:25');
INSERT INTO `bms_authority` VALUES ('104', '88', '5', '2020-08-06 18:40:25', '2020-08-06 18:40:25');
INSERT INTO `bms_authority` VALUES ('105', '89', '5', '2020-08-06 18:40:25', '2020-08-06 18:40:25');
INSERT INTO `bms_authority` VALUES ('106', '90', '5', '2020-08-06 18:40:25', '2020-08-06 18:40:25');
INSERT INTO `bms_authority` VALUES ('107', '95', '5', '2020-08-06 18:40:25', '2020-08-06 18:40:25');
INSERT INTO `bms_authority` VALUES ('108', '96', '5', '2020-08-06 18:40:25', '2020-08-06 18:40:25');
INSERT INTO `bms_authority` VALUES ('109', '91', '5', '2020-08-06 18:40:25', '2020-08-06 18:40:25');
INSERT INTO `bms_authority` VALUES ('110', '97', '5', '2020-08-06 18:40:25', '2020-08-06 18:40:25');
INSERT INTO `bms_authority` VALUES ('111', '98', '5', '2020-08-06 18:40:25', '2020-08-06 18:40:25');
INSERT INTO `bms_authority` VALUES ('441', '1', '1', '2020-11-19 10:33:11', '2020-11-19 10:33:11');
INSERT INTO `bms_authority` VALUES ('442', '2', '1', '2020-11-19 10:33:11', '2020-11-19 10:33:11');
INSERT INTO `bms_authority` VALUES ('443', '3', '1', '2020-11-19 10:33:11', '2020-11-19 10:33:11');
INSERT INTO `bms_authority` VALUES ('444', '4', '1', '2020-11-19 10:33:11', '2020-11-19 10:33:11');
INSERT INTO `bms_authority` VALUES ('445', '10', '1', '2020-11-19 10:33:11', '2020-11-19 10:33:11');
INSERT INTO `bms_authority` VALUES ('446', '14', '1', '2020-11-19 10:33:11', '2020-11-19 10:33:11');
INSERT INTO `bms_authority` VALUES ('447', '11', '1', '2020-11-19 10:33:11', '2020-11-19 10:33:11');
INSERT INTO `bms_authority` VALUES ('448', '15', '1', '2020-11-19 10:33:11', '2020-11-19 10:33:11');
INSERT INTO `bms_authority` VALUES ('449', '16', '1', '2020-11-19 10:33:11', '2020-11-19 10:33:11');
INSERT INTO `bms_authority` VALUES ('450', '17', '1', '2020-11-19 10:33:11', '2020-11-19 10:33:11');
INSERT INTO `bms_authority` VALUES ('451', '18', '1', '2020-11-19 10:33:11', '2020-11-19 10:33:11');
INSERT INTO `bms_authority` VALUES ('452', '25', '1', '2020-11-19 10:33:11', '2020-11-19 10:33:11');
INSERT INTO `bms_authority` VALUES ('453', '26', '1', '2020-11-19 10:33:11', '2020-11-19 10:33:11');
INSERT INTO `bms_authority` VALUES ('454', '81', '1', '2020-11-19 10:33:11', '2020-11-19 10:33:11');
INSERT INTO `bms_authority` VALUES ('455', '82', '1', '2020-11-19 10:33:11', '2020-11-19 10:33:11');
INSERT INTO `bms_authority` VALUES ('456', '83', '1', '2020-11-19 10:33:11', '2020-11-19 10:33:11');
INSERT INTO `bms_authority` VALUES ('457', '88', '1', '2020-11-19 10:33:11', '2020-11-19 10:33:11');
INSERT INTO `bms_authority` VALUES ('458', '89', '1', '2020-11-19 10:33:11', '2020-11-19 10:33:11');
INSERT INTO `bms_authority` VALUES ('459', '90', '1', '2020-11-19 10:33:11', '2020-11-19 10:33:11');
INSERT INTO `bms_authority` VALUES ('460', '95', '1', '2020-11-19 10:33:11', '2020-11-19 10:33:11');
INSERT INTO `bms_authority` VALUES ('461', '96', '1', '2020-11-19 10:33:11', '2020-11-19 10:33:11');
INSERT INTO `bms_authority` VALUES ('462', '91', '1', '2020-11-19 10:33:11', '2020-11-19 10:33:11');
INSERT INTO `bms_authority` VALUES ('463', '97', '1', '2020-11-19 10:33:11', '2020-11-19 10:33:11');
INSERT INTO `bms_authority` VALUES ('464', '98', '1', '2020-11-19 10:33:11', '2020-11-19 10:33:11');
INSERT INTO `bms_authority` VALUES ('465', '108', '1', '2020-11-19 10:33:11', '2020-11-19 10:33:11');
INSERT INTO `bms_authority` VALUES ('466', '109', '1', '2020-11-19 10:33:11', '2020-11-19 10:33:11');
INSERT INTO `bms_authority` VALUES ('467', '110', '1', '2020-11-19 10:33:11', '2020-11-19 10:33:11');
INSERT INTO `bms_authority` VALUES ('468', '111', '1', '2020-11-19 10:33:11', '2020-11-19 10:33:11');
INSERT INTO `bms_authority` VALUES ('469', '112', '1', '2020-11-19 10:33:11', '2020-11-19 10:33:11');
INSERT INTO `bms_authority` VALUES ('470', '113', '1', '2020-11-19 10:33:11', '2020-11-19 10:33:11');
INSERT INTO `bms_authority` VALUES ('471', '115', '1', '2020-11-19 10:33:11', '2020-11-19 10:33:11');
INSERT INTO `bms_authority` VALUES ('472', '116', '1', '2020-11-19 10:33:11', '2020-11-19 10:33:11');
INSERT INTO `bms_authority` VALUES ('473', '117', '1', '2020-11-19 10:33:11', '2020-11-19 10:33:11');
INSERT INTO `bms_authority` VALUES ('474', '114', '1', '2020-11-19 10:33:11', '2020-11-19 10:33:11');
INSERT INTO `bms_authority` VALUES ('475', '118', '1', '2020-11-19 10:33:11', '2020-11-19 10:33:11');
INSERT INTO `bms_authority` VALUES ('476', '119', '1', '2020-11-19 10:33:11', '2020-11-19 10:33:11');
INSERT INTO `bms_authority` VALUES ('477', '120', '1', '2020-11-19 10:33:11', '2020-11-19 10:33:11');
INSERT INTO `bms_authority` VALUES ('478', '121', '1', '2020-11-19 10:33:11', '2020-11-19 10:33:11');
INSERT INTO `bms_authority` VALUES ('479', '122', '1', '2020-11-19 10:33:11', '2020-11-19 10:33:11');
INSERT INTO `bms_authority` VALUES ('480', '125', '1', '2020-11-19 10:33:11', '2020-11-19 10:33:11');
INSERT INTO `bms_authority` VALUES ('481', '126', '1', '2020-11-19 10:33:11', '2020-11-19 10:33:11');
INSERT INTO `bms_authority` VALUES ('482', '123', '1', '2020-11-19 10:33:11', '2020-11-19 10:33:11');
INSERT INTO `bms_authority` VALUES ('483', '124', '1', '2020-11-19 10:33:11', '2020-11-19 10:33:11');
INSERT INTO `bms_authority` VALUES ('484', '127', '1', '2020-11-19 10:33:11', '2020-11-19 10:33:11');
INSERT INTO `bms_authority` VALUES ('485', '128', '1', '2020-11-19 10:33:11', '2020-11-19 10:33:11');
INSERT INTO `bms_authority` VALUES ('486', '129', '1', '2020-11-19 10:33:11', '2020-11-19 10:33:11');
INSERT INTO `bms_authority` VALUES ('487', '130', '1', '2020-11-19 10:33:11', '2020-11-19 10:33:11');
INSERT INTO `bms_authority` VALUES ('488', '131', '1', '2020-11-19 10:33:11', '2020-11-19 10:33:11');

-- ----------------------------
-- Table structure for `bms_mail`
-- ----------------------------
DROP TABLE IF EXISTS `bms_mail`;
CREATE TABLE `bms_mail` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '邮件ID',
  `sender_id` int(11) NOT NULL COMMENT '邮件发件人ID',
  `receiver_id` int(11) NOT NULL COMMENT '邮件收件人ID',
  `title` varchar(64) NOT NULL COMMENT '邮件主题',
  `content` longtext COMMENT '邮件正文',
  `attachment_one` int(11) DEFAULT NULL COMMENT '邮件的附件1',
  `attachment_two` int(11) DEFAULT NULL COMMENT '邮件的附件2',
  `attachment_three` int(11) DEFAULT NULL COMMENT '邮件的附件3',
  `delete_state` int(11) NOT NULL DEFAULT '1' COMMENT '邮件删除状态：1:双方均未删除  2：发送者删除；3：接收者删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '邮件创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '邮件更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bms_mail
-- ----------------------------
INSERT INTO `bms_mail` VALUES ('1', '1', '1', '测试邮件', '<p>测试正文<img src=\"http://img.baidu.com/hi/bobo/B_0026.gif\"/></p><p><img src=\"http://localhost:8080/ueditor/images/20200807/1596812716910.jpg\" title=\"1596812716910.jpg\" alt=\"1596812716910.jpg\"/></p>', '1', null, null, '1', '2020-08-07 23:05:25', '2020-08-07 23:05:25');
INSERT INTO `bms_mail` VALUES ('2', '1', '9', '测试邮件', '<p>测试正文<img src=\"http://img.baidu.com/hi/bobo/B_0026.gif\"/></p><p><img src=\"http://localhost:8080/ueditor/images/20200807/1596812716910.jpg\" title=\"1596812716910.jpg\" alt=\"1596812716910.jpg\"/></p>', '1', null, null, '1', '2020-08-07 23:05:25', '2020-08-07 23:05:25');
INSERT INTO `bms_mail` VALUES ('3', '1', '21', '测试邮件', '<p>测试正文<img src=\"http://img.baidu.com/hi/bobo/B_0026.gif\"/></p><p><img src=\"http://localhost:8080/ueditor/images/20200807/1596812716910.jpg\" title=\"1596812716910.jpg\" alt=\"1596812716910.jpg\"/></p>', '1', null, null, '1', '2020-08-07 23:05:25', '2020-08-07 23:05:25');

-- ----------------------------
-- Table structure for `bms_menu`
-- ----------------------------
DROP TABLE IF EXISTS `bms_menu`;
CREATE TABLE `bms_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '上级菜单的ID：默认为0',
  `name` varchar(32) NOT NULL COMMENT '菜单名称',
  `url` varchar(256) DEFAULT NULL COMMENT '菜单路径',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '菜单排序：默认为0，值越大则在同级别越优先显示',
  `icon` varchar(64) NOT NULL COMMENT '菜单图标',
  `state` int(11) NOT NULL DEFAULT '1' COMMENT '菜单状态：1:开启；2：停用',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '菜单创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '菜单更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=132 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bms_menu
-- ----------------------------
INSERT INTO `bms_menu` VALUES ('1', '0', '菜单管理', '', '0', '&#xe6b4;', '1', '2020-07-31 15:10:59', '2020-08-02 17:16:55');
INSERT INTO `bms_menu` VALUES ('2', '1', '菜单列表', '/admin/menu/index', '0', '&#xe6a2;', '1', '2020-07-31 15:11:13', '2020-08-02 13:38:43');
INSERT INTO `bms_menu` VALUES ('3', '2', '添加', 'xadmin.open(\'菜单添加\',\'/admin/menu/add\',700,500);', '2', '&#xe6b9;', '1', '2020-07-31 15:12:33', '2020-08-04 10:30:41');
INSERT INTO `bms_menu` VALUES ('4', '2', '编辑', 'openEdit();', '1', '&#xe69e;', '1', '2020-07-31 20:19:47', '2020-08-02 14:00:59');
INSERT INTO `bms_menu` VALUES ('10', '2', '删除', 'deleteMenu();', '0', '&#xe69d;', '1', '2020-08-02 12:53:19', '2020-08-02 16:35:33');
INSERT INTO `bms_menu` VALUES ('11', '0', '管理员管理', '', '0', '&#xe726;', '1', '2020-08-02 13:09:32', '2020-08-02 16:35:22');
INSERT INTO `bms_menu` VALUES ('14', '2', '添加按钮', 'openAddButton();', '0', '&#xe6b9;', '1', '2020-08-02 13:47:20', '2020-08-02 14:01:07');
INSERT INTO `bms_menu` VALUES ('15', '11', '管理员列表', '/admin/admin/index', '0', '&#xe6a2;', '1', '2020-08-02 14:03:08', '2020-08-03 13:27:51');
INSERT INTO `bms_menu` VALUES ('16', '15', '添加', 'xadmin.open(\'管理员添加\',\'/admin/admin/add\',700,550);', '0', '&#xe6b9;', '1', '2020-08-02 17:31:25', '2020-08-04 10:30:57');
INSERT INTO `bms_menu` VALUES ('17', '15', '编辑', 'openEdit();', '0', '&#xe69e;', '1', '2020-08-02 17:32:45', '2020-08-02 17:32:45');
INSERT INTO `bms_menu` VALUES ('18', '15', '删除', 'deleteAdmin();', '0', '&#xe69d;', '1', '2020-08-02 17:33:45', '2020-08-02 17:33:45');
INSERT INTO `bms_menu` VALUES ('25', '0', '角色管理', '', '0', '&#xe6a0;', '1', '2020-08-03 16:56:34', '2020-08-03 16:56:34');
INSERT INTO `bms_menu` VALUES ('26', '25', '角色列表', '/admin/role/index', '0', '&#xe6a2;', '1', '2020-08-03 16:57:23', '2020-08-03 16:57:23');
INSERT INTO `bms_menu` VALUES ('81', '26', '添加', 'xadmin.open(\'角色添加\',\'/admin/role/add\',550,350);', '0', '&#xe6b9;', '1', '2020-08-04 10:24:55', '2020-08-04 10:38:58');
INSERT INTO `bms_menu` VALUES ('82', '26', '编辑', 'openEdit();', '0', '&#xe69e;', '1', '2020-08-04 10:25:24', '2020-08-04 10:26:42');
INSERT INTO `bms_menu` VALUES ('83', '26', '删除', 'deleteRole();', '0', '&#xe69d;', '1', '2020-08-04 10:25:46', '2020-08-04 10:27:35');
INSERT INTO `bms_menu` VALUES ('88', '0', '邮件管理', '', '0', '&#xe69f;', '1', '2020-08-04 20:38:54', '2020-08-04 20:38:54');
INSERT INTO `bms_menu` VALUES ('89', '88', '发送邮件', '/admin/mail/write', '0', '&#xe71d;', '1', '2020-08-04 20:40:22', '2020-08-06 10:01:00');
INSERT INTO `bms_menu` VALUES ('90', '88', '收件箱', '/admin/mail/receive', '0', '&#xe749;', '1', '2020-08-04 20:43:18', '2020-08-06 10:01:12');
INSERT INTO `bms_menu` VALUES ('91', '88', '发件箱', '/admin/mail/send', '0', '&#xe6ab;', '1', '2020-08-04 20:43:36', '2020-08-06 18:17:45');
INSERT INTO `bms_menu` VALUES ('95', '90', '查看', 'preview();', '0', '&#xe6e6;', '1', '2020-08-06 09:57:26', '2020-08-06 11:39:13');
INSERT INTO `bms_menu` VALUES ('96', '90', '删除', 'deleteMail();', '0', '&#xe69d;', '1', '2020-08-06 09:58:33', '2020-08-06 09:58:33');
INSERT INTO `bms_menu` VALUES ('97', '91', '查看', 'preview();', '0', '&#xe6e6;', '1', '2020-08-06 14:20:56', '2020-08-06 14:20:56');
INSERT INTO `bms_menu` VALUES ('98', '91', '删除', 'deleteMail();', '0', '&#xe69d;', '1', '2020-08-06 14:21:24', '2020-08-06 14:21:24');
INSERT INTO `bms_menu` VALUES ('108', '0', '公告管理', '', '0', '&#xe6bc;', '1', '2020-08-07 14:22:32', '2020-08-07 14:36:49');
INSERT INTO `bms_menu` VALUES ('109', '108', '公告列表', '/admin/announcement/index', '0', '&#xe6a2;', '1', '2020-08-07 14:24:20', '2020-08-07 14:37:23');
INSERT INTO `bms_menu` VALUES ('110', '109', '添加', 'xadmin.open(\'公告添加\',\'/admin/announcement/add\',550,300);', '0', '&#xe6b9;', '1', '2020-08-07 14:25:49', '2020-08-07 16:01:02');
INSERT INTO `bms_menu` VALUES ('111', '109', '删除', 'deleteAnnouncement();', '0', '&#xe69d;', '1', '2020-08-07 14:27:11', '2020-08-07 14:27:11');
INSERT INTO `bms_menu` VALUES ('112', '0', '商品管理', '', '0', '&#xe6f6;', '1', '2020-11-12 09:19:59', '2020-11-12 09:21:33');
INSERT INTO `bms_menu` VALUES ('113', '112', '商品种类列表', '/admin/product_category/index', '0', '&#xe6a2;', '1', '2020-11-12 09:22:01', '2020-11-12 10:00:07');
INSERT INTO `bms_menu` VALUES ('114', '112', '商品列表', '/admin/product/index', '0', '&#xe6a2;', '1', '2020-11-12 09:25:18', '2020-11-12 15:48:28');
INSERT INTO `bms_menu` VALUES ('115', '113', '添加', 'xadmin.open(\'商品种类添加\',\'/admin/product_category/add\',550,200);', '0', '&#xe6b9;', '1', '2020-11-12 10:04:15', '2020-11-12 11:55:10');
INSERT INTO `bms_menu` VALUES ('116', '113', '编辑', 'openEdit();', '0', '&#xe69e;', '1', '2020-11-12 10:05:22', '2020-11-12 10:05:22');
INSERT INTO `bms_menu` VALUES ('117', '113', '删除', 'deleteProductCategory();', '0', '&#xe69d;', '1', '2020-11-12 10:06:39', '2020-11-12 10:06:39');
INSERT INTO `bms_menu` VALUES ('118', '114', '添加', 'xadmin.open(\'商品添加\',\'/admin/product/add\',700,550);', '0', '&#xe6b9;', '1', '2020-11-12 15:49:17', '2020-11-12 17:22:20');
INSERT INTO `bms_menu` VALUES ('119', '114', '编辑', 'openEdit();', '0', '&#xe69e;', '1', '2020-11-12 15:50:10', '2020-11-12 15:50:10');
INSERT INTO `bms_menu` VALUES ('120', '114', '删除', 'deleteProduct();', '0', '&#xe69d;', '1', '2020-11-12 15:50:36', '2020-11-12 15:50:36');
INSERT INTO `bms_menu` VALUES ('121', '0', '用户管理', '', '0', '&#xe6b8;', '1', '2020-11-18 20:21:08', '2020-11-18 20:21:08');
INSERT INTO `bms_menu` VALUES ('122', '121', '用户列表', '/admin/user/index', '0', '&#xe6a2;', '1', '2020-11-18 20:21:50', '2020-11-18 20:22:02');
INSERT INTO `bms_menu` VALUES ('123', '121', '评论列表', '/admin/user/comment', '0', '&#xe69b;', '1', '2020-11-18 20:24:23', '2020-11-18 20:24:23');
INSERT INTO `bms_menu` VALUES ('124', '123', '删除', 'deleteComment();', '0', '&#xe69d;', '1', '2020-11-18 20:24:48', '2020-11-18 20:26:56');
INSERT INTO `bms_menu` VALUES ('125', '122', '修改密码', 'openEdit();', '0', '&#xe69e;', '1', '2020-11-18 20:26:44', '2020-11-18 20:26:44');
INSERT INTO `bms_menu` VALUES ('126', '122', '删除', 'deleteUser();', '0', '&#xe69d;', '1', '2020-11-18 20:27:49', '2020-11-18 20:27:49');
INSERT INTO `bms_menu` VALUES ('127', '0', '订单管理', '', '0', '&#xe702;', '1', '2020-11-19 10:17:13', '2020-11-19 10:17:25');
INSERT INTO `bms_menu` VALUES ('128', '127', '订单列表', '/admin/order/index', '0', '&#xe6a2;', '1', '2020-11-19 10:17:50', '2020-11-19 10:17:50');
INSERT INTO `bms_menu` VALUES ('129', '128', '修改订单状态', 'openEditState();', '0', '&#xe69e;', '1', '2020-11-19 10:18:18', '2020-11-19 13:05:39');
INSERT INTO `bms_menu` VALUES ('130', '128', '删除订单', 'deleteOrder();', '0', '&#xe69d;', '1', '2020-11-19 10:18:50', '2020-11-19 10:18:50');
INSERT INTO `bms_menu` VALUES ('131', '128', '查看订单详情', 'viewOrder();', '0', '&#xe6e6;', '1', '2020-11-19 10:29:59', '2020-11-19 11:02:58');

-- ----------------------------
-- Table structure for `bms_role`
-- ----------------------------
DROP TABLE IF EXISTS `bms_role`;
CREATE TABLE `bms_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `name` varchar(32) NOT NULL COMMENT '角色名称',
  `description` varchar(128) DEFAULT NULL COMMENT '角色描述',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '角色创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '角色更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bms_role
-- ----------------------------
INSERT INTO `bms_role` VALUES ('1', '超级管理员', '该系统的超级管理员，拥有管理该系统所有权限！', '2020-08-04 09:54:40', '2020-08-06 22:30:49');
INSERT INTO `bms_role` VALUES ('5', '普通管理员', '该系统的普通管理员，可以管理该系统一部分权限！', '2020-08-04 13:46:54', '2020-08-04 13:46:54');

-- ----------------------------
-- Table structure for `mall_address`
-- ----------------------------
DROP TABLE IF EXISTS `mall_address`;
CREATE TABLE `mall_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '地址id',
  `receiver_name` varchar(8) NOT NULL COMMENT '收货人姓名',
  `receiver_address` varchar(64) NOT NULL COMMENT '收货人地址',
  `receiver_phone` varchar(11) NOT NULL COMMENT '收货人手机号码',
  `user_id` bigint(20) NOT NULL COMMENT '地址对应的用户id',
  `first_selected` int(11) NOT NULL DEFAULT '0' COMMENT '该地址是否为首先地址  0：不是；1：是',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '地址创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '地址更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mall_address
-- ----------------------------
INSERT INTO `mall_address` VALUES ('5', '杨杨', '福建省厦门市湖里区水晶公寓3栋201室', '13674558736', '1', '1', '2020-11-11 22:10:53', '2020-11-18 17:23:40');
INSERT INTO `mall_address` VALUES ('6', '小杨', '福建省福州市鼓楼区米罗时代1座302', '13877488573', '1', '0', '2020-11-11 22:12:01', '2020-11-18 17:23:40');

-- ----------------------------
-- Table structure for `mall_collect`
-- ----------------------------
DROP TABLE IF EXISTS `mall_collect`;
CREATE TABLE `mall_collect` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '收藏id',
  `user_id` bigint(20) NOT NULL COMMENT '收藏对应的用户id',
  `product_id` bigint(20) NOT NULL COMMENT '收藏对应的商品id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '收藏创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '收藏更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mall_collect
-- ----------------------------
INSERT INTO `mall_collect` VALUES ('10', '1', '10', '2020-11-14 18:29:55', '2020-11-14 18:29:55');
INSERT INTO `mall_collect` VALUES ('11', '1', '6', '2020-11-14 18:30:03', '2020-11-14 18:30:03');
INSERT INTO `mall_collect` VALUES ('13', '1', '7', '2020-11-18 20:03:25', '2020-11-18 20:03:25');

-- ----------------------------
-- Table structure for `mall_comment`
-- ----------------------------
DROP TABLE IF EXISTS `mall_comment`;
CREATE TABLE `mall_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `product_id` bigint(20) NOT NULL COMMENT '评论对应的商品id',
  `user_id` bigint(20) NOT NULL COMMENT '评论对应的用户id',
  `content` varchar(128) NOT NULL COMMENT '评论内容',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '评论创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '评论更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mall_comment
-- ----------------------------
INSERT INTO `mall_comment` VALUES ('1', '7', '1', '这菜我买过，很不错！！', '2020-11-18 20:03:32', '2020-11-18 20:03:32');
INSERT INTO `mall_comment` VALUES ('8', '9', '1', '我觉得很不错，很甜！！', '2020-11-19 14:27:29', '2020-11-19 14:27:29');
INSERT INTO `mall_comment` VALUES ('9', '3', '1', '这苹果真难吃，大家别买！！', '2020-11-19 14:28:22', '2020-11-19 14:28:22');
INSERT INTO `mall_comment` VALUES ('10', '6', '1', '还不错！', '2020-11-19 14:28:36', '2020-11-19 14:28:36');
INSERT INTO `mall_comment` VALUES ('11', '10', '1', '真甜这西瓜，我从没吃过这么好吃的', '2020-11-19 14:33:12', '2020-11-19 14:33:12');
INSERT INTO `mall_comment` VALUES ('12', '4', '1', '这包菜还是可以的！！', '2020-11-19 14:33:44', '2020-11-19 14:33:44');
INSERT INTO `mall_comment` VALUES ('13', '10', '1', '好吃好吃', '2020-11-19 14:51:32', '2020-11-19 14:51:32');

-- ----------------------------
-- Table structure for `mall_order`
-- ----------------------------
DROP TABLE IF EXISTS `mall_order`;
CREATE TABLE `mall_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `order_no` bigint(20) NOT NULL COMMENT '订单流水号',
  `user_id` bigint(20) NOT NULL COMMENT '订单所属用户id',
  `state` int(11) NOT NULL DEFAULT '0' COMMENT '订单状态  0：未支付；1：已支付，待发货；2：已取消；3：已送达，待签收；4：已签收；5：已发货',
  `total_price` decimal(10,2) NOT NULL COMMENT '订单总价',
  `address_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '订单对应的配送地址id',
  `remark` varchar(128) DEFAULT NULL COMMENT '订单留言',
  `is_deleted` int(11) NOT NULL DEFAULT '0' COMMENT '用户是否删除订单 0：未删除；1：已删除 ',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '订单创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '订单更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mall_order
-- ----------------------------
INSERT INTO `mall_order` VALUES ('1', '525844555678101504', '1', '1', '162.67', '6', '包装注意点，别压坏我水蜜桃！！！', '0', '2020-11-16 22:39:37', '2020-11-18 17:16:04');
INSERT INTO `mall_order` VALUES ('5', '526172833123151872', '1', '4', '70.02', '6', '尽快送来哈', '0', '2020-11-17 20:24:04', '2020-11-19 13:28:53');
INSERT INTO `mall_order` VALUES ('6', '526488209677627392', '1', '5', '173.89', '5', '', '0', '2020-11-18 17:17:16', '2020-11-19 13:07:22');

-- ----------------------------
-- Table structure for `mall_order_item`
-- ----------------------------
DROP TABLE IF EXISTS `mall_order_item`;
CREATE TABLE `mall_order_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单详情id',
  `order_id` bigint(20) NOT NULL COMMENT '订单详情对应的订单id',
  `product_id` bigint(20) NOT NULL COMMENT '订单详情对应的商品id',
  `product_name` varchar(16) NOT NULL COMMENT '商品名称',
  `product_pic` varchar(256) NOT NULL COMMENT '商品图片',
  `product_price` decimal(10,2) NOT NULL COMMENT '商品价格',
  `quantity` int(11) NOT NULL COMMENT '商品购买数量',
  `total_price` decimal(10,2) NOT NULL COMMENT '商品小计',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '订单详情创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '订单详情更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mall_order_item
-- ----------------------------
INSERT INTO `mall_order_item` VALUES ('1', '1', '6', '脆脆花菜', '20201113/1605266609840.jpeg', '36.21', '1', '36.21', '2020-11-16 22:39:37', '2020-11-16 22:39:37');
INSERT INTO `mall_order_item` VALUES ('2', '1', '9', '蜜汁水蜜桃', '20201113/1605266982555.jpeg', '63.23', '2', '126.46', '2020-11-16 22:39:37', '2020-11-16 22:39:37');
INSERT INTO `mall_order_item` VALUES ('3', '5', '8', '上海青', '20201113/1605266804873.jpg', '23.34', '3', '70.02', '2020-11-17 20:24:04', '2020-11-17 20:24:04');
INSERT INTO `mall_order_item` VALUES ('4', '6', '10', '新疆大西瓜', '20201113/1605267037772.jpg', '52.21', '1', '52.21', '2020-11-18 17:17:16', '2020-11-18 17:17:16');
INSERT INTO `mall_order_item` VALUES ('5', '6', '6', '脆脆花菜', '20201113/1605266609840.jpeg', '36.21', '2', '72.42', '2020-11-18 17:17:16', '2020-11-18 17:17:16');
INSERT INTO `mall_order_item` VALUES ('6', '6', '7', '香香空心菜', '20201113/1605266746974.jpg', '16.42', '3', '49.26', '2020-11-18 17:17:16', '2020-11-18 17:17:16');

-- ----------------------------
-- Table structure for `mall_product`
-- ----------------------------
DROP TABLE IF EXISTS `mall_product`;
CREATE TABLE `mall_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `product_name` varchar(16) NOT NULL COMMENT '商品的名称',
  `info` varchar(32) NOT NULL COMMENT '商品的详情',
  `product_pic` varchar(256) NOT NULL DEFAULT 'common/product_img.png' COMMENT '商品的图片',
  `price` decimal(10,2) NOT NULL COMMENT '商品的价格',
  `stock` int(11) NOT NULL COMMENT '商品的库存',
  `sell_num` int(11) NOT NULL DEFAULT '0' COMMENT '商品的销售数量',
  `comment_num` int(11) NOT NULL DEFAULT '0' COMMENT '商品的评论数量',
  `category_id` bigint(20) NOT NULL COMMENT '商品所属的商品种类id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '商品创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '商品更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mall_product
-- ----------------------------
INSERT INTO `mall_product` VALUES ('3', '红富士苹果', '超级甜的苹果，来自红富士著名品牌，值得购买！', '20201113/1605253581858.jpg', '120.23', '32', '0', '1', '1', '2020-11-13 15:47:49', '2020-11-19 14:49:07');
INSERT INTO `mall_product` VALUES ('4', '新鲜包菜', '刚刚采摘的包菜，味道美味，值得一买，不容有失！', '20201113/1605266416766.png', '46.32', '123', '0', '1', '2', '2020-11-13 19:21:20', '2020-11-19 14:50:12');
INSERT INTO `mall_product` VALUES ('5', '新疆哈密瓜', '新疆的美味哈密瓜，瓜甜多汁，香甜爽口！', '20201113/1605266527205.jpg', '64.17', '56', '0', '0', '1', '2020-11-13 19:23:14', '2020-11-16 15:11:36');
INSERT INTO `mall_product` VALUES ('6', '脆脆花菜', '绝对美味，绝对好吃，这花菜不好吃你找我！', '20201113/1605266609840.jpeg', '36.21', '349', '3', '1', '2', '2020-11-13 19:24:20', '2020-11-19 14:50:21');
INSERT INTO `mall_product` VALUES ('7', '香香空心菜', '美味的空心菜，炒过的香味你无法拒绝！', '20201113/1605266746974.jpg', '16.42', '31', '3', '1', '2', '2020-11-13 19:26:33', '2020-11-19 14:50:34');
INSERT INTO `mall_product` VALUES ('8', '上海青', '这上海青，我无法用言语形容了，一个字，妙！', '20201113/1605266804873.jpg', '23.34', '9', '3', '0', '2', '2020-11-13 19:27:38', '2020-11-17 20:24:17');
INSERT INTO `mall_product` VALUES ('9', '蜜汁水蜜桃', '汁多甜美的水蜜桃，你没法拒绝！', '20201113/1605266982555.jpeg', '63.23', '31', '2', '1', '1', '2020-11-13 19:30:30', '2020-11-19 14:49:27');
INSERT INTO `mall_product` VALUES ('10', '新疆大西瓜', '这西瓜吸收天地之养分，已经是天下最甜西瓜！', '20201113/1605267037772.jpg', '52.21', '20', '1', '2', '1', '2020-11-13 19:31:21', '2020-11-19 14:51:32');

-- ----------------------------
-- Table structure for `mall_product_category`
-- ----------------------------
DROP TABLE IF EXISTS `mall_product_category`;
CREATE TABLE `mall_product_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品种类id',
  `category_name` varchar(8) NOT NULL COMMENT '商品种类名称',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '商品种类创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '商品种类更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mall_product_category
-- ----------------------------
INSERT INTO `mall_product_category` VALUES ('1', '水果', '2020-11-12 12:12:27', '2020-11-12 12:41:15');
INSERT INTO `mall_product_category` VALUES ('2', '蔬菜', '2020-11-12 12:41:22', '2020-11-12 12:41:22');

-- ----------------------------
-- Table structure for `mall_user`
-- ----------------------------
DROP TABLE IF EXISTS `mall_user`;
CREATE TABLE `mall_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(8) NOT NULL COMMENT '用户名称',
  `password` varchar(16) NOT NULL COMMENT '用户密码',
  `email` varchar(64) NOT NULL COMMENT '用户电子邮箱',
  `head_pic` varchar(256) NOT NULL DEFAULT 'common/user_img.jpg' COMMENT '用户头像',
  `phone` varchar(11) NOT NULL COMMENT '用户手机号码',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '用户信息创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '用户信息更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mall_user
-- ----------------------------
INSERT INTO `mall_user` VALUES ('1', '杨杨吖', '12345678', '823208782@qq.com', '20201110/1605014280044.jpg', '13774559485', '2020-11-08 11:16:47', '2020-11-11 13:52:31');
INSERT INTO `mall_user` VALUES ('9', '杨杨的小号', '12345678', '823208782@qq.com', 'common/user_img.jpg', '13874559273', '2020-11-08 18:07:14', '2020-11-10 20:14:19');
