package com.groupeseb.mediaimport.mapper.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.groupeseb.mediaimport.mapper.serializer.AbstractRefDataSerializer;
import com.groupeseb.ofs.core.model.resourcemedia.ResourceMedia;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ResourceMediaSerializer extends AbstractRefDataSerializer<ResourceMedia> {
	public ResourceMediaSerializer() {
		super(ResourceMedia.class);
	}

	@Override
	public void serialize(ResourceMedia value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
		jgen.writeStartObject();
		jgen.writeObjectField("uid", value.getUid());
		jgen.writeObjectField("media", value.getMedia());
		jgen.writeObjectField("isCoverMedia", value.getIsCoverMedia());
		jgen.writeObjectField("orderInTheSlideshow", value.getOrderInTheSlideshow());
		serializeLocalizations("captionTitle", value.getCaptionTitle(), jgen);
		serializeLocalizations("captionText", value.getCaptionText(), jgen);
		jgen.writeObjectField("tags", value.getTags());
		jgen.writeEndObject();
	}

}
