package com.groupeseb.mediaimport.mapper.deserializer;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.groupeseb.mediaimport.mapper.deserializer.Deserializer;
import com.groupeseb.mediaimport.mapper.deserializer.ListDeserializer;
import com.groupeseb.mediaimport.mapper.deserializer.ResourceMediaDeserializer;
import com.groupeseb.mediaimport.mapper.deserializer.helper.LocalizedKeyValueHelper;
import com.groupeseb.ofs.core.model.commontypes.LocalizedKeyValue;
import com.groupeseb.ofs.core.model.equipment.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class EquipmentDeserializer extends Deserializer<Equipment> {

	private final ResourceMediaDeserializer resourceMediaDeserializer;

	@Autowired
	public EquipmentDeserializer(ResourceMediaDeserializer resourceMediaDeserializer) {
		super(Equipment.class);
		this.resourceMediaDeserializer = resourceMediaDeserializer;
	}

	@Override
	public Equipment deserialize(TreeNode treeNode, ObjectCodec codec) throws IOException {
		Equipment equipment = LocalizedKeyValueHelper.deserialize(new Equipment(), treeNode);

		if (treeNode.path("brand").isObject()) {
			equipment.withBrand(LocalizedKeyValueHelper.deserialize(new LocalizedKeyValue(), treeNode.path("brand")));
		}
		equipment.withMedias(ListDeserializer.deserialize(resourceMediaDeserializer, treeNode, "medias"));

		return equipment;
	}
}
