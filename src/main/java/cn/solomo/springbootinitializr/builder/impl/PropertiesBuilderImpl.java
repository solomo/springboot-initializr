package cn.solomo.springbootinitializr.builder.impl;

import cn.solomo.springbootinitializr.builder.BaseBuilder;
import cn.solomo.springbootinitializr.configure.PropertiesConfig;
import java.io.File;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author solom
 * @ClassName GenerationProperties
 * @Description
 * @Version 1.0.0
 * @create 2022-02-17 18:16
 */
@Service
@Slf4j
public class PropertiesBuilderImpl extends BaseBuilder {

  public void generation(PropertiesConfig config, String projectsRoot) throws Exception {
    File file = new File(projectsRoot + "/src/main/resources/", "application.properties");
    // 写入文件
    super.writeFile(file, "application.properties.ftl", config);
    log.info("创建配置文件 application.properties {}", file.getPath());
  }
}
