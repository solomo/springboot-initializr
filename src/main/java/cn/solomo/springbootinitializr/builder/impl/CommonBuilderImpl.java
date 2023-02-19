package cn.solomo.springbootinitializr.builder.impl;

import cn.solomo.springbootinitializr.builder.BaseBuilder;
import cn.solomo.springbootinitializr.builder.model.ApplicationInfo;
import cn.solomo.springbootinitializr.configure.PropertiesConfig;
import java.io.File;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author solom
 * @ClassName CommonBuilderImpl
 * @Description
 * @Version 1.0.0
 * @create 2022-02-21 11:01
 */
@Slf4j
@Service
public class CommonBuilderImpl extends BaseBuilder {

  public void generation(PropertiesConfig config, String projectsRoot) throws Exception {
    ApplicationInfo info = new ApplicationInfo();
    info.setPackageName(config.getPackageName()+".common");
    String packagePath = config.getPackageName().replace(".", "/") + "/common/";
    File file = new File(projectsRoot + "/src/main/java/" + packagePath, "Common.java");
    // 写入文件
    super.writeFile(file, "common/common.ftl", info);
    file = new File(projectsRoot + "/src/main/java/" + packagePath, "Msg.java");
    // 写入文件
    super.writeFile(file, "common/msg.ftl", info);
    file = new File(projectsRoot + "/src/main/java/" + packagePath, "ImageCode.java");
    // 写入文件
    super.writeFile(file, "common/image_code.ftl", info);
  }
}
