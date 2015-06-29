package com.groupeseb.mediaimport.mapper.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.groupeseb.mediaimport.mapper.serializer.LocalizedKeyValueSerializer;
import com.groupeseb.ofs.core.model.technique.Technique;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TechniqueSerializer extends LocalizedKeyValueSerializer<Technique> {
	public TechniqueSerializer() {
		super(Technique.class);
	}

	@Override
	protected void serializeAdditionalValues(Technique value, JsonGenerator jgen) throws IOException {
		jgen.writeObjectField("type", value.getType());
		serializeListContainer("kitchenwares", value.getKitchenwares(), jgen);
		serializeListContainer("utensils", value.getUtensils(), jgen);
		serializeListContainer("foods", value.getFoods(), jgen);
		serializeListContainer("medias", value.getMedias(), jgen);
	}
}
