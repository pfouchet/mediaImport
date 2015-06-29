package com.groupeseb.mediaimport.mapper.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.groupeseb.mediaimport.mapper.serializer.LocalizedKeyValueSerializer;
import com.groupeseb.ofs.core.model.unit.Unit;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UnitSerializer extends LocalizedKeyValueSerializer<Unit> {
	public UnitSerializer() {
		super(Unit.class);
	}

	@Override
	public void serializeAdditionalValues(Unit value, JsonGenerator jgen) throws IOException {
		jgen.writeObjectField("type", value.getType());
		serializeLocalizations("pluralName", value.getPluralName(), jgen);
		serializeLocalizations("abbreviation", value.getAbbreviation(), jgen);
	}

}
