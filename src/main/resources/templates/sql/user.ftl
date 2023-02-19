DROP TABLE IF EXISTS `${artifactId}_user`;
CREATE TABLE `${artifactId}_user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `login_name` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '登录账号',
  `login_password` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '登录密码',
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '姓名',
  `mobile` varchar(11) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `name_idx` (`name`) COMMENT '名称索引',
  KEY `mobile_idx` (`mobile`) COMMENT '手机号索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='登录用户表';

INSERT INTO `${artifactId}_user` VALUES (1, 'admin', '$2a$10$mtwnODXNH20Zl8EbT.5vWOZd/sYQCzOI0N5et92xtscn3J/DDY7f.', '${artifactId}', '', now());