package cn.solomo.springbootinitializr.builder.model;

import cn.solomo.springbootinitializr.configure.PropertiesConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApplicationInfo {
  private PropertiesConfig config;
  private String packageName;
  private String className;
	private String groupId;
	private String artifactId;
}
