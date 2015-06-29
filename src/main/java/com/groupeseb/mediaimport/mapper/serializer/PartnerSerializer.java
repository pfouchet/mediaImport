package com.groupeseb.mediaimport.mapper.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.groupeseb.mediaimport.mapper.serializer.LocalizedKeyValueSerializer;
import com.groupeseb.ofs.core.model.partner.Partner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PartnerSerializer extends LocalizedKeyValueSerializer<Partner> {
	public PartnerSerializer() {
		super(Partner.class);
	}

	@Override
	protected void serializeAdditionalValues(Partner value, JsonGenerator jgen) throws IOException {
		jgen.writeObjectField("website", value.getWebsite());
		serializeListContainer("medias", value.getMedias(), jgen);
	}
}
