package cn.solomo.springbootinitializr.builder.model;

import cn.solomo.springbootinitializr.configure.PropertiesConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author solom
 * @ClassName CustomMetadataInfo
 * @Description
 * @Version 1.0.0
 * @create 2022-02-24 15:37
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomMetadataInfo {
	private PropertiesConfig config;
	private String packageName;
	private String menuEntity;
	private String roleEntity;
	private String roleMenusEntity;
	private String userRolesEntity;
	private String menuService;
	private String roleMenusService;
	private String userRoleEntity;
	private String roleService;
	private String userRolesService;
}
