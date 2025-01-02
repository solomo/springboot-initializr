package cn.solomo.springbootinitializr.configure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author solom
 * @ClassName ProjectConfig
 * @Description 配置文件的配置
 * @Version 1.0.0
 * @create 2022-02-17 16:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertiesConfig implements Cloneable{

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  public static class Datasource implements Cloneable{
    private Mysql mysql;
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  public static class Mysql implements Cloneable{
    private String url;
    private String username;
    private String password;
  }

  private Datasource datasource;

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  public static class Redis implements Cloneable{
    private String host;
    private String password;
    private String port;
    private String database;
  }

  private Redis redis;
  private String applicationJavaName;
  private String description;
	private String groupId;
	private String artifactId;
	private String packageName;
	private PropertiesConfig config;
	private boolean autoExecuteSQL;
}
