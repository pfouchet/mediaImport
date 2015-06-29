package com.groupeseb.mediaimport.mapper.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.groupeseb.mediaimport.mapper.serializer.AbstractRefDataSerializer;
import com.groupeseb.ofs.core.model.commontypes.LocalizedValue;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LocalizedValueSerializer extends AbstractRefDataSerializer<LocalizedValue> {

	LocalizedValueSerializer() {
		super(LocalizedValue.class);
	}

	@Override
	public void serialize(LocalizedValue value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
		jgen.writeStartObject();
		serializeLocalizations("localization", value.getLocalization(), jgen);
		jgen.writeEndObject();
	}
}
