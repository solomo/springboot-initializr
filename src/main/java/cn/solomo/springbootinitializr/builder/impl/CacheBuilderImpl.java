package cn.solomo.springbootinitializr.builder.impl;

import cn.solomo.springbootinitializr.builder.BaseBuilder;
import cn.solomo.springbootinitializr.builder.model.ApplicationInfo;
import cn.solomo.springbootinitializr.configure.PropertiesConfig;
import java.io.File;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author solom
 * @ClassName CacheBuilderImpl
 * @Description
 * @Version 1.0.0
 * @create 2022-02-21 13:59
 */
@Slf4j
@Service
public class CacheBuilderImpl extends BaseBuilder {

  public void generation(PropertiesConfig config, String projectsRoot) throws Exception {
    ApplicationInfo info = new ApplicationInfo();
    info.setPackageName(config.getPackageName()+".config");
    String packagePath = config.getPackageName().replace(".", "/") + "/config/";
    File file = new File(projectsRoot + "/src/main/java/" + packagePath, "Cache.java");
    // 写入文件
    super.writeFile(file, "cache/cache.ftl", info);
    file = new File(projectsRoot + "/src/main/java/" + packagePath, "CaffeineCache.java");
    super.writeFile(file, "cache/caffeine_cache.ftl", info);
    log.info("创建cache配置类 Cache.java {}", file.getPath());
  }
}
