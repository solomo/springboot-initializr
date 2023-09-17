package ${packageName}.modelmapper.jdk8;

import java.util.Optional;
import org.modelmapper.internal.util.MappingContextHelper;
import org.modelmapper.spi.ConditionalConverter;
import org.modelmapper.spi.MappingContext;

/**
 * @author solom
 * @classname ToOptionalConverter.java
 * @create ${.now?string["yyyy-MM-dd HH:mm:ss"]}
 * @since: jdk 17
 */
public class ToOptionalConverter implements ConditionalConverter<Object, Optional<Object>> {

  @Override
  public MatchResult match(Class<?> sourceType, Class<?> destinationType) {
    return (!Optional.class.equals(sourceType) && Optional.class.equals(destinationType))
        ? MatchResult.FULL
        : MatchResult.NONE;
  }

  @Override
  public Optional<Object> convert(MappingContext<Object, Optional<Object>> mappingContext) {
    if (mappingContext.getSource() == null) {
      return Optional.empty();
    }

    MappingContext<?, ?> propertyContext = mappingContext.create(
        mappingContext.getSource(),
        MappingContextHelper.resolveDestinationGenericType(mappingContext));
    Object destination = mappingContext.getMappingEngine().map(propertyContext);
    return Optional.ofNullable(destination);
  }
}