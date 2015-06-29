package com.groupeseb.mediaimport.mapper.deserializer;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.groupeseb.mediaimport.mapper.deserializer.Deserializer;
import com.groupeseb.mediaimport.mapper.deserializer.helper.LocalizationHelper;
import com.groupeseb.ofs.core.model.commontypes.LocalizedValue;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LocalizedValueDeserializer extends Deserializer<LocalizedValue> {

	protected LocalizedValueDeserializer() {
		super(LocalizedValue.class);
	}

	@Override
	public LocalizedValue deserialize(TreeNode treeNode, ObjectCodec codec) throws IOException {
		return new LocalizedValue().withLocalization(LocalizationHelper.getLocalizations("localization", treeNode));
	}
}
