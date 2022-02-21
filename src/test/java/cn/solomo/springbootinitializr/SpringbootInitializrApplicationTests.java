package cn.solomo.springbootinitializr;

import cn.solomo.springbootinitializr.builder.impl.ApplicationBuilderImpl;
import cn.solomo.springbootinitializr.builder.impl.CacheBuilderImpl;
import cn.solomo.springbootinitializr.builder.impl.CommonBuilderImpl;
import cn.solomo.springbootinitializr.builder.impl.ConfigBuilderImpl;
import cn.solomo.springbootinitializr.builder.impl.ControllerBuilderImpl;
import cn.solomo.springbootinitializr.builder.impl.LogbackBuilderImpl;
import cn.solomo.springbootinitializr.builder.impl.PomBuilderImpl;
import cn.solomo.springbootinitializr.builder.impl.PropertiesBuilderImpl;
import cn.solomo.springbootinitializr.builder.impl.RedisConfigBuilderImpl;
import cn.solomo.springbootinitializr.configure.PropertiesConfig;
import cn.solomo.springbootinitializr.configure.PropertiesConfig.Datasource;
import cn.solomo.springbootinitializr.configure.PropertiesConfig.Mysql;
import cn.solomo.springbootinitializr.configure.PropertiesConfig.Redis;
import java.net.URL;
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

  @Test
  void contextLoads() throws Exception {
    URL resource = this.getClass().getResource("/");
    String projectsRoot = resource.getFile() + "/projects/";
    PropertiesConfig propertiesConfig = new PropertiesConfig();
    propertiesConfig.setDatasource(Datasource.builder().mysql(Mysql.builder().url("a").username("b").password("c").build()).build());
    propertiesConfig.setRedis(Redis.builder().host("aa").password("bb").port("cc").database("1").build());
    propertiesConfig.setArtifactId("web");
    propertiesConfig.setGroupId("cn.mg");
    propertiesConfig.setName("abc");
    propertiesConfig.setDescription("test");
    String lastPackageName = propertiesConfig.getArtifactId().replaceAll("-", "").toLowerCase();
    propertiesConfig.setPackageName(propertiesConfig.getGroupId() + "." + lastPackageName);

    propertiesBuilder.generation(propertiesConfig, projectsRoot);
    logbackBuilder.generation(propertiesConfig, projectsRoot);
    applicationBuilder.generation(propertiesConfig, projectsRoot);
    pomBuilder.generation(propertiesConfig, projectsRoot);
    commonBuilder.generation(propertiesConfig, projectsRoot);
    configBuilder.generation(propertiesConfig, projectsRoot);
    controllerBuilder.generation(propertiesConfig, projectsRoot);
  }

}
