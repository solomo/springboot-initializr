package ${packageName};

import cn.hutool.extra.spring.SpringUtil;
import ${config.packageName}.security.CustomMetadataSource;
import ${config.packageName}.security.MyAuthenticationFailureHandler;
import ${config.packageName}.security.MyAuthenticationSucessHandler;
import ${config.packageName}.security.UrlAccessDecisionManager;
import ${config.packageName}.security.ValidateCodeFilter;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

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
  @Autowired
  private JwtProperties jwtProperties;

  @Bean
  public SessionRegistry sessionRegistry() {
    return new SessionRegistryImpl();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return (web) -> web.ignoring().requestMatchers("/login.html",
    "/css/**", "/js/**", "/img/**", "/layui/**", "/layer/**",
    "/api/code/image", "/swagger-ui/**", "/v3/api-docs",
    "/v3/api-docs/swagger-config",
    "/configuration/ui",
    "/swagger-resources/**",
    "/configuration/security",
    "/swagger-ui.html",
    "/webjars/**", "/test/**", "/favicon.ico", "/actuator/shutdown",
    "/doc.html");
  }

  @Bean
  protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
    requestHandler.setCsrfRequestAttributeName("_csrf");
    http.authorizeHttpRequests((requests)->requests
    .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
      @Override
      public <O extends FilterSecurityInterceptor> O postProcess(O o) {
      o.setSecurityMetadataSource(metadataSource);
      o.setAccessDecisionManager(urlAccessDecisionManager);
      return o;
      }
      })
      .anyRequest().authenticated())
      .requestCache(requestCache->requestCache.disable())
      .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
      @Override
      public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
      CorsConfiguration config = new CorsConfiguration();
      config.setAllowedMethods(Collections.singletonList("*"));
      config.setAllowCredentials(true);
      config.setAllowedHeaders(Collections.singletonList("*"));
      config.setExposedHeaders(Arrays.asList(jwtProperties.getRequestHeader()));
      config.setMaxAge(jwtProperties.getExpirationTime());
      return config;
      }
      }))
      .csrf((csrf) -> csrf.disable())
      .formLogin(httpSecurityFormLoginConfigurer ->
      httpSecurityFormLoginConfigurer.loginPage("/login.html").permitAll()
      .loginProcessingUrl("/api/login_ajax.html").permitAll()
      .defaultSuccessUrl("/")
      .successHandler(authenticationSucessHandler)
      .failureHandler(authenticationFailureHandler))
      .addFilterBefore(new JwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
      .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }
}