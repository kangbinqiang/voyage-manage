/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost:3306
 Source Schema         : manage

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 18/06/2020 17:11:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for m_cate
-- ----------------------------
DROP TABLE IF EXISTS `m_cate`;
CREATE TABLE `m_cate`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `parent_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '父亲d',
  `cate_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '分类id',
  `cate_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '分类名称',
  `cate_level` int(8) DEFAULT NULL COMMENT '层级',
  `cate_status` tinyint(1) DEFAULT 1 COMMENT '是否有效',
  `created_time` date DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `updated_time` date DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of m_cate
-- ----------------------------
INSERT INTO `m_cate` VALUES ('0bebe93d0d2e4e65a2ed3c3e33b57e26', NULL, 'ff54005011094cbca3df11d2871a9396', '衣服', 0, 0, '2020-06-17', 'admin', '2020-06-17', 'admin');
INSERT INTO `m_cate` VALUES ('24f2aead2327444f85e1f85b92292a66', 'ff54005011094cbca3df11d2871a9396', '875ebee7849f43e2b86ddc810464b316', '男生衣服', 1, 0, '2020-06-17', 'admin', '2020-06-17', 'admin');
INSERT INTO `m_cate` VALUES ('6a7de334a98c4b99aea074f188051f5a', 'ff54005011094cbca3df11d2871a9396', '3ab97597e5bc4d249c563599d67a602e', '女生衣服', 1, 0, '2020-06-17', 'admin', '2020-06-17', 'admin');
INSERT INTO `m_cate` VALUES ('976f90e4ae89493f9f330769b023f5a1', 'ff54005011094cbca3df11d2871a9396', '457466e5c133475980e64b9902964079', '老人衣服', 1, 0, '2020-06-17', 'admin', '2020-06-17', 'admin');
INSERT INTO `m_cate` VALUES ('ac84281fbd3144bc9e5efbf90193f2b9', NULL, '6c870c169d1549f285c002e599be1f81', '电器', 0, 1, '2020-06-17', 'admin', '2020-06-17', 'admin');
INSERT INTO `m_cate` VALUES ('d11eb80c4d0b44baa5f872bcd7dac080', NULL, 'ed0c6e03ff8249b1a6ebba68e970f877', '数码', 0, 0, '2020-06-17', 'admin', '2020-06-17', 'admin');
INSERT INTO `m_cate` VALUES ('dbf055f36feb4afb91db7d18735d2e24', '875ebee7849f43e2b86ddc810464b316', '6f063041268d4d42b490873f51496ebc', '男生的鞋子', 2, 0, '2020-06-17', 'admin', '2020-06-17', 'admin');

-- ----------------------------
-- Table structure for m_menu
-- ----------------------------
DROP TABLE IF EXISTS `m_menu`;
CREATE TABLE `m_menu`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `menu_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单名称',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图标',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单路径',
  `parent_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '子菜单id',
  `created_time` date DEFAULT NULL COMMENT '创建见',
  `created_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `updated_time` date DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of m_menu
-- ----------------------------
INSERT INTO `m_menu` VALUES ('011112fc46054a528cb1898b98e44dca', '用户管理', 'el-icon-user', '/users', '75cac4ebbe8c49f080ee3537c60abd34', '2020-04-24', 'admin', '2020-04-24', 'admin');
INSERT INTO `m_menu` VALUES ('06f0774fcf974e0390b597bcd9fb67d0', '分类管理', 'el-icon-user', '/categories', 'bb43c445e3d04e74acd05aba7c1fb8d7', '2020-06-06', 'admin', '2020-06-06', 'admin');
INSERT INTO `m_menu` VALUES ('41969ab4454d4bba97e4b5a5e2a5888f', '权限管理', 'el-icon-search', '/permissions', '75cac4ebbe8c49f080ee3537c60abd34', '2020-04-26', 'admin', '2020-04-26', 'admin');
INSERT INTO `m_menu` VALUES ('75cac4ebbe8c49f080ee3537c60abd34', '系统管理', 'el-icon-location', '/system', NULL, '2020-04-24', 'admin', '2020-04-24', 'admin');
INSERT INTO `m_menu` VALUES ('a104e4b37c8a499199808e5f3f6b100f', '角色管理', 'el-icon-search', '/roles', '75cac4ebbe8c49f080ee3537c60abd34', '2020-04-26', 'admin', '2020-04-26', 'admin');
INSERT INTO `m_menu` VALUES ('bb43c445e3d04e74acd05aba7c1fb8d7', '商品管理', 'el-icon-location', '/goods', NULL, '2020-06-06', 'admin', '2020-06-06', 'admin');
INSERT INTO `m_menu` VALUES ('c3bd595caeb04eeababa5a8bace43613', '信息管理', 'el-icon-location', '/message', NULL, '2020-04-24', 'admin', '2020-04-24', 'admin');

-- ----------------------------
-- Table structure for m_permission
-- ----------------------------
DROP TABLE IF EXISTS `m_permission`;
CREATE TABLE `m_permission`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `permission_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限名称',
  `permission_path` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限路径',
  `permission_level` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限等级',
  `created_time` date DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `updated_time` date DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of m_permission
-- ----------------------------
INSERT INTO `m_permission` VALUES ('14e0fb1dc6b947e7b28949b455a60cee', '新增用户', '/user', '2', '2020-04-26', 'admin', '2020-04-26', 'admin');
INSERT INTO `m_permission` VALUES ('d3c3add48e164c95a2ce8e3dd87fb461', '用户管理', '/user', '1', '2020-04-26', 'admin', '2020-04-26', 'admin');
INSERT INTO `m_permission` VALUES ('ff5bc36774c649ccab3849212b32c38f', '查询用户', '/user', '2', '2020-04-26', 'admin', '2020-04-26', 'admin');

-- ----------------------------
-- Table structure for m_role
-- ----------------------------
DROP TABLE IF EXISTS `m_role`;
CREATE TABLE `m_role`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `role_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色名称',
  `role_desc` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色描述',
  `created_time` date DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `updated_time` date DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of m_role
-- ----------------------------
INSERT INTO `m_role` VALUES ('1060f2139a944b0e85415a418293c001', 'Android开发', 'Android开发', '2020-04-27', 'admin', '2020-04-27', 'admin');
INSERT INTO `m_role` VALUES ('14399cafa2474514b5fdcc7e7d57435a', '项目经理', '项目经理', '2020-04-27', 'admin', '2020-04-27', 'admin');
INSERT INTO `m_role` VALUES ('1b6e6de8aab940308ef18924169fa93f', 'IOS开发', 'IOS开发', '2020-04-27', 'admin', '2020-04-27', 'admin');
INSERT INTO `m_role` VALUES ('426c48e4726345df80469066d75f59fd', 'H5开发', 'H5开发', '2020-04-27', 'admin', '2020-04-27', 'admin');
INSERT INTO `m_role` VALUES ('590509e490a04c848780bf95a0b8abf1', '后端开发', '后端开发', '2020-04-27', 'admin', '2020-04-27', 'admin');
INSERT INTO `m_role` VALUES ('6874aca5e1b84ac78759a1b56eee37d5', '管理员', '管理员权限', '2020-04-26', 'admin', '2020-04-26', 'admin');
INSERT INTO `m_role` VALUES ('9e6f8cb927124a1d907c2bc02ce51a5d', '运维人员', '运维人员', '2020-04-27', 'admin', '2020-04-27', 'admin');
INSERT INTO `m_role` VALUES ('a14809d9e4304c9abbde313eff76870b', '超级管理员', '超级管理员', '2020-04-27', 'admin', '2020-04-27', 'admin');
INSERT INTO `m_role` VALUES ('e3ac680dd32041699396ed17d7b48b4e', '系统管理员', '系统管理员', '2020-04-27', 'admin', '2020-04-27', 'admin');
INSERT INTO `m_role` VALUES ('fca894e736b34224bf4dd649d6a4d430', '产品经理', '产品经理', '2020-04-27', 'admin', '2020-04-27', 'admin');

-- ----------------------------
-- Table structure for m_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `m_role_permission`;
CREATE TABLE `m_role_permission`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色id',
  `permission_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限id',
  `created_time` date DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `updated_time` date DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of m_role_permission
-- ----------------------------
INSERT INTO `m_role_permission` VALUES ('2c08c875627042cfaf8acc2179b87d2b', 'fca894e736b34224bf4dd649d6a4d430', 'ff5bc36774c649ccab3849212b32c38f', '2020-04-27', 'admin', '2020-04-27', 'admin');
INSERT INTO `m_role_permission` VALUES ('2c7c2746dc734389b3121d1774e6dc1e', '1b6e6de8aab940308ef18924169fa93f', '14e0fb1dc6b947e7b28949b455a60cee', '2020-04-27', 'admin', '2020-04-27', 'admin');
INSERT INTO `m_role_permission` VALUES ('336fa4768ed84c4c8a605c6beb9b9e31', 'e3ac680dd32041699396ed17d7b48b4e', '14e0fb1dc6b947e7b28949b455a60cee', '2020-04-27', 'admin', '2020-04-27', 'admin');
INSERT INTO `m_role_permission` VALUES ('4441bff8e8994854829cf5527b7bb55d', 'fca894e736b34224bf4dd649d6a4d430', 'd3c3add48e164c95a2ce8e3dd87fb461', '2020-04-27', 'admin', '2020-04-27', 'admin');
INSERT INTO `m_role_permission` VALUES ('56d5b891d44945ad8b949377188e049a', 'a14809d9e4304c9abbde313eff76870b', 'ff5bc36774c649ccab3849212b32c38f', '2020-04-27', 'admin', '2020-04-27', 'admin');
INSERT INTO `m_role_permission` VALUES ('57fa0220c22e45beb8f0a312d3248f84', '1060f2139a944b0e85415a418293c001', 'd3c3add48e164c95a2ce8e3dd87fb461', '2020-04-27', 'admin', '2020-04-27', 'admin');
INSERT INTO `m_role_permission` VALUES ('58011387dc9648e08e877c481273bd45', 'a14809d9e4304c9abbde313eff76870b', 'd3c3add48e164c95a2ce8e3dd87fb461', '2020-04-27', 'admin', '2020-04-27', 'admin');
INSERT INTO `m_role_permission` VALUES ('5b2c68e21a154764962b389fe0c698f6', '590509e490a04c848780bf95a0b8abf1', 'ff5bc36774c649ccab3849212b32c38f', '2020-04-27', 'admin', '2020-04-27', 'admin');
INSERT INTO `m_role_permission` VALUES ('5cd23328deeb4f0985702356c2d1aef6', '1b6e6de8aab940308ef18924169fa93f', 'd3c3add48e164c95a2ce8e3dd87fb461', '2020-04-27', 'admin', '2020-04-27', 'admin');
INSERT INTO `m_role_permission` VALUES ('5dbbc928c0aa4a228ed823e0efff041e', '14399cafa2474514b5fdcc7e7d57435a', '14e0fb1dc6b947e7b28949b455a60cee', '2020-04-27', 'admin', '2020-04-27', 'admin');
INSERT INTO `m_role_permission` VALUES ('6874aca5e1b84asgsadgergvsdggvd', '6874aca5e1b84ac78759a1b56eee37d5', 'd3c3add48e164c95a2ce8e3dd87fb461', '2020-04-27', 'admin', '2020-04-27', 'admin');
INSERT INTO `m_role_permission` VALUES ('74421822e8f842b782f994c907839f80', 'e3ac680dd32041699396ed17d7b48b4e', 'ff5bc36774c649ccab3849212b32c38f', '2020-04-27', 'admin', '2020-04-27', 'admin');
INSERT INTO `m_role_permission` VALUES ('789c75b9b7854dbca3d9dcd2e3e48bd9', '590509e490a04c848780bf95a0b8abf1', '14e0fb1dc6b947e7b28949b455a60cee', '2020-04-27', 'admin', '2020-04-27', 'admin');
INSERT INTO `m_role_permission` VALUES ('8800d43cd4b24fbc8bf76826817be5bf', '14399cafa2474514b5fdcc7e7d57435a', 'd3c3add48e164c95a2ce8e3dd87fb461', '2020-04-27', 'admin', '2020-04-27', 'admin');
INSERT INTO `m_role_permission` VALUES ('a612a94bfbed46f38b4e91d1f6d4ee80', '1060f2139a944b0e85415a418293c001', 'ff5bc36774c649ccab3849212b32c38f', '2020-04-27', 'admin', '2020-04-27', 'admin');
INSERT INTO `m_role_permission` VALUES ('b6514fabcb1148f3a7feb5eaead02c38', 'a14809d9e4304c9abbde313eff76870b', '14e0fb1dc6b947e7b28949b455a60cee', '2020-04-27', 'admin', '2020-04-27', 'admin');
INSERT INTO `m_role_permission` VALUES ('cfded68e50de4dd588aa120911d728dd', '1060f2139a944b0e85415a418293c001', '14e0fb1dc6b947e7b28949b455a60cee', '2020-04-27', 'admin', '2020-04-27', 'admin');
INSERT INTO `m_role_permission` VALUES ('ed1161fd0398421780e4523efe69442d', '14399cafa2474514b5fdcc7e7d57435a', 'ff5bc36774c649ccab3849212b32c38f', '2020-04-27', 'admin', '2020-04-27', 'admin');
INSERT INTO `m_role_permission` VALUES ('f02bf1f4c07849f7b1e3e503365a1587', 'e3ac680dd32041699396ed17d7b48b4e', 'd3c3add48e164c95a2ce8e3dd87fb461', '2020-04-27', 'admin', '2020-04-27', 'admin');
INSERT INTO `m_role_permission` VALUES ('fbb59053d0dc4f76883d8690e46c63a8', '590509e490a04c848780bf95a0b8abf1', 'd3c3add48e164c95a2ce8e3dd87fb461', '2020-04-27', 'admin', '2020-04-27', 'admin');
INSERT INTO `m_role_permission` VALUES ('fsdgdsff5bc36774c649ccab3849212', '6874aca5e1b84ac78759a1b56eee37d5', 'ff5bc36774c649ccab3849212b32c38f', '2020-04-27', 'admin', '2020-04-27', 'admin');

-- ----------------------------
-- Table structure for m_user
-- ----------------------------
DROP TABLE IF EXISTS `m_user`;
CREATE TABLE `m_user`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `user_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机号',
  `salt` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '盐值',
  `created_time` date DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `updated_time` date DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of m_user
-- ----------------------------
INSERT INTO `m_user` VALUES ('07927cd846fb42b996082f216f69cb46', 'root', 'e96749114a8244564a7bea086a8a2b5f', 1, 'voyageroot@163.xom', '18293829525', 'QFRR1E5IBrStYcHNUvvw6Q==', '2020-04-24', 'admin', '2020-04-25', 'admin');
INSERT INTO `m_user` VALUES ('978f273ee4b9406ba0b07aac047b11af', 'admin', '10fa78ea51dd5497c71b5bf7bfb17c7c', 1, 'admin@voyage.com', '15021092903', '/i+IsHOluMcs3nMX5idRPw==', '2020-04-25', 'admin', '2020-04-25', 'admin');
INSERT INTO `m_user` VALUES ('9e92dde8a16b4dde879f4a4d3be55d43', 'voyage', '2a28113ea86807cc494b396b291a292b', 1, 'voyage@fujfu.com', '13034126585', '7JAPx1ztq2/0wMqU+q4mCw==', '2020-04-25', 'admin', '2020-04-25', 'admin');
INSERT INTO `m_user` VALUES ('e19328fe292b415ca621565fe5436090', 'fujfu', '380783cf860ff4819153d1722c4f2ff2', 1, 'fshgdsjj@fgj.com', '15031256985', 'WU4J5kNUKtwAxJ68AoaOtw==', '2020-04-25', 'admin', '2020-04-25', 'admin');

-- ----------------------------
-- Table structure for m_user_role
-- ----------------------------
DROP TABLE IF EXISTS `m_user_role`;
CREATE TABLE `m_user_role`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户id',
  `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色id',
  `created_time` date DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `updated_time` date DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
