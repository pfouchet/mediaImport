package com.groupeseb.mediaimport.mapper.deserializer;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.groupeseb.mediaimport.mapper.deserializer.ListDeserializer;
import com.groupeseb.mediaimport.mapper.deserializer.LocalizedKeyValueDeserializer;
import com.groupeseb.mediaimport.mapper.deserializer.helper.LocalizedKeyValueHelper;
import com.groupeseb.ofs.core.model.preferencekey.PreferenceKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PreferenceKeyDeserializer extends Deserializer<PreferenceKey> {

	private final com.groupeseb.mediaimport.mapper.deserializer.LocalizedKeyValueDeserializer localizedKeyValueDeserializer;

	@Autowired
	public PreferenceKeyDeserializer(LocalizedKeyValueDeserializer localizedKeyValueDeserializer) {
		super(PreferenceKey.class);
		this.localizedKeyValueDeserializer = localizedKeyValueDeserializer;
	}

	@Override
	public PreferenceKey deserialize(TreeNode treeNode, ObjectCodec codec) throws IOException {
		PreferenceKey preferenceKey = LocalizedKeyValueHelper.deserialize(new PreferenceKey(), treeNode);

		if (exists(treeNode.path("type"))) {
			preferenceKey.withType(localizedKeyValueDeserializer.deserialize(treeNode.path("type"), codec));
		}

		preferenceKey.withPreferenceParameterKeys(ListDeserializer.deserialize(localizedKeyValueDeserializer, treeNode, "preferenceParameterKeys"));

		return preferenceKey;

	}
}
