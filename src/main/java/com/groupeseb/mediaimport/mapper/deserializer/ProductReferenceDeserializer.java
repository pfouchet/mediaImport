package com.groupeseb.mediaimport.mapper.deserializer;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.groupeseb.mediaimport.mapper.deserializer.ListDeserializer;
import com.groupeseb.mediaimport.mapper.deserializer.helper.LocalizedKeyValueHelper;
import com.groupeseb.ofs.core.model.productreference.ProductReference;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ProductReferenceDeserializer extends Deserializer<ProductReference> {

	public ProductReferenceDeserializer() {
		super(ProductReference.class);
	}

	@Override
	public ProductReference deserialize(TreeNode tree, ObjectCodec codec) throws IOException {
		ProductReference productReference = LocalizedKeyValueHelper.deserialize(new ProductReference(), tree);

		if (!tree.path("parent").isMissingNode()) {
			productReference.withParent(deserialize(tree.path("parent"), codec));
		}

		productReference.withExternalId(getText(tree.path("externalId")));
		productReference.withChildren(ListDeserializer.deserialize(this, tree, "children"));

		return productReference;
	}
}
