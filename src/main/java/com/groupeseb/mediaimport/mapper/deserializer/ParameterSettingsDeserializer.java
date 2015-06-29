package com.groupeseb.mediaimport.mapper.deserializer;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.groupeseb.mediaimport.mapper.deserializer.UnitDeserializer;
import com.groupeseb.mediaimport.mapper.deserializer.helper.LocalizedKeyValueHelper;
import com.groupeseb.ofs.core.model.commontypes.LocalizedKeyValue;
import com.groupeseb.ofs.core.model.parametersetting.ParameterSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ParameterSettingsDeserializer extends Deserializer<ParameterSetting> {

	private final com.groupeseb.mediaimport.mapper.deserializer.UnitDeserializer unitDeserializer;

	@Autowired
	public ParameterSettingsDeserializer(UnitDeserializer unitDeserializer) {
		super(ParameterSetting.class);
		this.unitDeserializer = unitDeserializer;
	}

	@Override
	public ParameterSetting deserialize(TreeNode treeNode, ObjectCodec codec) throws IOException {
		ParameterSetting parameterSetting = new ParameterSetting();
		parameterSetting.withName(LocalizedKeyValueHelper.deserialize(new LocalizedKeyValue(), treeNode.path("name")));
		parameterSetting.withKey(getText(treeNode.path("key")));
		parameterSetting.withDefaultValue(getText(treeNode.path("defaultValue")));
		parameterSetting.withBottomValue(getDouble(treeNode.path("bottomValue")));
		parameterSetting.withTopValue(getDouble(treeNode.path("topValue")));
		parameterSetting.withMaximumLength(getInt(treeNode.path("maximumLength")));
		parameterSetting.withPlaceholder(getText(treeNode.path("placeholder")));
		parameterSetting.withUnit(unitDeserializer.deserialize(treeNode.path("unit"), codec));
		parameterSetting.withPossibleValues(LocalizedKeyValueHelper.deserialize("possibleValues", LocalizedKeyValue.class, treeNode));
		return parameterSetting;
	}
}
