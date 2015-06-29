package com.groupeseb.mediaimport.mapper.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.groupeseb.mediaimport.mapper.serializer.LocalizedKeyValueSerializer;
import com.groupeseb.ofs.core.model.preferencekey.PreferenceKey;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PreferenceKeySerializer extends LocalizedKeyValueSerializer<PreferenceKey> {

	public PreferenceKeySerializer() {
		super(PreferenceKey.class);
	}

	@Override
	void serializeAdditionalValues(PreferenceKey value, JsonGenerator jgen) throws IOException {
		jgen.writeObjectField("type", value.getType());
		serializeListContainer("preferenceParameterKeys", value.getPreferenceParameterKeys(), jgen);
	}
}
