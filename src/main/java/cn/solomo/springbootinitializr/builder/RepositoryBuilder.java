package cn.solomo.springbootinitializr.builder;

import cn.solomo.springbootinitializr.builder.impl.CodeGeneratorBuilderImpl;
import cn.solomo.springbootinitializr.builder.impl.PomBuilderImpl;
import cn.solomo.springbootinitializr.configure.PropertiesConfig;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

/**
 * @author solom
 * @ClassName RepositoryBuilder
 * @Description
 * @Version 1.0.0
 * @create 2022-02-24 14:10
 */
@Slf4j
@Service
public class RepositoryBuilder extends BaseBuilder{
	@Autowired
	private PomBuilderImpl pomBuilder;
	@Autowired
	private CodeGeneratorBuilderImpl codeGeneratorBuilder;
	@Autowired
	FreeMarkerConfigurer freeMarkerConfigurer;
	
	
	public void generation(PropertiesConfig config, String projectsRoot) throws Exception {
		PropertiesConfig repositoryConfig = new PropertiesConfig();
		repositoryConfig.setDatasource(config.getDatasource());
		repositoryConfig.setRedis(config.getRedis());
		repositoryConfig.setPackageName(config.getGroupId() + "." + config.getArtifactId() + ".repository");
		projectsRoot = projectsRoot + config.getArtifactId() + "/";
		repositoryConfig.setGroupId(config.getGroupId() + "." + config.getArtifactId());
		repositoryConfig.setArtifactId("repository");
		repositoryConfig.setDescription("repository");
		repositoryConfig.setConfig(config);
		pomBuilder.generation(repositoryConfig, projectsRoot + repositoryConfig.getArtifactId(), "pom_repository.ftl");
		File f = new File(projectsRoot + repositoryConfig.getArtifactId() + "/src/main/resources/entity.java.ftl");
		f.getParentFile().mkdirs();
		Path copied = f.toPath();
		ClassPathResource cpr = new ClassPathResource("/templates/swagger/entity.java.ftl");
		Files.copy(cpr.getInputStream(), copied, StandardCopyOption.REPLACE_EXISTING);
		codeGeneratorBuilder.generation(repositoryConfig, projectsRoot + repositoryConfig.getArtifactId());
		pomBuilder.generation(repositoryConfig, projectsRoot + repositoryConfig.getArtifactId(), "pom_repository_final.ftl");
	}
}
