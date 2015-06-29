package com.groupeseb.mediaimport.mapper.serializer;


import com.fasterxml.jackson.core.JsonGenerator;
import com.groupeseb.mediaimport.mapper.serializer.LocalizedKeyValueSerializer;
import com.groupeseb.ofs.core.model.sourcesystem.SourceSystem;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SourceSystemSerializer extends LocalizedKeyValueSerializer<SourceSystem> {

	public SourceSystemSerializer() {
		super(SourceSystem.class);
	}

	@Override
	void serializeAdditionalValues(SourceSystem value, JsonGenerator jgen) throws IOException {
		serializeListContainer("domains", value.getDomains(), jgen);
	}
}
