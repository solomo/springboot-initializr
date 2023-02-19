package cn.solomo.springbootinitializr.builder.impl;

import cn.solomo.springbootinitializr.builder.BaseBuilder;
import cn.solomo.springbootinitializr.configure.PropertiesConfig;
import java.io.File;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author solom
 * @ClassName PomBuilderImpl
 * @Description
 * @Version 1.0.0
 * @create 2022-02-18 17:42
 */
@Slf4j
@Service
public class PomBuilderImpl extends BaseBuilder {

  public void generation(PropertiesConfig config, String projectsRoot, String tmpFile) throws Exception {
    File file = new File(projectsRoot, "pom.xml");
    // 写入文件
    super.writeFile(file, tmpFile, config);
    log.info("创建配置文件 pom.xml {}", file.getPath());
  }
}
