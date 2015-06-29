package com.groupeseb.mediaimport.mapper.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.groupeseb.mediaimport.mapper.serializer.LocalizedKeyValueSerializer;
import com.groupeseb.ofs.core.model.pack.Pack;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PackSerializer extends LocalizedKeyValueSerializer<Pack> {

	public PackSerializer() {
		super(Pack.class);
	}

	@Override
	void serializeAdditionalValues(Pack value, JsonGenerator jgen) throws IOException {
		serializeListContainer("medias", value.getMedias(), jgen);
		jgen.writeObjectField("binaries", value.getBinaries());
	}
}
