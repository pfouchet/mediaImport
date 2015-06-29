package com.groupeseb.mediaimport.mapper.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.groupeseb.mediaimport.mapper.serializer.AbstractRefDataSerializer;
import com.groupeseb.ofs.core.model.commontypes.LocalizedKeyValue;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LocalizedKeyValueSerializer<T extends LocalizedKeyValue> extends AbstractRefDataSerializer<T> {

	private LocalizedKeyValueSerializer() {
		super(LocalizedKeyValue.class, true);
	}

	public LocalizedKeyValueSerializer(Class<T> entityType) {
		super(entityType, true);
	}

	@Override
	public void serialize(T value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
		jgen.writeStartObject();
		jgen.writeObjectField("key", value.getKey());
		serializeLocalizations("name", value.getName(), jgen);
		serializeAdditionalValues(value, jgen);
		jgen.writeEndObject();
	}

	void serializeAdditionalValues(T value, JsonGenerator jgen) throws IOException {
	}
}
