package com.groupeseb.mediaimport.mapper.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.groupeseb.mediaimport.mapper.serializer.LocalizedKeyValueSerializer;
import com.groupeseb.ofs.core.model.equipment.Equipment;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class EquipmentSerializer extends LocalizedKeyValueSerializer<Equipment> {
	public EquipmentSerializer() {
		super(Equipment.class);
	}

	@Override
	protected void serializeAdditionalValues(Equipment value, JsonGenerator jgen) throws IOException {
		jgen.writeObjectField("brand", value.getBrand());
		serializeListContainer("medias", value.getMedias(), jgen);
	}
}
