package cn.solomo.springbootinitializr.builder.impl;

import cn.solomo.springbootinitializr.builder.BaseBuilder;
import cn.solomo.springbootinitializr.configure.PropertiesConfig;
import java.io.File;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author solom
 * @ClassName CodeGeneratorBuilderImpl
 * @Description
 * @Version 1.0.0
 * @create 2022-02-22 16:13
 */
@Slf4j
@Service
public class CodeGeneratorBuilderImpl extends BaseBuilder {

  public void generation(PropertiesConfig config, String projectsRoot) throws Exception {
    String packagePath = config.getPackageName().replace(".", "/") + "/";
    File file = new File(projectsRoot + "/src/main/java/" + packagePath, "CodeGenerator.java");
    // 写入文件
    super.writeFile(file, "code_generator.ftl", config);
    log.info("创建 CodeGenerator.java {}", file.getPath());

    file = new File(projectsRoot + "/src/main/resources/");
    file.mkdir();

    packagePath = config.getPackageName().replace(".", "/") + "/common/";
    file = new File(projectsRoot + "/src/main/java/" + packagePath, "BeanConverter.java");
    super.writeFile(file, "code/common/bean_converter.ftl", config);

    packagePath = config.getPackageName().replace(".", "/") + "/entity/convert/";
    file = new File(projectsRoot + "/src/main/java/" + packagePath, "Convert.java");
    super.writeFile(file, "code/entity/convert.ftl", config);

    packagePath = config.getPackageName().replace(".", "/") + "/modelmapper/jdk8/";
    file = new File(projectsRoot + "/src/main/java/" + packagePath, "FromOptionalConverter.java");
    super.writeFile(file, "code/modelmapper/jdk8/from_optional_converter.ftl", config);

    packagePath = config.getPackageName().replace(".", "/") + "/modelmapper/jdk8/";
    file = new File(projectsRoot + "/src/main/java/" + packagePath, "Jdk8Module.java");
    super.writeFile(file, "code/modelmapper/jdk8/jdk8_module.ftl", config);

    packagePath = config.getPackageName().replace(".", "/") + "/modelmapper/jdk8/";
    file = new File(projectsRoot + "/src/main/java/" + packagePath, "ToOptionalConverter.java");
    super.writeFile(file, "code/modelmapper/jdk8/to_optional_converter.ftl", config);

    packagePath = config.getPackageName().replace(".", "/") + "/modelmapper/jsr310/";
    file = new File(projectsRoot + "/src/main/java/" + packagePath, "FromTemporalConverter.java");
    super.writeFile(file, "code/modelmapper/jsr310/from_temporal_converter.ftl", config);

    packagePath = config.getPackageName().replace(".", "/") + "/modelmapper/jsr310/";
    file = new File(projectsRoot + "/src/main/java/" + packagePath, "Jsr310ModuleConfig.java");
    super.writeFile(file, "code/modelmapper/jsr310/jsr310_module_config.ftl", config);

    packagePath = config.getPackageName().replace(".", "/") + "/modelmapper/jsr310/";
    file = new File(projectsRoot + "/src/main/java/" + packagePath, "Jsr310Module.java");
    super.writeFile(file, "code/modelmapper/jsr310/jsr310_module.ftl", config);

    packagePath = config.getPackageName().replace(".", "/") + "/modelmapper/jsr310/";
    file = new File(projectsRoot + "/src/main/java/" + packagePath, "TemporalToTemporalConverter.java");
    super.writeFile(file, "code/modelmapper/jsr310/temporal_to_temporal_converter.ftl", config);

    packagePath = config.getPackageName().replace(".", "/") + "/modelmapper/jsr310/";
    file = new File(projectsRoot + "/src/main/java/" + packagePath, "ToTemporalConverter.java");
    super.writeFile(file, "code/modelmapper/jsr310/to_temporal_converter.ftl", config);
    String command = "cmd /c mvn spring-boot:run";
    String os = System.getProperty("os.name");
    if(!os.toLowerCase().startsWith("win")) {
      command = "sh -c mvn spring-boot:run";
    }
    Process p = Runtime.getRuntime().exec(command, null , new File(projectsRoot));
    log.info("command:{}, {}", command, p);
  }
}
