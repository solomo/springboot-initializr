package cn.solomo.springbootinitializr.configure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author solom
 * @ClassName BaseConfig
 * @Description
 * @Version 1.0.0
 * @create 2022-02-17 18:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseConfig implements Cloneable{
  private String groupId;
  private String artifactId;
  private String packageName;
  private String version;
  private String name;
  private String description;
}
