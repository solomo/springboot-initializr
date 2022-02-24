package cn.solomo.springbootinitializr;

import cn.solomo.springbootinitializr.builder.RepositoryBuilder;
import cn.solomo.springbootinitializr.builder.WebBuilder;
import cn.solomo.springbootinitializr.builder.impl.PomBuilderImpl;
import cn.solomo.springbootinitializr.configure.PropertiesConfig;
import cn.solomo.springbootinitializr.configure.PropertiesConfig.Datasource;
import cn.solomo.springbootinitializr.configure.PropertiesConfig.Mysql;
import cn.solomo.springbootinitializr.configure.PropertiesConfig.Redis;
import cn.solomo.springbootinitializr.service.SpringContextUtil;
import java.io.File;
import java.util.Scanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringbootInitializrApplication {

  public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext ctx = SpringApplication.run(SpringbootInitializrApplication.class, args);
	
		String projectsRoot = "";
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入生成项目的目录：");
		if (scanner.hasNext()) {
			projectsRoot = scanner.next();
		}
		PropertiesConfig propertiesConfig = new PropertiesConfig();
		scanner = new Scanner(System.in);
		System.out.println("请输入项目groupId：");
		if (scanner.hasNext()) {
			propertiesConfig.setGroupId(scanner.next());
		}
		scanner = new Scanner(System.in);
		System.out.println("请输入项目artifactId：");
		if (scanner.hasNext()) {
			propertiesConfig.setArtifactId(scanner.next());
		}
		scanner = new Scanner(System.in);
		System.out.println("请输入项目名称：");
		if (scanner.hasNext()) {
			propertiesConfig.setName(scanner.next());
		}
		scanner = new Scanner(System.in);
		System.out.println("请输入项目描述：");
		if (scanner.hasNext()) {
			propertiesConfig.setDescription(scanner.next());
		}
		Mysql mysql = new Mysql();
		scanner = new Scanner(System.in);
		System.out.println("请输入mysql ip地址：");
		if (scanner.hasNext()) {
			mysql.setUrl("jdbc:mysql://"+scanner.next());
		}
		scanner = new Scanner(System.in);
		System.out.println("请输入mysql端口：");
		if (scanner.hasNext()) {
			mysql.setUrl(mysql.getUrl()+":"+scanner.next());
		}
		scanner = new Scanner(System.in);
		System.out.println("请输入数据库名称：");
		if (scanner.hasNext()) {
			mysql.setUrl(mysql.getUrl()+"/"+scanner.next()+"?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&zeroDateTimeBehavior=convertToNull");
		}
		scanner = new Scanner(System.in);
		System.out.println("请输入数据库账号：");
		if (scanner.hasNext()) {
			mysql.setUsername(scanner.next());
		}
		scanner = new Scanner(System.in);
		System.out.println("请输入数据库账号的密码：");
		if (scanner.hasNext()) {
			mysql.setPassword(scanner.next());
		}
		propertiesConfig.setDatasource(Datasource.builder().mysql(mysql).build());
		Redis redis = new Redis();
		scanner = new Scanner(System.in);
		System.out.println("请输入redis host ip：");
		if (scanner.hasNext()) {
			redis.setHost(scanner.next());
		}
		scanner = new Scanner(System.in);
		System.out.println("请输入redis port：");
		if (scanner.hasNext()) {
			redis.setPort(scanner.next());
		}
		scanner = new Scanner(System.in);
		System.out.println("请输入redis password：");
		if (scanner.hasNext()) {
			redis.setPassword(scanner.next());
		}
		scanner = new Scanner(System.in);
		System.out.println("请输入redis database：");
		if (scanner.hasNext()) {
			redis.setDatabase(scanner.next());
		}
		File f = new File(projectsRoot + propertiesConfig.getArtifactId());
		if(!f.exists()){
			f.mkdirs();
		}
		propertiesConfig.setRedis(redis);
		propertiesConfig.setPackageName(propertiesConfig.getGroupId() + "." + propertiesConfig.getArtifactId());
		PomBuilderImpl pomBuilder = SpringContextUtil.getBean(PomBuilderImpl.class);
		pomBuilder.generation(propertiesConfig, projectsRoot + propertiesConfig.getArtifactId(), "pom.ftl");
		RepositoryBuilder repositoryBuilder = SpringContextUtil.getBean(RepositoryBuilder.class);
		repositoryBuilder.generation(propertiesConfig, projectsRoot);
		WebBuilder webBuilder = SpringContextUtil.getBean(WebBuilder.class);
		webBuilder.generation(propertiesConfig, projectsRoot);
		ctx.close();
  }

}
