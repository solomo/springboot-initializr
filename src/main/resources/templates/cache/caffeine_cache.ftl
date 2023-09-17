package ${packageName};

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

/**
 * <p>
 * CaffeineCache实现类
 * </p>
 *
 * @author solom
 * @create ${.now?string["yyyy-MM-dd HH:mm:ss"]}
 * @since: jdk 17
 */
@Slf4j
@Service("caffeineCache")
public class CaffeineCache implements Cache {

  @Autowired
  private CacheManager caffeineCacheManager;

  @Override
  public <T> T get(String cacheName, String key, Class<T> clazz) {
    log.debug("{} get -> cacheName [{}], key [{}], class type [{}]", this.getClass().getName(),
        cacheName, key, clazz.getName());
    return Objects.requireNonNull(caffeineCacheManager.getCache(cacheName)).get(key, clazz);
  }

  @Override
  public void put(String cacheName, String key, Object value) {
    log.debug("{} put -> cacheName [{}], key [{}], value [{}]", this.getClass().getName(), cacheName, key, value);
    Objects.requireNonNull(caffeineCacheManager.getCache(cacheName)).put(key, value);
  }

  @Override
  public void remove(String cacheName, String key) {
    log.debug("{} remove -> cacheName [{}], key [{}]", this.getClass().getName(), cacheName, key);
    Objects.requireNonNull(caffeineCacheManager.getCache(cacheName)).evict(key);
  }
}