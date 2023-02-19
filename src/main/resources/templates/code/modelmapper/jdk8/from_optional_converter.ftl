package ${packageName}.modelmapper.jdk8;

import java.util.Optional;
import org.modelmapper.spi.ConditionalConverter;
import org.modelmapper.spi.MappingContext;

/**
 * @author solom
 * @classname FromOptionalConverter.java
 * @create ${.now?string["yyyy-MM-dd HH:mm:ss"]}
 * @since: jdk 1.8
 */
public class FromOptionalConverter implements ConditionalConverter<Optional<Object>, Object> {

  @Override
  public MatchResult match(Class<?> sourceType, Class<?> destinationType) {
    return (Optional.class.equals(sourceType) && !Optional.class.equals(destinationType))
        ? MatchResult.FULL
        : MatchResult.NONE;
  }

  @Override
  public Object convert(MappingContext<Optional<Object>, Object> mappingContext) {
    if (mappingContext.getSource() == null || !mappingContext.getSource().isPresent()) {
      return null;
    }

    MappingContext<Object, Object> propertyContext = mappingContext.create(
        mappingContext.getSource().get(), mappingContext.getDestinationType());
    return mappingContext.getMappingEngine().map(propertyContext);
  }
}