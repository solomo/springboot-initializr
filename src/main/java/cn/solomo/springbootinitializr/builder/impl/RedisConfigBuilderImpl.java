package cn.solomo.springbootinitializr.builder.impl;

import cn.solomo.springbootinitializr.builder.BaseBuilder;
import cn.solomo.springbootinitializr.builder.model.ApplicationInfo;
import cn.solomo.springbootinitializr.configure.PropertiesConfig;
import java.io.File;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author solom
 * @ClassName RedisConfigBuilderImpl
 * @Description
 * @Version 1.0.0
 * @create 2022-02-21 10:51
 */
@Slf4j
@Service
public class RedisConfigBuilderImpl extends BaseBuilder {

  public void generation(PropertiesConfig config, String projectsRoot) throws Exception {
    ApplicationInfo info = new ApplicationInfo();
    info.setPackageName(config.getPackageName()+".config");
    info.setConfig(config);

    String packagePath = config.getPackageName().replace(".", "/") + "/config/";
    File file = new File(projectsRoot + "/src/main/java/" + packagePath, "RedisConfig.java");
    // 写入文件
    super.writeFile(file, "redis/redis.ftl", info);
    file = new File(projectsRoot + "/src/main/java/" + packagePath, "GsonRedisSerializer.java");
    super.writeFile(file, "redis/gson.ftl", info);
    log.info("创建redis配置类 RedisConfig.java {}", file.getPath());
  }
}
