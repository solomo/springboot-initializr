package cn.solomo.springbootinitializr.builder.impl;

import cn.solomo.springbootinitializr.builder.BaseBuilder;
import cn.solomo.springbootinitializr.builder.model.ApplicationInfo;
import cn.solomo.springbootinitializr.configure.PropertiesConfig;
import java.io.File;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author solom
 * @ClassName PackageBuilder
 * @Description
 * @Version 1.0.0
 * @create 2022-03-01 10:56
 */
@Slf4j
@Service
public class PackageBuilder extends BaseBuilder {
	
	public void generation(PropertiesConfig config, String projectsRoot) throws Exception {
		ApplicationInfo info = new ApplicationInfo();
		info.setPackageName(config.getPackageName()+".controller");
		info.setConfig(config);
		String packagePath = config.getPackageName().replace(".", "/") + "/controller/";
		File file = new File(projectsRoot + "/src/main/java/" + packagePath, "package-info.java");
		super.writeFile(file, "package-info.ftl", info);
		info.setPackageName(config.getPackageName()+".exception");
		packagePath = config.getPackageName().replace(".", "/") + "/exception/";
		file = new File(projectsRoot + "/src/main/java/" + packagePath, "package-info.java");
		super.writeFile(file, "package-info.ftl", info);
	}
}
