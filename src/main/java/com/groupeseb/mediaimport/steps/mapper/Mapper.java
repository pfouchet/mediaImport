package com.groupeseb.mediaimport.steps.mapper;

import com.groupeseb.bus.commonapi.model.lite.MediaLite;
import com.groupeseb.ofs.core.model.media.Media;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.builtin.DateToStringConverter;
import ma.glasnost.orika.impl.ConfigurableMapper;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;

@Component
public class Mapper extends ConfigurableMapper {

	public static final String STRING_TO_DATE_CONVERTER = "stringToDateConverter";

	@Override
	protected void configureFactoryBuilder(DefaultMapperFactory.Builder factoryBuilder) {
		factoryBuilder.mapNulls(false);
	}

	@Override
	protected void configure(MapperFactory mapperFactory) {

		mapperFactory.getConverterFactory().registerConverter(STRING_TO_DATE_CONVERTER,
		                                                      new DateToStringConverter("yyyy-MM-dd'T'HH:mm:ss"));

		mapperFactory.classMap(Media.class, MediaLite.class)
				.field("type.key", "type")
				.field("originalUrl", "original")
				.field("thumbnailUrl", "thumbnail")
				.fieldMap("creationDate", "creationDate").converter(STRING_TO_DATE_CONVERTER).add()
				.fieldMap("releaseDate", "releaseDate").converter(STRING_TO_DATE_CONVERTER).add()
				.byDefault()
				.register();
	}
}
