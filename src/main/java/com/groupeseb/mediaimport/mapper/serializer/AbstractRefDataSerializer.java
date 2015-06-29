package com.groupeseb.mediaimport.mapper.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.groupeseb.mediaimport.mapper.serializer.ListContainer;
import com.groupeseb.ofs.core.model.commontypes.Localization;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractRefDataSerializer<T> extends StdSerializer<T> {
	AbstractRefDataSerializer(Class<T> entityType) {
		super(entityType);
	}

	/**
	 * This constructor call a super() constructor with 2 parameters which handle generic types.
	 *
	 * @param entityType entityType
	 * @param dummy unused
	 */
	AbstractRefDataSerializer(Class<?> entityType, @SuppressWarnings("SameParameterValue") boolean dummy) {
		super(entityType, dummy);
	}

	@Override
	public abstract void serialize(T value, JsonGenerator jgen, SerializerProvider provider) throws IOException;

	void serializeLocalizations(String fieldName, List<Localization> localizations, JsonGenerator jgen) throws IOException {
		jgen.writeObjectFieldStart(fieldName);
		Map<String, List<Localization>> groupedLocalizations = getLocalizationsGroupedByMarket(localizations);

		for(Map.Entry<String,List<Localization>> localizationsGroupedByMarket : groupedLocalizations.entrySet()) {
			jgen.writeObjectFieldStart(localizationsGroupedByMarket.getKey());
			for(Localization localization : localizationsGroupedByMarket.getValue()) {
				jgen.writeObjectField(localization.getLang(), localization.getValue());
			}
			jgen.writeEndObject();
		}

		jgen.writeEndObject();
	}

	void serializeListContainer(String fieldName, List<?> list, JsonGenerator jgen) throws IOException {
		jgen.writeObjectField(fieldName, new ListContainer<>(list));
	}


	private Map<String, List<Localization>> getLocalizationsGroupedByMarket(List<Localization> localizations) {
		Map<String, List<Localization>> localizationsGroupedByMarket = new HashMap<>();

		for (Localization localization : localizations) {
			List<Localization> localizationsForOneMarket = localizationsGroupedByMarket.get(localization.getMarket());

			if (localizationsForOneMarket == null) {
				localizationsForOneMarket = new ArrayList<>();
				localizationsGroupedByMarket.put(localization.getMarket(), localizationsForOneMarket);
			}
			localizationsForOneMarket.add(localization);
		}

		return localizationsGroupedByMarket;
	}

}