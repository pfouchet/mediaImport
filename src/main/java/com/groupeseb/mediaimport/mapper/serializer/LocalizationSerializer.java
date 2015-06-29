package com.groupeseb.mediaimport.mapper.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.groupeseb.mediaimport.mapper.serializer.AbstractRefDataSerializer;
import com.groupeseb.ofs.core.model.commontypes.Localization;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LocalizationSerializer extends AbstractRefDataSerializer<Localization> {
	public LocalizationSerializer() {
		super(Localization.class);
	}

	@Override
	public void serialize(Localization value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
		jgen.writeStartObject();
		jgen.writeObjectField(value.getLang(), value.getValue());
		jgen.writeObjectField("market", value.getMarket());
		jgen.writeEndObject();
	}

}
