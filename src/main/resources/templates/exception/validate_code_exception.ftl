package ${packageName};

import org.springframework.security.core.AuthenticationException;

/**
 * @Author solom
 * @Description
 * @version: v1.0.0
 * @create ${.now?string["yyyy-MM-dd HH:mm:ss"]}
 * @since: jdk 17
 **/
public class ValidateCodeException extends AuthenticationException {

  private static final long serialVersionUID = 1L;

  public ValidateCodeException(String message) {
    super(message);
  }
}