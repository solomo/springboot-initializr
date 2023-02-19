DROP TABLE IF EXISTS `${artifactId}_role_menus`;
CREATE TABLE `${artifactId}_role_menus`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键，自增长',
  `rid` int UNSIGNED NOT NULL COMMENT '角色表id',
  `mid` int UNSIGNED NOT NULL COMMENT '菜单表id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色权限对应菜单关系' ROW_FORMAT = Dynamic;

INSERT INTO `${artifactId}_role_menus` VALUES (1, 1, 1);
INSERT INTO `${artifactId}_role_menus` VALUES (2, 1, 2);
