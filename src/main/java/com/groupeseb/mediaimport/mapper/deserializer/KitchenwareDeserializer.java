package com.groupeseb.mediaimport.mapper.deserializer;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.groupeseb.mediaimport.mapper.deserializer.ListDeserializer;
import com.groupeseb.mediaimport.mapper.deserializer.ResourceMediaDeserializer;
import com.groupeseb.mediaimport.mapper.deserializer.helper.LocalizedKeyValueHelper;
import com.groupeseb.ofs.core.model.kitchenware.Kitchenware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class KitchenwareDeserializer extends Deserializer<Kitchenware> {

	private final com.groupeseb.mediaimport.mapper.deserializer.ResourceMediaDeserializer resourceMediaDeserializer;

	@Autowired
	public KitchenwareDeserializer(ResourceMediaDeserializer resourceMediaDeserializer) {
		super(com.groupeseb.mediaimport.mapper.deserializer.KitchenwareDeserializer.class);
		this.resourceMediaDeserializer = resourceMediaDeserializer;
	}

	@Override
	public Kitchenware deserialize(TreeNode treeNode, ObjectCodec codec) throws IOException {

		Kitchenware kitchenware = LocalizedKeyValueHelper.deserialize(new Kitchenware(), treeNode);
		kitchenware.withMedias(ListDeserializer.deserialize(resourceMediaDeserializer, treeNode, "medias"));

		return kitchenware;
	}
}
