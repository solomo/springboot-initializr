package cn.solomo.springbootinitializr.builder;

import cn.solomo.springbootinitializr.builder.impl.ApplicationBuilderImpl;
import cn.solomo.springbootinitializr.builder.impl.CommonBuilderImpl;
import cn.solomo.springbootinitializr.builder.impl.ConfigBuilderImpl;
import cn.solomo.springbootinitializr.builder.impl.ControllerBuilderImpl;
import cn.solomo.springbootinitializr.builder.impl.LogbackBuilderImpl;
import cn.solomo.springbootinitializr.builder.impl.PackageBuilder;
import cn.solomo.springbootinitializr.builder.impl.PomBuilderImpl;
import cn.solomo.springbootinitializr.builder.impl.PropertiesBuilderImpl;
import cn.solomo.springbootinitializr.builder.impl.UserDetailBuilderImpl;
import cn.solomo.springbootinitializr.configure.PropertiesConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author solom
 * @ClassName WebBuilder
 * @Description
 * @Version 1.0.0
 * @create 2022-02-24 14:07
 */
@Slf4j
@Service
public class WebBuilder extends BaseBuilder{
	
	@Autowired
	private LogbackBuilderImpl logbackBuilder;
	@Autowired
	private CommonBuilderImpl commonBuilder;
	@Autowired
	private ConfigBuilderImpl configBuilder;
	@Autowired
	private ControllerBuilderImpl controllerBuilder;
	@Autowired
	private UserDetailBuilderImpl userDetailBuilder;
	@Autowired
	private PropertiesBuilderImpl propertiesBuilder;
	@Autowired
	private ApplicationBuilderImpl applicationBuilder;
	@Autowired
	private PomBuilderImpl pomBuilder;
	@Autowired
	private PackageBuilder packageBuilder;
	
	public void generation(PropertiesConfig config, String projectsRoot) throws Exception {
		PropertiesConfig webConfig = new PropertiesConfig();
		projectsRoot = projectsRoot + config.getArtifactId() + "/";
		webConfig.setConfig(config);
		webConfig.setDatasource(config.getDatasource());
		webConfig.setRedis(config.getRedis());
		webConfig.setGroupId(config.getGroupId() + "." + config.getArtifactId());
		webConfig.setArtifactId("web");
		webConfig.setDescription("web");
		webConfig.setAutoExecuteSQL(config.isAutoExecuteSQL());
		webConfig.setPackageName(webConfig.getGroupId() + "." + webConfig.getArtifactId());
		webConfig.setSecurity(config.isSecurity());
		
		applicationBuilder.generation(webConfig, projectsRoot + webConfig.getArtifactId());
		pomBuilder.generation(webConfig, projectsRoot + webConfig.getArtifactId(), "pom_web.ftl");
		propertiesBuilder.generation(webConfig, projectsRoot + webConfig.getArtifactId());
		logbackBuilder.generation(webConfig, projectsRoot + webConfig.getArtifactId());
		commonBuilder.generation(webConfig, projectsRoot + webConfig.getArtifactId());
		configBuilder.generation(webConfig, projectsRoot + webConfig.getArtifactId());
		if(webConfig.isSecurity()) {
			controllerBuilder.generation(webConfig, projectsRoot + webConfig.getArtifactId());
			userDetailBuilder.generation(config, projectsRoot + webConfig.getArtifactId());
		}
		packageBuilder.generation(webConfig, projectsRoot + webConfig.getArtifactId());
	}
}
