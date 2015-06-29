package com.groupeseb.mediaimport.mapper.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.groupeseb.mediaimport.mapper.serializer.AbstractRefDataSerializer;
import com.groupeseb.ofs.core.model.parametersetting.ParameterSetting;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ParameterSettingSerializer extends AbstractRefDataSerializer<ParameterSetting> {
	public ParameterSettingSerializer() {
		super(ParameterSetting.class);
	}

	@Override
	public void serialize(ParameterSetting value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
		jgen.writeStartObject();
		jgen.writeObjectField("key", value.getKey());
		jgen.writeObjectField("name", value.getName());
		jgen.writeObjectField("topValue", value.getTopValue());
		jgen.writeObjectField("bottomValue", value.getBottomValue());
		jgen.writeObjectField("defaultValue", value.getDefaultValue());
		jgen.writeObjectField("maximumLength", value.getMaximumLength());
		jgen.writeObjectField("unit", value.getUnit());
		jgen.writeObjectField("placeholder", value.getPlaceholder());
		serializeListContainer("possibleValues", value.getPossibleValues(), jgen);
		jgen.writeEndObject();
	}

}
