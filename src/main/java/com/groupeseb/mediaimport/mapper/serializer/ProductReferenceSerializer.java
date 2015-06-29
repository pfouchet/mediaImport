package com.groupeseb.mediaimport.mapper.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.groupeseb.mediaimport.mapper.serializer.LocalizedKeyValueSerializer;
import com.groupeseb.ofs.core.model.productreference.ProductReference;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ProductReferenceSerializer extends LocalizedKeyValueSerializer<ProductReference> {
	public ProductReferenceSerializer() {
		super(ProductReference.class);
	}

	@Override
	protected void serializeAdditionalValues(ProductReference value, JsonGenerator jgen) throws IOException {
		jgen.writeObjectField("externalId", value.getExternalId());
	}
}
