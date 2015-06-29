package com.groupeseb.mediaimport.mapper.deserializer;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.groupeseb.mediaimport.mapper.deserializer.Deserializer;
import com.groupeseb.mediaimport.mapper.deserializer.helper.LocalizationHelper;
import com.groupeseb.mediaimport.mapper.deserializer.helper.LocalizedKeyValueHelper;
import com.groupeseb.ofs.core.model.commontypes.LocalizedKeyValue;
import com.groupeseb.ofs.core.model.unit.Unit;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UnitDeserializer extends Deserializer<Unit> {

	protected UnitDeserializer() {
		super(Unit.class);
	}

	@Override
	public Unit deserialize(TreeNode treeNode, ObjectCodec codec) throws IOException {
		Unit unit = LocalizedKeyValueHelper.deserialize(new Unit(), treeNode);

		if(exists(treeNode.path("type"))) {
			unit.withType(LocalizedKeyValueHelper.deserialize(new LocalizedKeyValue(), treeNode.path("type")));
		}

		unit.withPluralName(LocalizationHelper.getLocalizations("pluralName", treeNode));
		unit.withAbbreviation(LocalizationHelper.getLocalizations("abbreviation", treeNode));

		return unit;
	}
}
