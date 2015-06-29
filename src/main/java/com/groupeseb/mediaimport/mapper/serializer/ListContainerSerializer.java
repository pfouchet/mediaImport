package com.groupeseb.mediaimport.mapper.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.groupeseb.ofs.core.util.Reflection;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ListContainerSerializer extends AbstractRefDataSerializer<com.groupeseb.mediaimport.mapper.serializer.ListContainer<?>> {
	public ListContainerSerializer() {
		super(com.groupeseb.mediaimport.mapper.serializer.ListContainer.class, true);
	}

	@Override
	public void serialize(ListContainer<?> value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
		jgen.writeStartObject();
		for (Object item : value.getContent()) {
			jgen.writeObjectField(getId(item), item);
		}
		jgen.writeEndObject();
	}

	private String getId(String field, Object item) {
		try {
			return Reflection.getFieldValue(item.getClass().getDeclaredField(field), item).toString();
		} catch (NoSuchFieldException | IllegalAccessException e) {
			return null;
		}
	}

	public String getId(Object item) {

		String key = getId("key", item);

		if (key == null) {
			key = getId("uid", item);
		}

		return key;
	}
}
