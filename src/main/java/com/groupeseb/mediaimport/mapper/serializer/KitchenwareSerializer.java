package com.groupeseb.mediaimport.mapper.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.groupeseb.mediaimport.mapper.serializer.LocalizedKeyValueSerializer;
import com.groupeseb.ofs.core.model.kitchenware.Kitchenware;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class KitchenwareSerializer extends LocalizedKeyValueSerializer<Kitchenware> {
	public KitchenwareSerializer() {
		super(Kitchenware.class);
	}

	@Override
	protected void serializeAdditionalValues(Kitchenware value, JsonGenerator jgen) throws IOException {
		serializeListContainer("medias", value.getMedias(), jgen);
	}
}
