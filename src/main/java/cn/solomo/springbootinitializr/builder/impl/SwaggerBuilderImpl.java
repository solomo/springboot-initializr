package cn.solomo.springbootinitializr.builder.impl;

import cn.solomo.springbootinitializr.builder.BaseBuilder;
import cn.solomo.springbootinitializr.builder.model.ApplicationInfo;
import cn.solomo.springbootinitializr.configure.PropertiesConfig;
import java.io.File;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author solom
 * @ClassName SwaggerBuilderImpl
 * @Description
 * @Version 1.0.0
 * @create 2022-02-21 14:59
 */
@Slf4j
@Service
public class SwaggerBuilderImpl extends BaseBuilder {

  public void generation(PropertiesConfig config, String projectsRoot) throws Exception {
    ApplicationInfo info = new ApplicationInfo();
    info.setPackageName(config.getPackageName()+".config");
    info.setConfig(config);
    String packagePath = config.getPackageName().replace(".", "/") + "/config/";
    File file = new File(projectsRoot + "/src/main/java/" + packagePath, "OpenApiConfig.java");
    // 写入文件
    super.writeFile(file, "swagger/open_api_config.ftl", info);
  }
}
