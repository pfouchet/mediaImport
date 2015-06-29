package com.groupeseb.mediaimport.mapper.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.groupeseb.mediaimport.mapper.serializer.LocalizedKeyValueSerializer;
import com.groupeseb.ofs.core.model.market.Market;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MarketSerializer extends LocalizedKeyValueSerializer<Market> {
	public MarketSerializer() {
		super(Market.class);
	}

	@Override
	protected void serializeAdditionalValues(Market value, JsonGenerator jgen) throws IOException {
		serializeListContainer("langs", value.getLangs(), jgen);
	}
}
