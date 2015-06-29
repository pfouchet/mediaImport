package com.groupeseb.mediaimport.mapper.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.groupeseb.mediaimport.mapper.serializer.LocalizedKeyValueSerializer;
import com.groupeseb.ofs.core.model.classification.Classification;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ClassificationSerializer extends LocalizedKeyValueSerializer<Classification> {
	public ClassificationSerializer() {
		super(Classification.class);
	}

	@Override
	protected void serializeAdditionalValues(Classification value, JsonGenerator jgen) throws IOException {
		jgen.writeObjectField("type", value.getType());
		serializeListContainer("medias", value.getMedias(), jgen);
		jgen.writeObjectField("keywords", value.getKeywords());
	}
}
