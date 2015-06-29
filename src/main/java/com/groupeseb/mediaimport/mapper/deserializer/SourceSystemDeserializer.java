package com.groupeseb.mediaimport.mapper.deserializer;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.groupeseb.mediaimport.mapper.deserializer.ListDeserializer;
import com.groupeseb.mediaimport.mapper.deserializer.LocalizedKeyValueDeserializer;
import com.groupeseb.mediaimport.mapper.deserializer.helper.LocalizedKeyValueHelper;
import com.groupeseb.ofs.core.model.sourcesystem.SourceSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SourceSystemDeserializer extends Deserializer<SourceSystem> {

	private final com.groupeseb.mediaimport.mapper.deserializer.LocalizedKeyValueDeserializer localizedKeyValueDeserializer;

	@Autowired
	public SourceSystemDeserializer(LocalizedKeyValueDeserializer localizedKeyValueDeserializer) {
		super(SourceSystem.class);
		this.localizedKeyValueDeserializer = localizedKeyValueDeserializer;
	}

	@Override
	public SourceSystem deserialize(TreeNode treeNode, ObjectCodec codec) throws IOException {
		SourceSystem sourceSystem = LocalizedKeyValueHelper.deserialize(new SourceSystem(), treeNode);

		sourceSystem.withDomains(ListDeserializer.deserialize(localizedKeyValueDeserializer, treeNode, "domains"));

		return sourceSystem;
	}
}
