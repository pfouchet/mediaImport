package com.groupeseb.mediaimport.mapper.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.groupeseb.mediaimport.mapper.serializer.AbstractRefDataSerializer;
import com.groupeseb.ofs.core.model.media.Media;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MediaSerializer extends AbstractRefDataSerializer<Media> {
	public MediaSerializer() {
		super(Media.class);
	}

	@Override
	public void serialize(Media value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
		jgen.writeStartObject();
		jgen.writeObjectField("key", value.getKey());
		jgen.writeObjectField("type", value.getType());
		jgen.writeObjectField("metadata", value.getMetadata());
		jgen.writeObjectField("originalUrl", value.getOriginalUrl());
		jgen.writeObjectField("thumbnailUrl", value.getThumbnailUrl());
		jgen.writeEndObject();
	}
}
