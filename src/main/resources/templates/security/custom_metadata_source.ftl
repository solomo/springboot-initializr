package ${packageName};

import ${config.packageName}.repository.entity.${menuEntity};
import ${config.packageName}.repository.entity.${roleEntity};
import ${config.packageName}.repository.entity.${roleMenusEntity};
import ${config.packageName}.repository.entity.${userRolesEntity};
import ${config.packageName}.repository.service.${menuService};
import ${config.packageName}.repository.service.${roleMenusService};
import ${config.packageName}.repository.service.${roleService};
import ${config.packageName}.repository.service.${userRolesService};
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

/**
* @author solom
* @ClassName CustomMetadataSource
* @Description
* @Version 1.0.0
* @create ${.now?string["yyyy-MM-dd HH:mm:ss"]}
* @since: jdk 17
*/
@Component
public class CustomMetadataSource implements FilterInvocationSecurityMetadataSource {

  @Autowired
  private ${menuService} menuService;
  @Autowired
  private ${userRolesService} userRolesService;
  @Autowired
  private ${roleService} roleService;
  @Autowired
  private ${roleMenusService} roleMenusService;

  AntPathMatcher antPathMatcher = new AntPathMatcher();

  @Override
  public Collection<ConfigAttribute> getAttributes(Object o) {
    String requestUrl = ((FilterInvocation) o).getRequestUrl();
    UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    ${userRolesEntity} userRoles = userRolesService.lambdaQuery().eq(${userRolesEntity}::getUserId, userEntity.getId()).one();
    ${roleEntity} role = roleService.getById(userRoles.getRoleId());
    List<${roleMenusEntity}> menus = roleMenusService.lambdaQuery().eq(${roleMenusEntity}::getRid, userRoles.getRoleId()).list();
    for (${roleMenusEntity} roleMenus : menus) {
      ${menuEntity} menu = menuService.getById(roleMenus.getMid());
      if (antPathMatcher.match(menu.getUrl(), requestUrl)) {
        return SecurityConfig.createList(role.getRoleName());
      }
    }
    //没有匹配上的资源，都是登录访问
    return SecurityConfig.createList("ROLE_LOGIN");
  }

  @Override
  public Collection<ConfigAttribute> getAllConfigAttributes() {
    return null;
  }

  @Override
  public boolean supports(Class<?> aClass) {
    return FilterInvocation.class.isAssignableFrom(aClass);
  }
}
