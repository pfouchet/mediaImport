package com.groupeseb.mediaimport.mapper.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.groupeseb.mediaimport.mapper.serializer.AbstractRefDataSerializer;
import com.groupeseb.ofs.core.model.commontypes.Binary;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class BinarySerializer extends AbstractRefDataSerializer<Binary> {

	public BinarySerializer() {
		super(Binary.class);
	}

	@Override
	public void serialize(Binary value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
		jgen.writeStartObject();
		jgen.writeObjectField("applianceGroup", value.getApplianceGroup());
		jgen.writeObjectField("link", value.getLink());
		jgen.writeObjectField("checksum", value.getChecksum());
		jgen.writeObjectField("version", value.getVersion());
		jgen.writeEndObject();
	}
}
