package ${packageName};

import ${config.packageName}.repository.entity.${userEntity};
import ${config.packageName}.repository.entity.${userRoleEntity};
import ${config.packageName}.repository.service.${roleService};
import ${config.packageName}.repository.service.${userRolesService};
import ${config.packageName}.repository.service.${userService};
import ${config.packageName}.web.security.UserEntity;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author solom
 * @ClassName UserDetailsServiceImpl.java
 * @date 2019-12-09 15:51
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
  private ${roleService} roleService;
  @Autowired
  private ${userRolesService} userRolesService;
	@Autowired
	private ${userService} userService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    ${userEntity} user = userService.lambdaQuery().eq(DemoUser::getLoginName, username).one();
    if (user != null) {
      //根据用户id获取用户角色
			${userRoleEntity} userRole = userRolesService.lambdaQuery().eq(${userRoleEntity}::getUserId, user.getId()).one();
      // 填充权限
      HashSet<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority(roleService.getById(userRole.getRoleId()).getRoleName()));
      //填充权限菜单
      UserEntity userEntity = new UserEntity();
      userEntity.setId(user.getId());
      userEntity.setUsername(username);
      userEntity.setPassword(user.getLoginPassword());
      userEntity.setName(user.getName());
      userEntity.setAuthorities(authorities);
      return userEntity;
    } else {
      throw new UsernameNotFoundException(username + " not found");
    }
  }
}