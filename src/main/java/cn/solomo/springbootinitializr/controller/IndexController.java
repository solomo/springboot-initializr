package cn.solomo.springbootinitializr.controller;

import cn.hutool.core.util.ZipUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONException;
import cn.hutool.json.JSONObject;
import cn.solomo.springbootinitializr.builder.RepositoryBuilder;
import cn.solomo.springbootinitializr.builder.WebBuilder;
import cn.solomo.springbootinitializr.builder.impl.PomBuilderImpl;
import cn.solomo.springbootinitializr.configure.PropertiesConfig;
import cn.solomo.springbootinitializr.configure.PropertiesConfig.Datasource;
import cn.solomo.springbootinitializr.configure.PropertiesConfig.Mysql;
import cn.solomo.springbootinitializr.configure.PropertiesConfig.Redis;
import cn.solomo.springbootinitializr.service.SpringContextUtil;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.Writer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author solom
 * @ClassName IndexController
 * @Description
 * @Version 1.0.0
 * @create 2022-02-25 17:28
 */
@Slf4j
@RequestMapping("/")
@Controller
public class IndexController {
	
	@RequestMapping("index.html")
	public String index() {
		return "index";
	}
	
	@RequestMapping("execute.html")
	@ResponseBody
	public JSONObject execute(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String projectsRoot = request.getSession().getServletContext().getRealPath("/");
		PropertiesConfig propertiesConfig = new PropertiesConfig();
		propertiesConfig.setGroupId(request.getParameter("groupId"));
		propertiesConfig.setArtifactId(request.getParameter("artifactId"));
		propertiesConfig.setDescription(request.getParameter("desc"));
		Mysql mysql = new Mysql();
		mysql.setUrl("jdbc:mysql://" + request.getParameter("mysqlUrl"));
		mysql.setUrl(mysql.getUrl() + ":" + request.getParameter("mysqlPort"));
		mysql.setUrl(mysql.getUrl() + "/" + request.getParameter("mysqlDatabase")
				+ "?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&zeroDateTimeBehavior=convertToNull");
		mysql.setUsername(request.getParameter("mysqlUsername"));
		mysql.setPassword(request.getParameter("mysqlPassword"));
		propertiesConfig.setDatasource(Datasource.builder().mysql(mysql).build());
		Redis redis = new Redis();
		redis.setHost(request.getParameter("redisHost"));
		redis.setPort(request.getParameter("redisPort"));
		redis.setPassword(request.getParameter("reidsPassword"));
		redis.setDatabase(request.getParameter("redisDatabase"));
		File f = new File(projectsRoot + propertiesConfig.getArtifactId());
		if (!f.exists()) {
			f.mkdirs();
		}
		propertiesConfig.setRedis(redis);
		propertiesConfig.setPackageName(propertiesConfig.getGroupId() + "." + propertiesConfig.getArtifactId());
		propertiesConfig.setAutoExecuteSQL(request.getParameter("autoExecuteSQL").equals("1"));
		propertiesConfig.setSecurity(request.getParameter("security").equals("1"));
		PomBuilderImpl pomBuilder = SpringContextUtil.getBean(PomBuilderImpl.class);
		pomBuilder.generation(propertiesConfig, projectsRoot + propertiesConfig.getArtifactId(), "pom.ftl");
		RepositoryBuilder repositoryBuilder = SpringContextUtil.getBean(RepositoryBuilder.class);
		repositoryBuilder.generation(propertiesConfig, projectsRoot);
		WebBuilder webBuilder = SpringContextUtil.getBean(WebBuilder.class);
		webBuilder.generation(propertiesConfig, projectsRoot);
		ZipUtil.zip(projectsRoot + propertiesConfig.getArtifactId());
		log.info(projectsRoot + propertiesConfig.getArtifactId());
		FileUtils.deleteDirectory(f);
		JSONObject rtn = new JSONObject();
		rtn.putOpt("code", "ok");
		return rtn;
	}
}
