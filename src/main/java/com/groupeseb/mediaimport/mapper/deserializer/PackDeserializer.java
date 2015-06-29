package com.groupeseb.mediaimport.mapper.deserializer;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.groupeseb.mediaimport.mapper.deserializer.BinaryDeserializer;
import com.groupeseb.mediaimport.mapper.deserializer.Deserializer;
import com.groupeseb.mediaimport.mapper.deserializer.ListDeserializer;
import com.groupeseb.mediaimport.mapper.deserializer.ResourceMediaDeserializer;
import com.groupeseb.mediaimport.mapper.deserializer.helper.LocalizedKeyValueHelper;
import com.groupeseb.ofs.core.model.pack.Pack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PackDeserializer extends Deserializer<Pack> {

	private final ResourceMediaDeserializer resourceMediaDeserializer;

	private final BinaryDeserializer binaryDeserializer;

	@Autowired
	public PackDeserializer(ResourceMediaDeserializer resourceMediaDeserializer,
	                        BinaryDeserializer binaryDeserializer) {
		super(Pack.class);
		this.resourceMediaDeserializer = resourceMediaDeserializer;
		this.binaryDeserializer = binaryDeserializer;
	}

	@Override
	public Pack deserialize(TreeNode treeNode, ObjectCodec codec) throws IOException {
		Pack pack = LocalizedKeyValueHelper.deserialize(new Pack(), treeNode);

		pack.withMedias(ListDeserializer.deserialize(resourceMediaDeserializer, treeNode, "medias"));
		pack.withBinaries(getList(binaryDeserializer, treeNode.path("binaries"), codec));

		return pack;
	}
}
