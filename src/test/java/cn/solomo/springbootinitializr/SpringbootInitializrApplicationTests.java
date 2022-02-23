package cn.solomo.springbootinitializr;

import cn.solomo.springbootinitializr.builder.impl.ApplicationBuilderImpl;
import cn.solomo.springbootinitializr.builder.impl.CacheBuilderImpl;
import cn.solomo.springbootinitializr.builder.impl.CodeGeneratorBuilderImpl;
import cn.solomo.springbootinitializr.builder.impl.CommonBuilderImpl;
import cn.solomo.springbootinitializr.builder.impl.ConfigBuilderImpl;
import cn.solomo.springbootinitializr.builder.impl.ControllerBuilderImpl;
import cn.solomo.springbootinitializr.builder.impl.LogbackBuilderImpl;
import cn.solomo.springbootinitializr.builder.impl.PomBuilderImpl;
import cn.solomo.springbootinitializr.builder.impl.PropertiesBuilderImpl;
import cn.solomo.springbootinitializr.builder.impl.RedisConfigBuilderImpl;
import cn.solomo.springbootinitializr.builder.impl.SqlBuilderImpl;
import cn.solomo.springbootinitializr.configure.PropertiesConfig;
import cn.solomo.springbootinitializr.configure.PropertiesConfig.Datasource;
import cn.solomo.springbootinitializr.configure.PropertiesConfig.Mysql;
import cn.solomo.springbootinitializr.configure.PropertiesConfig.Redis;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootInitializrApplicationTests {

  @Autowired
  private PropertiesBuilderImpl propertiesBuilder;
  @Autowired
  private ApplicationBuilderImpl applicationBuilder;
  @Autowired
  private PomBuilderImpl pomBuilder;
  @Autowired
  private LogbackBuilderImpl logbackBuilder;
  @Autowired
  private CommonBuilderImpl commonBuilder;
  @Autowired
  private ConfigBuilderImpl configBuilder;
  @Autowired
  private ControllerBuilderImpl controllerBuilder;
  @Autowired
  private SqlBuilderImpl sqlBuilder;
  @Autowired
  private CodeGeneratorBuilderImpl codeGeneratorBuilder;

  @Test
  void contextLoads() throws Exception {
    URL resource = this.getClass().getResource("/");
    String projectsRoot = resource.getFile() + "projects/";
    PropertiesConfig propertiesConfig = new PropertiesConfig();
    propertiesConfig.setDatasource(Datasource.builder().mysql(Mysql.builder().url(
            "jdbc:mysql://xxx:3306/demo?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&zeroDateTimeBehavior=convertToNull")
        .username("root").password("admin**").build()).build());
    propertiesConfig.setRedis(Redis.builder().host("aa").password("bb").port("cc").database("1").build());
    propertiesConfig.setArtifactId("demo");
    propertiesConfig.setGroupId("cn.mg");
    propertiesConfig.setName("abc");
    propertiesConfig.setDescription("test");
    pomBuilder.generation(propertiesConfig, projectsRoot + propertiesConfig.getArtifactId(), "pom.ftl");

    PropertiesConfig repositoryConfig = new PropertiesConfig();
    repositoryConfig.setDatasource(propertiesConfig.getDatasource());
    repositoryConfig.setRedis(propertiesConfig.getRedis());
    repositoryConfig.setPackageName(propertiesConfig.getGroupId() + "." + propertiesConfig.getArtifactId() + ".repository");
    projectsRoot = resource.getFile() + "projects/";
    projectsRoot = projectsRoot + propertiesConfig.getArtifactId() + "/";
    repositoryConfig.setGroupId(propertiesConfig.getGroupId() + "." + propertiesConfig.getArtifactId());
    repositoryConfig.setArtifactId("repository");
    repositoryConfig.setName("repository");
    repositoryConfig.setDescription("repository");
    pomBuilder.generation(repositoryConfig, projectsRoot + repositoryConfig.getArtifactId(), "pom_repository.ftl");
    sqlBuilder.generation(propertiesConfig, projectsRoot + repositoryConfig.getArtifactId());
    File f = new File(projectsRoot + repositoryConfig.getArtifactId() + "/src/main/resources/entity.java.ftl");
    Path copied = f.toPath();
    Path originalPath = Paths.get("src/main/resources/templates/swagger/entity.java.ftl");
    Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING);

    codeGeneratorBuilder.generation(repositoryConfig, projectsRoot + repositoryConfig.getArtifactId());

    PropertiesConfig webConfig = new PropertiesConfig();
    webConfig.setDatasource(propertiesConfig.getDatasource());
    webConfig.setRedis(propertiesConfig.getRedis());
    webConfig.setPackageName(propertiesConfig.getGroupId() + "." + propertiesConfig.getArtifactId() + ".web");
    projectsRoot = resource.getFile() + "projects/";
    projectsRoot = projectsRoot + propertiesConfig.getArtifactId() + "/";
    webConfig.setGroupId(propertiesConfig.getGroupId() + "." + propertiesConfig.getArtifactId());
    webConfig.setArtifactId("web");
    webConfig.setName("web");
    webConfig.setDescription("web");

    applicationBuilder.generation(webConfig, projectsRoot + webConfig.getArtifactId());
    pomBuilder.generation(webConfig, projectsRoot + webConfig.getArtifactId(), "pom_web.ftl");
    propertiesBuilder.generation(webConfig, projectsRoot + webConfig.getArtifactId());
    logbackBuilder.generation(webConfig, projectsRoot + webConfig.getArtifactId());
    commonBuilder.generation(webConfig, projectsRoot + webConfig.getArtifactId());
    configBuilder.generation(webConfig, projectsRoot + webConfig.getArtifactId());
    controllerBuilder.generation(webConfig, projectsRoot + webConfig.getArtifactId());
  }

}
