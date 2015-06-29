package com.groupeseb.mediaimport.mapper.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.groupeseb.mediaimport.mapper.serializer.AbstractRefDataSerializer;
import com.groupeseb.ofs.core.model.commontypes.KeyValue;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class KeyValueSerializer extends AbstractRefDataSerializer<KeyValue> {
	public KeyValueSerializer() {
		super(KeyValue.class);
	}

	@Override
	public void serialize(KeyValue value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
		jgen.writeStartObject();
		jgen.writeObjectField(value.getKey(), value.getValue());
		jgen.writeEndObject();
	}

}
