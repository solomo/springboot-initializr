DROP TABLE IF EXISTS `${artifactId}_user_roles`;
CREATE TABLE `${artifactId}_user_roles`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int UNSIGNED NULL DEFAULT NULL COMMENT '用户表id',
  `role_id` int UNSIGNED NULL DEFAULT NULL COMMENT '角色表id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户对应角色权限数据' ROW_FORMAT = DYNAMIC;

INSERT INTO `${artifactId}_user_roles` VALUES (1, 1, 1);