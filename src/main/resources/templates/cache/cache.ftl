package ${packageName};

/**
 * <p>
 * Cache接口
 * </p>
 *
 * @author solom
 * @create ${.now?string["yyyy-MM-dd HH:mm:ss"]}
 * @since: jdk 17
 */
public interface Cache {

  <T> T get(String cacheName, String key, Class<T> clazz);

  void put(String cacheName, String key, Object value);

  void remove(String cacheName, String key);
}
