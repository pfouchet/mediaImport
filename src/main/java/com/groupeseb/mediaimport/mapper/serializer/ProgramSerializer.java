package com.groupeseb.mediaimport.mapper.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.groupeseb.mediaimport.mapper.serializer.LocalizedKeyValueSerializer;
import com.groupeseb.ofs.core.model.program.Program;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ProgramSerializer extends LocalizedKeyValueSerializer<Program> {
	public ProgramSerializer() {
		super(Program.class);
	}

	@Override
	protected void serializeAdditionalValues(Program value, JsonGenerator jgen) throws IOException {
		jgen.writeObjectField("applianceGroup", value.getApplianceGroup());
		serializeListContainer("parameterSettings", value.getParameterSettings(), jgen);
		serializeListContainer("medias", value.getMedias(), jgen);
	}
}
