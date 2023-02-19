package cn.solomo.springbootinitializr.builder.impl;

import cn.solomo.springbootinitializr.builder.BaseBuilder;
import cn.solomo.springbootinitializr.configure.PropertiesConfig;
import cn.solomo.springbootinitializr.service.ExecuteSQLService;
import java.io.File;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author solom
 * @ClassName SqlBuilderImpl
 * @Description
 * @Version 1.0.0
 * @create 2022-02-22 10:35
 */
@Slf4j
@Service
public class SqlBuilderImpl extends BaseBuilder {
  @Autowired
  private ExecuteSQLService executeSQLService;

  public void generation(PropertiesConfig config, String projectsRoot) throws Exception {
    File file = new File(projectsRoot + "/src/main/resources/sql/", "user.sql");// 写入文件
    super.writeFile(file, "sql/user.ftl", config);
    file = new File(projectsRoot + "/src/main/resources/sql/", "role.sql");// 写入文件
    super.writeFile(file, "sql/role.ftl", config);
    file = new File(projectsRoot + "/src/main/resources/sql/", "user_roles.sql");// 写入文件
    super.writeFile(file, "sql/user_roles.ftl", config);
    file = new File(projectsRoot + "/src/main/resources/sql/", "menu.sql");// 写入文件
    super.writeFile(file, "sql/menu.ftl", config);
    file = new File(projectsRoot + "/src/main/resources/sql/", "role_menus.sql");// 写入文件
    super.writeFile(file, "sql/role_menus.ftl", config);
		if(config.isAutoExecuteSQL()) {
			executeSQLService.executeSqlByFile(config.getDatasource().getMysql().getUrl(), config.getDatasource().getMysql().getUsername(), config.getDatasource().getMysql().getPassword(), projectsRoot + "/src/main/resources/sql/user.sql");
			executeSQLService.executeSqlByFile(config.getDatasource().getMysql().getUrl(), config.getDatasource().getMysql().getUsername(), config.getDatasource().getMysql().getPassword(), projectsRoot + "/src/main/resources/sql/role.sql");
			executeSQLService.executeSqlByFile(config.getDatasource().getMysql().getUrl(), config.getDatasource().getMysql().getUsername(), config.getDatasource().getMysql().getPassword(), projectsRoot + "/src/main/resources/sql/user_roles.sql");
			executeSQLService.executeSqlByFile(config.getDatasource().getMysql().getUrl(), config.getDatasource().getMysql().getUsername(), config.getDatasource().getMysql().getPassword(), projectsRoot + "/src/main/resources/sql/menu.sql");
			executeSQLService.executeSqlByFile(config.getDatasource().getMysql().getUrl(), config.getDatasource().getMysql().getUsername(), config.getDatasource().getMysql().getPassword(), projectsRoot + "/src/main/resources/sql/role_menus.sql");
		}
  }
}
