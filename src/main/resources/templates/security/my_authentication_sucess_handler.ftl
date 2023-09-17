package ${packageName};

import ${config.packageName}.config.Cache;
import ${config.packageName}.config.JwtProvider;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * @Author solom
 * @Description 
 * @version: v1.0.0
 * @create ${.now?string["yyyy-MM-dd HH:mm:ss"]}
 * @since: jdk 1.8
 **/
@Component
public class MyAuthenticationSucessHandler implements AuthenticationSuccessHandler {

  @Autowired
  private JwtProvider jwtProvider;
  @Autowired
  private Cache caffeineCache;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
    response.setContentType("application/json;charset=utf-8");
    response.setStatus(HttpServletResponse.SC_OK);
    PrintWriter out = response.getWriter();
    //生成自定义token
    AccessToken accessToken = jwtProvider.createToken((UserEntity) authentication.getPrincipal());

    UserEntity userDetail = (UserEntity) authentication.getPrincipal();
    // 放入缓存
    caffeineCache.put("user", userDetail.getUsername(), userDetail);
    out.write("{\"status\":\"ok\",\"msg\":\"登录成功\", \"token\":\"" + accessToken.getToken() + "\"}");
    out.flush();
    out.close();
  }
}