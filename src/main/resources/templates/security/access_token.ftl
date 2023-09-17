package ${packageName};

import java.util.Date;
import lombok.Builder;
import lombok.Data;

/**
 * @author solom
 * @ClassName AccessToken
 * @Description
 * @Version 1.0.0
 * @create ${.now?string["yyyy-MM-dd HH:mm:ss"]}
 * @since: jdk 17
 */
@Data
@Builder
public class AccessToken {

  private String loginAccount;
  private String token;
  private Date expirationTime;
}