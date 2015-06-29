package com.groupeseb.mediaimport.mapper.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.groupeseb.mediaimport.mapper.serializer.LocalizedKeyValueSerializer;
import com.groupeseb.ofs.core.model.role.Role;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RoleSerializer extends LocalizedKeyValueSerializer<Role> {
	public RoleSerializer() {
		super(Role.class);
	}

	@Override
	protected void serializeAdditionalValues(Role value, JsonGenerator jgen) throws IOException {
		jgen.writeObjectField("application", value.getApplication());
		jgen.writeObjectField("resource", value.getResource());
	}
}
