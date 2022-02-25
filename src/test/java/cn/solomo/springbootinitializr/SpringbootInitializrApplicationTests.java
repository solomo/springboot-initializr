package cn.solomo.springbootinitializr;

import cn.solomo.springbootinitializr.builder.RepositoryBuilder;
import cn.solomo.springbootinitializr.builder.WebBuilder;
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
	private RepositoryBuilder repositoryBuilder;
  @Autowired
	private WebBuilder webBuilder;
	@Autowired
	private PomBuilderImpl pomBuilder;

  @Test
  void contextLoads() throws Exception {
    URL resource = this.getClass().getResource("/");
    String projectsRoot = resource.getFile() + "projects/";
    PropertiesConfig propertiesConfig = new PropertiesConfig();
    propertiesConfig.setDatasource(Datasource.builder().mysql(Mysql.builder().url(
            "jdbc:mysql://39.108.117.44:3306/demo?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&zeroDateTimeBehavior=convertToNull")
        .username("root").password("admin**").build()).build());
    propertiesConfig.setRedis(Redis.builder().host("aa").password("bb").port("cc").database("1").build());
    propertiesConfig.setArtifactId("demo");
    propertiesConfig.setGroupId("cn.mg");
    propertiesConfig.setDescription("test");
		propertiesConfig.setPackageName(propertiesConfig.getGroupId() + "." + propertiesConfig.getArtifactId());
		pomBuilder.generation(propertiesConfig, projectsRoot + propertiesConfig.getArtifactId(), "pom.ftl");
    repositoryBuilder.generation(propertiesConfig, projectsRoot);
		webBuilder.generation(propertiesConfig, projectsRoot);
  }

}
