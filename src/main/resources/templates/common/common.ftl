package ${packageName};

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;

/**
 * @author solom
 * @classname Common.java
 * @create ${.now?string["yyyy-MM-dd HH:mm:ss"]}
 * @since: jdk 17
 */
public final class Common {

  public final static Gson lowerCaseWithUnderscoresGson = new GsonBuilder()
      .registerTypeAdapter(LocalDate.class, new JsonSerializer<LocalDate>() {
        @Override
        public JsonElement serialize(LocalDate src, Type typeOfSrc,
            JsonSerializationContext context) {
          return new JsonPrimitive(src.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }
      }).registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
        @Override
        public LocalDate deserialize(JsonElement json, Type type,
            JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
          String datetime = json.getAsJsonPrimitive().getAsString();
          if (StringUtils.isNumeric(datetime)) {
            Date d = new Date(Long.parseLong(datetime));
            Instant instant = d.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();
            return instant.atZone(zoneId).toLocalDate();
          } else {
            return LocalDate.parse(datetime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
          }
        }
      })
      .registerTypeAdapter(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {
        @Override
        public JsonElement serialize(LocalDateTime src, Type typeOfSrc,
            JsonSerializationContext context) {
          return new JsonPrimitive(src.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
      }).registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
        @Override
        public LocalDateTime deserialize(JsonElement json, Type type,
            JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
          String datetime = json.getAsJsonPrimitive().getAsString();
          if (StringUtils.isNumeric(datetime)) {
            Date d = new Date(Long.parseLong(datetime));
            Instant instant = d.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();
            return instant.atZone(zoneId).toLocalDateTime();
          } else {
            return LocalDateTime
                .parse(datetime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
          }
        }
      }).setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

  public final static Gson identityGson = new GsonBuilder()
      .registerTypeAdapter(LocalDate.class, new JsonSerializer<LocalDate>() {
        @Override
        public JsonElement serialize(LocalDate src, Type typeOfSrc,
            JsonSerializationContext context) {
          return new JsonPrimitive(src.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }
      }).registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
        @Override
        public LocalDate deserialize(JsonElement json, Type type,
            JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
          String datetime = json.getAsJsonPrimitive().getAsString();
          if (StringUtils.isNumeric(datetime)) {
            Date d = new Date(Long.parseLong(datetime));
            Instant instant = d.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();
            return instant.atZone(zoneId).toLocalDate();
          } else {
            return LocalDate.parse(datetime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
          }
        }
      })
      .registerTypeAdapter(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {
        @Override
        public JsonElement serialize(LocalDateTime src, Type typeOfSrc,
            JsonSerializationContext context) {
          return new JsonPrimitive(src.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
      }).registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
        @Override
        public LocalDateTime deserialize(JsonElement json, Type type,
            JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
          String datetime = json.getAsJsonPrimitive().getAsString();
          if (StringUtils.isNumeric(datetime)) {
            Date d = new Date(Long.parseLong(datetime));
            Instant instant = d.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();
            return instant.atZone(zoneId).toLocalDateTime();
          } else {
            return LocalDateTime
                .parse(datetime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
          }
        }
      }).setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();


  public final static DateTimeFormatter dayDTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  public final static DateTimeFormatter dayTimeDTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
}