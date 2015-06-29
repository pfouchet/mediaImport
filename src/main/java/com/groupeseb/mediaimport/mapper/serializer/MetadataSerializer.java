package com.groupeseb.mediaimport.mapper.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.groupeseb.mediaimport.mapper.serializer.AbstractRefDataSerializer;
import com.groupeseb.ofs.core.model.media.Media.Metadata;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MetadataSerializer extends AbstractRefDataSerializer<Metadata> {
	public MetadataSerializer() {
		super(Metadata.class);
	}

	@Override
	public void serialize(Metadata value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
		jgen.writeStartObject();
		jgen.writeObjectField("width", value.getWidth());
		jgen.writeObjectField("height", value.getHeight());
		jgen.writeObjectField("length", value.getLength());
		jgen.writeEndObject();
	}
}
