package com.groupeseb.mediaimport.mapper.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.groupeseb.mediaimport.mapper.serializer.LocalizedKeyValueSerializer;
import com.groupeseb.ofs.core.model.basicpreparation.BasicPreparation;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class BasicPreparationSerializer extends LocalizedKeyValueSerializer<BasicPreparation> {
	public BasicPreparationSerializer() {
		super(BasicPreparation.class);
	}

	@Override
	public void serializeAdditionalValues(BasicPreparation value, JsonGenerator jgen) throws IOException {
		serializeLocalizations("goodFor", value.getGoodFor(), jgen);
		serializeListContainer("medias", value.getMedias(), jgen);
		jgen.writeObjectField("ingredients", value.getIngredients());
		jgen.writeObjectField("instructions", value.getInstructions());
	}

}
