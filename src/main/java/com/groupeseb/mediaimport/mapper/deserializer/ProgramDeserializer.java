package com.groupeseb.mediaimport.mapper.deserializer;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.groupeseb.mediaimport.mapper.deserializer.Deserializer;
import com.groupeseb.mediaimport.mapper.deserializer.ListDeserializer;
import com.groupeseb.mediaimport.mapper.deserializer.ParameterSettingsDeserializer;
import com.groupeseb.mediaimport.mapper.deserializer.ResourceMediaDeserializer;
import com.groupeseb.mediaimport.mapper.deserializer.helper.LocalizedKeyValueHelper;
import com.groupeseb.ofs.core.model.commontypes.LocalizedKeyValue;
import com.groupeseb.ofs.core.model.program.Program;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ProgramDeserializer extends Deserializer<Program> {

	private final ParameterSettingsDeserializer parameterSettingsDeserializer;
	private final ResourceMediaDeserializer resourceMediaDeserializer;

	@Autowired
	public ProgramDeserializer(ParameterSettingsDeserializer parameterSettingsDeserializer,
	                           ResourceMediaDeserializer resourceMediaDeserializer) {
		super(Program.class);
		this.parameterSettingsDeserializer = parameterSettingsDeserializer;
		this.resourceMediaDeserializer = resourceMediaDeserializer;
	}

	@Override
	public Program deserialize(TreeNode treeNode, ObjectCodec codec) throws IOException {
		return LocalizedKeyValueHelper.deserialize(new Program(), treeNode)
				.withApplianceGroup(LocalizedKeyValueHelper.deserialize(new LocalizedKeyValue(), treeNode.path("applianceGroup")))
				.withParameterSettings(ListDeserializer.deserialize(parameterSettingsDeserializer, treeNode, "parameterSettings"))
				.withMedias(ListDeserializer.deserialize(resourceMediaDeserializer, treeNode, "medias"));
	}
}
