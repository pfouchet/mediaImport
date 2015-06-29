package com.groupeseb.mediaimport.mapper.deserializer;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.groupeseb.mediaimport.mapper.deserializer.Deserializer;
import com.groupeseb.mediaimport.mapper.deserializer.helper.LocalizedKeyValueHelper;
import com.groupeseb.ofs.core.model.commontypes.Binary;
import com.groupeseb.ofs.core.model.commontypes.LocalizedKeyValue;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class BinaryDeserializer extends Deserializer<Binary> {

	protected BinaryDeserializer() {
		super(Binary.class);
	}

	@Override
	public Binary deserialize(TreeNode treeNode, ObjectCodec codec) throws IOException {
		Binary binary = new Binary();

		if (exists(treeNode.path("applianceGroup"))) {
			binary.withApplianceGroup(LocalizedKeyValueHelper.deserialize(new LocalizedKeyValue(), treeNode.path("applianceGroup")));
		}

		binary.withLink(getText(treeNode.path("link")));
		binary.withChecksum(getText(treeNode.path("checksum")));
		binary.withVersion(getText(treeNode.path("version")));

		return binary;
	}
}
