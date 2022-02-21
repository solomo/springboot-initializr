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
public class PropertiesConfig extends BaseConfig{

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  public static class Datasource{
    private Mysql mysql;
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  public static class Mysql{
    private String url;
    private String username;
    private String password;
  }

  private Datasource datasource;

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  public static class Redis{
    private String host;
    private String password;
    private String port;
    private String database;
  }

  private Redis redis;

  private String applicationJavaName;
  private String name;
  private String description;
}
