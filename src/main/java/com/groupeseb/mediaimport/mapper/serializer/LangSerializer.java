package com.groupeseb.mediaimport.mapper.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.groupeseb.mediaimport.mapper.serializer.LocalizedKeyValueSerializer;
import com.groupeseb.ofs.core.model.lang.Lang;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LangSerializer extends LocalizedKeyValueSerializer<Lang> {
	public LangSerializer() {
		super(Lang.class);
	}

	@Override
	protected void serializeAdditionalValues(Lang value, JsonGenerator jgen) throws IOException {
		jgen.writeObjectField("defaultMarket", value.getDefaultMarket());
		jgen.writeObjectField("isDefault", value.getIsDefault());
	}
}
