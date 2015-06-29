package com.groupeseb.mediaimport.mapper.deserializer;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.groupeseb.mediaimport.mapper.deserializer.Deserializer;
import com.groupeseb.mediaimport.mapper.deserializer.helper.LocalizedKeyValueHelper;
import com.groupeseb.ofs.core.model.commontypes.LocalizedKeyValue;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LocalizedKeyValueDeserializer extends Deserializer<LocalizedKeyValue> {

	public LocalizedKeyValueDeserializer() {
		super(LocalizedKeyValue.class);
	}

	@Override
	public LocalizedKeyValue deserialize(TreeNode treeNode, ObjectCodec codec) throws IOException {
		LocalizedKeyValue lkv = new LocalizedKeyValue();

		return LocalizedKeyValueHelper.deserialize(lkv, treeNode);
	}
}
