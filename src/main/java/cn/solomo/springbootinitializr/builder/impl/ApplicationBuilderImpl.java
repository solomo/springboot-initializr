package cn.solomo.springbootinitializr.builder.impl;

import cn.solomo.springbootinitializr.builder.BaseBuilder;
import cn.solomo.springbootinitializr.configure.PropertiesConfig;
import java.io.File;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author solom
 * @ClassName ApplicationBuilderImpl
 * @Description
 * @Version 1.0.0
 * @create 2022-02-18 17:34
 */
@Slf4j
@Service
public class ApplicationBuilderImpl extends BaseBuilder {

  public void generation(PropertiesConfig config, String projectsRoot) throws Exception {
    //启动类名称
    String[] split = config.getArtifactId().split("-");
    StringBuffer applicationJavaName = new StringBuffer();
    Arrays.asList(split).forEach(s -> {
      applicationJavaName.append(s.substring(0, 1).toUpperCase() + s.substring(1));
    });
    applicationJavaName.append("Application");
    config.setApplicationJavaName(applicationJavaName.toString());
    String packagePath = config.getPackageName().replace(".", "/") + "/";
    File file = new File(projectsRoot + "/src/main/java/" + packagePath, applicationJavaName + ".java");
    // 写入文件
    super.writeFile(file, "application.ftl", config);
    log.info("创建主入口类 Application.java {}", file.getPath());
  }
}
