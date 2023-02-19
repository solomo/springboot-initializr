package cn.solomo.springbootinitializr.builder.impl;

import cn.solomo.springbootinitializr.builder.BaseBuilder;
import cn.solomo.springbootinitializr.builder.model.ApplicationInfo;
import cn.solomo.springbootinitializr.builder.model.CustomMetadataInfo;
import cn.solomo.springbootinitializr.configure.PropertiesConfig;
import com.google.common.base.CaseFormat;
import java.io.File;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author solom
 * @ClassName SecurityBuilderImpl
 * @Description
 * @Version 1.0.0
 * @create 2022-02-21 14:17
 */
@Slf4j
@Service
public class SecurityBuilderImpl extends BaseBuilder {

  public void generation(PropertiesConfig config, String projectsRoot) throws Exception {
    ApplicationInfo info = new ApplicationInfo();
    info.setPackageName(config.getPackageName()+".security");
    info.setConfig(config);
    String packagePath = config.getPackageName().replace(".", "/") + "/security/";
    File file = new File(projectsRoot + "/src/main/java/" + packagePath, "UserEntity.java");
    // 写入文件
    super.writeFile(file, "security/user_entity.ftl", info);
    file = new File(projectsRoot + "/src/main/java/" + packagePath, "AccessToken.java");
    super.writeFile(file, "security/access_token.ftl", info);
    //custom_metadata_source.ftl
    file = new File(projectsRoot + "/src/main/java/" + packagePath, "CustomMetadataSource.java");
		CustomMetadataInfo customMetaInfo = new CustomMetadataInfo();
		customMetaInfo.setPackageName(config.getPackageName()+".security");
		customMetaInfo.setConfig(config.getConfig());
		customMetaInfo.setMenuEntity(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, config.getConfig().getArtifactId()+"_menu"));
		customMetaInfo.setRoleEntity(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, config.getConfig().getArtifactId()+"_role"));
		customMetaInfo.setRoleMenusEntity(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, config.getConfig().getArtifactId()+"_role_menus"));
		customMetaInfo.setUserRolesEntity(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, config.getConfig().getArtifactId()+"_user_roles"));
		customMetaInfo.setMenuService(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "i_"+config.getConfig().getArtifactId()+"_menu_service"));
		customMetaInfo.setRoleMenusService(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "i_"+config.getConfig().getArtifactId()+"_role_menus_service"));
		customMetaInfo.setUserRolesService(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "i_"+config.getConfig().getArtifactId()+"_user_roles_service"));
		customMetaInfo.setRoleService(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "i_"+config.getConfig().getArtifactId()+"_role_service"));
		customMetaInfo.setUserRolesService(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "i_"+config.getConfig().getArtifactId()+"_user_roles_service"));
		super.writeFile(file, "security/custom_metadata_source.ftl", customMetaInfo);
    //my_authentication_failure_handler.ftl
    file = new File(projectsRoot + "/src/main/java/" + packagePath, "MyAuthenticationFailureHandler.java");
    super.writeFile(file, "security/my_authentication_failure_handler.ftl", info);
    //my_authentication_sucess_handler.ftl
    file = new File(projectsRoot + "/src/main/java/" + packagePath, "MyAuthenticationSucessHandler.java");
    super.writeFile(file, "security/my_authentication_sucess_handler.ftl", info);
    //url_access_decision_manager.ftl
    file = new File(projectsRoot + "/src/main/java/" + packagePath, "UrlAccessDecisionManager.java");
    super.writeFile(file, "security/url_access_decision_manager.ftl", info);
    //validate_code_filter.ftl
    file = new File(projectsRoot + "/src/main/java/" + packagePath, "ValidateCodeFilter.java");
    super.writeFile(file, "security/validate_code_filter.ftl", info);
  }
}
