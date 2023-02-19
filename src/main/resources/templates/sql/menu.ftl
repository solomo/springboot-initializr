DROP TABLE IF EXISTS `${artifactId}_menu`;
CREATE TABLE `${artifactId}_menu`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'menu id',
  `menu_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `url` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单地址',
  `path` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '菜单路径',
  `parent_id` int NULL DEFAULT NULL COMMENT '父级id',
  `enabled` tinyint(1) NULL DEFAULT 1 COMMENT '是否启用，0：不启用，1：启用',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单' ROW_FORMAT = Dynamic;

INSERT INTO `${artifactId}_menu` VALUES (1, '所有', '/**', '/', NULL, 1, now());
INSERT INTO `${artifactId}_menu` VALUES (2, '首页', '/index.html', '/', 1, 1, now());