package cn.solomo.springbootinitializr.builder.model;

import cn.solomo.springbootinitializr.configure.PropertiesConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author solom
 * @ClassName UserDetailInfo
 * @Description
 * @Version 1.0.0
 * @create 2022-02-24 15:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailInfo {
	private PropertiesConfig config;
	private String packageName;
	private String className;
	private String userEntity;
	private String userRoleEntity;
	private String roleService;
	private String userRolesService;
	private String userService;
}
