package cn.solomo.springbootinitializr.builder.impl;

import cn.solomo.springbootinitializr.builder.BaseBuilder;
import cn.solomo.springbootinitializr.builder.model.ApplicationInfo;
import cn.solomo.springbootinitializr.configure.PropertiesConfig;
import java.io.File;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author solom
 * @ClassName ControllerBuilderImpl
 * @Description
 * @Version 1.0.0
 * @create 2022-02-21 15:21
 */
@Slf4j
@Service
public class ControllerBuilderImpl extends BaseBuilder {

  public void generation(PropertiesConfig config, String projectsRoot) throws Exception {
    ApplicationInfo info = new ApplicationInfo();
    info.setPackageName(config.getPackageName()+".exception");
    info.setConfig(config);
    String packagePath = config.getPackageName().replace(".", "/") + "/exception/";
    //validate_code_exception.ftl
    File file = new File(projectsRoot + "/src/main/java/" + packagePath, "ValidateCodeException.java");
    super.writeFile(file, "exception/validate_code_exception.ftl", info);
    info.setPackageName(config.getPackageName()+".controller");
    packagePath = config.getPackageName().replace(".", "/") + "/controller/";
    file = new File(projectsRoot + "/src/main/java/" + packagePath, "ValidateController.java");
    super.writeFile(file, "controller/validate_controller.ftl", info);
  }
}
