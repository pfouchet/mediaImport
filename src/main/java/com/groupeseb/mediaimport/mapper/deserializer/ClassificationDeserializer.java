package com.groupeseb.mediaimport.mapper.deserializer;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.groupeseb.mediaimport.mapper.deserializer.ResourceMediaDeserializer;
import com.groupeseb.mediaimport.mapper.deserializer.helper.LocalizedKeyValueHelper;
import com.groupeseb.ofs.core.model.classification.Classification;
import com.groupeseb.ofs.core.model.commontypes.LocalizedKeyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ClassificationDeserializer extends Deserializer<Classification> {

	private final com.groupeseb.mediaimport.mapper.deserializer.ResourceMediaDeserializer resourceMediaDeserializer;
	private final LocalizedValueDeserializer localizedValueDeserializer;

	@Autowired
	protected ClassificationDeserializer(ResourceMediaDeserializer resourceMediaDeserializer,
	                                     LocalizedValueDeserializer localizedValueDeserializer) {
		super(Classification.class);
		this.resourceMediaDeserializer = resourceMediaDeserializer;
		this.localizedValueDeserializer = localizedValueDeserializer;
	}

	@Override
	public Classification deserialize(TreeNode treeNode, ObjectCodec codec) throws IOException {
		return LocalizedKeyValueHelper.deserialize(new Classification(), treeNode)
				.withType(LocalizedKeyValueHelper.deserialize(new LocalizedKeyValue(), treeNode.path("type")))
				.withMedias(ListDeserializer.deserialize(resourceMediaDeserializer, treeNode, "medias"))
				.withKeywords(getList(localizedValueDeserializer, treeNode.path("keywords"), codec));
	}
}
