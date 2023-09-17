package ${packageName};

import ${config.packageName}.exception.ValidateCodeException;
import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;
import ${config.packageName}.controller.ValidateController;

/**
 * @Author solom
 * @Description 
 * @version: v1.0.0
 * @create ${.now?string["yyyy-MM-dd HH:mm:ss"]}
 * @since: jdk 1.8
 **/
public class ValidateCodeFilter extends OncePerRequestFilter {

  @Autowired
  private AuthenticationFailureHandler authenticationFailureHandler;

  private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

  @Override
  protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
    if (StringUtils.equalsIgnoreCase("/api/login_ajax.html", httpServletRequest.getRequestURI())
        && StringUtils.equalsIgnoreCase(httpServletRequest.getMethod(), "post")) {
      try {
        validateCode(new ServletWebRequest(httpServletRequest));
      } catch (ValidateCodeException e) {
        authenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
        return;
      }
    }
    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }

  private void validateCode(ServletWebRequest servletWebRequest) throws ServletRequestBindingException {
    String codeInSession = (String) sessionStrategy.getAttribute(servletWebRequest, ValidateController.SESSION_KEY_IMAGE_CODE);
    String codeInRequest = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), "imageCode");
    if (StringUtils.isBlank(codeInRequest)) {
      throw new ValidateCodeException("验证码不能为空！");
    }
    if (codeInSession == null) {
      throw new ValidateCodeException("验证码不存在！");
    }
    if (!StringUtils.equalsIgnoreCase(codeInSession, codeInRequest)) {
      throw new ValidateCodeException("验证码不正确！");
    }
    sessionStrategy.removeAttribute(servletWebRequest, ValidateController.SESSION_KEY_IMAGE_CODE);
  }

}