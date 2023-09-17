package ${packageName};

import java.util.HashSet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author solom
 * @classname UserEntity.java
 * @create ${.now?string["yyyy-MM-dd HH:mm:ss"]}
 * @since: jdk 17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements UserDetails {

  private static final long serialVersionUID = 1L;

  private int id;// 用户id
  private String username;// 用户名
  private String password;// 密码
  private String name;
  private HashSet<SimpleGrantedAuthority> authorities;

  @Override
  public HashSet<SimpleGrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }


  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
