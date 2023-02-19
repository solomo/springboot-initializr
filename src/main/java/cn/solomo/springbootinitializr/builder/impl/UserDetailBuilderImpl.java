package cn.solomo.springbootinitializr.builder.impl;

import cn.solomo.springbootinitializr.builder.BaseBuilder;
import cn.solomo.springbootinitializr.builder.model.UserDetailInfo;
import cn.solomo.springbootinitializr.configure.PropertiesConfig;
import com.google.common.base.CaseFormat;
import java.io.File;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author solom
 * @ClassName UserDetailBuilderImpl
 * @Description
 * @Version 1.0.0
 * @create 2022-02-24 14:14
 */
@Slf4j
@Service
public class UserDetailBuilderImpl extends BaseBuilder {
	
	public void generation(PropertiesConfig config, String projectsRoot) throws Exception {
		UserDetailInfo info = new UserDetailInfo();
		info.setPackageName(config.getPackageName()+".web.service");
		info.setConfig(config);
		info.setUserEntity(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, config.getArtifactId()+"_user"));
		info.setUserRoleEntity(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, config.getArtifactId()+"_user_roles"));
		info.setRoleService(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "i_"+config.getArtifactId()+"_role_service"));
		info.setUserRolesService(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "i_"+config.getArtifactId()+"_user_roles_service"));
		info.setUserService(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "i_"+config.getArtifactId()+"_user_service"));
		
		String packagePath = info.getPackageName().replace(".", "/");
		File file = new File(projectsRoot + "/src/main/java/" + packagePath, "UserDetailsServiceImpl.java");
		// 写入文件
		super.writeFile(file, "security/user_detail_service.ftl", info);
	}
}
