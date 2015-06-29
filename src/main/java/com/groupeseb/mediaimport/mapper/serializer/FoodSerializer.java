package com.groupeseb.mediaimport.mapper.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.groupeseb.mediaimport.mapper.serializer.LocalizedKeyValueSerializer;
import com.groupeseb.ofs.core.model.food.Food;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class FoodSerializer extends LocalizedKeyValueSerializer<Food> {
	public FoodSerializer() {
		super(Food.class);
	}

	@Override
	protected void serializeAdditionalValues(Food value, JsonGenerator jgen) throws IOException {
		jgen.writeObjectField("summary", value.getSummary());
		jgen.writeObjectField("parentFood", value.getParentFood());
		serializeLocalizations("goodFor", value.getGoodFor(), jgen);
		serializeListContainer("medias", value.getMedias(), jgen);
		serializeListContainer("mayBeReplacedByList", value.getMayBeReplacedByList(), jgen);
		serializeListContainer("associatedFoods", value.getAssociatedFoods(), jgen);
		serializeListContainer("childFoods", value.getChildFoods(), jgen);
		serializeListContainer("seasonalities", value.getSeasonalities(), jgen);
		serializeListContainer("typesOfUse", value.getTypesOfUse(), jgen);
	}
}
