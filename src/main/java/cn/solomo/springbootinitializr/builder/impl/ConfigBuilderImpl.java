package cn.solomo.springbootinitializr.builder.impl;

import cn.solomo.springbootinitializr.builder.BaseBuilder;
import cn.solomo.springbootinitializr.builder.model.ApplicationInfo;
import cn.solomo.springbootinitializr.configure.PropertiesConfig;
import java.io.File;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author solom
 * @ClassName ConfigBuilderImpl
 * @Description
 * @Version 1.0.0
 * @create 2022-02-21 14:07
 */
@Slf4j
@Service
public class ConfigBuilderImpl extends BaseBuilder {
  @Autowired
  private RedisConfigBuilderImpl redisConfigBuilder;
  @Autowired
  private JwtBuilderImpl jwtBuilder;
  @Autowired
  private SwaggerBuilderImpl swaggerBuilder;

  public void generation(PropertiesConfig config, String projectsRoot) throws Exception {
    redisConfigBuilder.generation(config, projectsRoot);
    ApplicationInfo info = new ApplicationInfo();
    info.setPackageName(config.getPackageName()+".config");
    info.setConfig(config);
    String packagePath = config.getPackageName().replace(".", "/") + "/config/";
    File file = new File(projectsRoot + "/src/main/java/" + packagePath, "ControllerResponseBodyAdvice.java");
    // 写入文件
    super.writeFile(file, "controller_response_body_advice.ftl", info);
    swaggerBuilder.generation(config, projectsRoot);

  }
}
