package ${packageName};

import ${config.packageName}.security.CustomMetadataSource;
import ${config.packageName}.security.MyAuthenticationFailureHandler;
import ${config.packageName}.security.MyAuthenticationSucessHandler;
import ${config.packageName}.security.UrlAccessDecisionManager;
import ${config.packageName}.security.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

/**
 * @Author solom
 * @Description spring security 配置
 * @version: v1.0.0
 * @create ${.now?string["yyyy-MM-dd HH:mm:ss"]}
 * @since: jdk 17
 **/
@Configuration
public class WebSecurityConfig {

  @Autowired
  private MyAuthenticationSucessHandler authenticationSucessHandler;
  @Autowired
  private MyAuthenticationFailureHandler authenticationFailureHandler;
  @Autowired
  private CustomMetadataSource metadataSource;
  @Autowired
  private UrlAccessDecisionManager urlAccessDecisionManager;
  @Autowired
  private CorsFilter corsFilter;
  @Autowired
  private UserDetailsService userDetailsService;

  @Bean
  public SessionRegistry sessionRegistry() {
    return new SessionRegistryImpl();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }


  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/login.html", "/css/**", "/js/**", "/img/**", "/layui/**", "/layer/**",
        "/api/code/image", "/swagger-ui/**", "/v3/api-docs/**", "/api-docs.yaml", "/favicon.ico",
        "/configuration/ui",
        "/swagger-resources/**",
        "/configuration/security",
        "/swagger-ui.html",
        "/webjars/**", "/actuator/shutdown");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.addFilterBefore(new ValidateCodeFilter(), UsernamePasswordAuthenticationFilter.class) // 添加验证码校验过滤器
        .formLogin() // 表单登录
        .loginPage("/login.html") // 登录跳转 URL
        .loginProcessingUrl("/api/login_ajax.html") // 处理表单登录 URL
        .defaultSuccessUrl("/")
        .successHandler(authenticationSucessHandler) // 处理登录成功
        .failureHandler(authenticationFailureHandler) // 处理登录失败
        .and()
        .logout()
        .logoutUrl("/api/logout.html")
        .and().headers().frameOptions().disable()
        .and()
        .addFilterBefore(new JwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
        .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
        .authorizeRequests() // 授权配置
        .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
          @Override
          public <O extends FilterSecurityInterceptor> O postProcess(O o) {
            o.setSecurityMetadataSource(metadataSource);
            o.setAccessDecisionManager(urlAccessDecisionManager);
            return o;
          }
        })
        .antMatchers("/login.html", "/api/login_ajax.html", "/api/code/image", "/favicon.ico")
        .permitAll() // 无需认证的请求路径
        .anyRequest()  // 所有请求
        .authenticated() // 都需要认证
        .and()
        .requestCache().disable()
        .csrf().disable();
  }
}