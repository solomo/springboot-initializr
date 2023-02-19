package ${packageName};

import cn.hutool.extra.spring.SpringUtil;
import ${config.packageName}.security.UserEntity;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author solom
 * @ClassName JwtAuthenticationTokenFilter
 * @Description
 * @Version 1.0.0
 * @create ${.now?string["yyyy-MM-dd HH:mm:ss"]}
 * @since: jdk 1.8
 */
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
    JwtProvider jwtProvider = SpringUtil.getBean(JwtProvider.class);
    JwtProperties jwtProperties = new JwtProperties();
    CaffeineCache cache = SpringUtil.getBean(CaffeineCache.class);
    // 拿到Authorization请求头内的信息
    String authToken = jwtProvider.getToken(request);
    // 判断一下内容是否为空
    if (StringUtils.isNotEmpty(authToken) && authToken.startsWith(jwtProperties.getTokenPrefix())) {
      // 去掉token前缀(plus_chat )，拿到真实token
      authToken = authToken.substring(jwtProperties.getTokenPrefix().length());
      // 拿到token里面的登录账号
      String loginAccount = jwtProvider.getSubjectFromToken(authToken);
      // 查询用户
      UserEntity userDetails = cache.get("user", loginAccount, UserEntity.class);
      if (StringUtils.isNotEmpty(loginAccount) && userDetails != null && jwtProvider.validateToken(authToken, userDetails)) {
        // 组装authentication对象，构造参数是Principal Credentials 与 Authorities
        // 后面的拦截器里面会用到 grantedAuthorities 方法
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        // 将authentication信息放入到上下文对象中
        SecurityContextHolder.getContext().setAuthentication(authentication);
      } else {
        response.sendRedirect("/login.html");
        return;
      }
    }

    chain.doFilter(request, response);
  }
}