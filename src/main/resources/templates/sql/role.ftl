DROP TABLE IF EXISTS `${artifactId}_role`;
CREATE TABLE `${artifactId}_role`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'role id',
  `role_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色名',
  `create_by` int NULL DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色权限名称' ROW_FORMAT = Dynamic;

INSERT INTO `${artifactId}_role` VALUES (1, '管理员', 1, now());