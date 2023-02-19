package ${packageName};

import ${config.packageName}.common.Common;
import com.google.gson.Gson;
import java.io.UnsupportedEncodingException;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * @author solom
 * @ClassName GsonRedisSerializer
 * @Description
 * @Version 1.0.0
 * @create ${.now?string["yyyy-MM-dd HH:mm:ss"]}
 * @since: jdk 1.8
 */
public class GsonRedisSerializer implements RedisSerializer {
  Gson gson = null;

  public GsonRedisSerializer() {
    this.gson = Common.lowerCaseWithUnderscoresGson;
  }

  @Override
  public byte[] serialize(Object o) throws SerializationException {
    return gson.toJson(o).getBytes();
  }

  @Override
  public Object deserialize(byte[] bytes) throws SerializationException {
    try {
      return gson.fromJson(new String(bytes, "UTF-8"), Object.class);
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return null;
  }
}