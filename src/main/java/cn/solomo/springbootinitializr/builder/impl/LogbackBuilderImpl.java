package cn.solomo.springbootinitializr.builder.impl;

import cn.solomo.springbootinitializr.builder.BaseBuilder;
import cn.solomo.springbootinitializr.configure.PropertiesConfig;
import java.io.File;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author solom
 * @ClassName LogbackBuilderImpl
 * @Description
 * @Version 1.0.0
 * @create 2022-02-21 10:18
 */
@Slf4j
@Service
public class LogbackBuilderImpl extends BaseBuilder {

  public void generation(PropertiesConfig config, String projectsRoot) throws Exception {
    File file = new File(projectsRoot + "/src/main/resources/", "logback-spring.xml");
    // 写入文件
    super.writeFile(file, "logback-spring.ftl", config);
    log.info("创建配置文件 logback-spring.xml {}", file.getPath());
  }
}
