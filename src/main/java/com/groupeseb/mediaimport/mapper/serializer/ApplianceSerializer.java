package com.groupeseb.mediaimport.mapper.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.groupeseb.mediaimport.mapper.serializer.LocalizedKeyValueSerializer;
import com.groupeseb.ofs.core.model.appliance.Appliance;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ApplianceSerializer extends LocalizedKeyValueSerializer<Appliance> {
	public ApplianceSerializer() {
		super(Appliance.class);
	}

	@Override
	public void serializeAdditionalValues(Appliance value, JsonGenerator jgen) throws IOException {
		jgen.writeObjectField("applianceGroup", value.getApplianceGroup());
		jgen.writeObjectField("isConnectable", value.getIsConnectable());
		jgen.writeObjectField("medias", value.getMedias());
	}

}
