package ${packageName};

import lombok.Data;
import org.springframework.context.annotation.Configuration;

/**
 * @author solom
 * @ClassName JwtProperties
 * @Description
 * @Version 1.0.0
 * @create ${.now?string["yyyy-MM-dd HH:mm:ss"]}
 * @since: jdk 17
 */
@Data
@Configuration
public class JwtProperties {

  /**
   * 密匙 修改成非对称加密
   */
  private String apiSecretKey = "k9qPr2kjYWkzXrHKUXji190WG98B5vw6yd+MIG6kcKM1nh/mD4PAGCPfmXyierX85rU0+0Kx4w6CsSJogBXV/FptrXr9BPX5nFlL5N3D2JDUkdL2gUaRF9G8HUJVyxJ+OeHZa3USJOmx8PoD8SP8KZlBLlZm5IExVi+LFNN2kZoJOvk1AYfyYIXFwN/iZwX/qBbnFt6RFmBp+7iNZxabO1AbUDLJHd2ZXWuFow==";

  /**
   * 过期时间-默认7天
   */
  private Long expirationTime = 60 * 60 * 24 * 7L;

  /**
   * 默认存放token的请求头
   */
  private String requestHeader = "Auth";

  /**
   * 默认token前缀
   */
  private String tokenPrefix = "${config.artifactId} ";
}