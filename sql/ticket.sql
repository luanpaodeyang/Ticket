/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80035 (8.0.35)
 Source Host           : localhost:3306
 Source Schema         : ticket

 Target Server Type    : MySQL
 Target Server Version : 80035 (8.0.35)
 File Encoding         : 65001

 Date: 09/01/2024 16:08:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `id_card` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `show_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `quantity` int NULL DEFAULT NULL,
  `amount` int NULL DEFAULT NULL,
  `home_id` bigint NULL DEFAULT NULL,
  `ticket_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK4g1wodr2xijearemkgm47mn9a`(`ticket_id` ASC) USING BTREE,
  INDEX `FKraklwdmekfx4v8xsdf7lgx8lb`(`home_id` ASC) USING BTREE,
  CONSTRAINT `FK4g1wodr2xijearemkgm47mn9a` FOREIGN KEY (`ticket_id`) REFERENCES `home` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKraklwdmekfx4v8xsdf7lgx8lb` FOREIGN KEY (`home_id`) REFERENCES `home` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES (1, '韩飞', '610623200303151812', '邓紫棋I AM GLORIA 演唱会-泉州站', 3, 4500, 4, NULL);
INSERT INTO `customer` VALUES (2, '张三', '60623200001021234', '邓紫棋I AM GLORIA 演唱会-泉州站', 3, 4500, 4, NULL);
INSERT INTO `customer` VALUES (3, 'han', '123', '演唱会5', 2, 850, 5, NULL);

-- ----------------------------
-- Table structure for home
-- ----------------------------
DROP TABLE IF EXISTS `home`;
CREATE TABLE `home`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `show_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `performers` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `show_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `total_tickets` int UNSIGNED NULL DEFAULT NULL,
  `sold_tickets` int UNSIGNED NULL DEFAULT NULL,
  `remaining_tickets` int UNSIGNED NULL DEFAULT NULL,
  `price` int UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of home
-- ----------------------------
INSERT INTO `home` VALUES (1, '薛之谦天外来物巡回演唱会-澳门站', '薛之谦', '2023年12月8日-10日', 30000, 1200, 28800, 1717);
INSERT INTO `home` VALUES (2, '周杰伦2024巡回演唱会-福州站', '周杰伦', '2024年5月16日', 30000, 5, 29995, 2000);
INSERT INTO `home` VALUES (3, '林俊杰世界巡回演唱会-南宁站', '林俊杰', '2023年12月9日-10日', 25000, 15000, 10000, 1820);
INSERT INTO `home` VALUES (4, '邓紫棋I AM GLORIA 演唱会-泉州站', '邓紫棋', '2024年1月20日', 35000, 15006, 1994, 1500);
INSERT INTO `home` VALUES (5, '演唱会5', '演员5', '2023年10月30日', 10, 9, 1, 425);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `invitation_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '151821', NULL);
INSERT INTO `user` VALUES (2, 'test', '12345678', NULL);

SET FOREIGN_KEY_CHECKS = 1;
