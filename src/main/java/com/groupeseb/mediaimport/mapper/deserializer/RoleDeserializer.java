package com.groupeseb.mediaimport.mapper.deserializer;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.groupeseb.mediaimport.mapper.deserializer.Deserializer;
import com.groupeseb.mediaimport.mapper.deserializer.helper.LocalizedKeyValueHelper;
import com.groupeseb.ofs.core.model.role.Role;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RoleDeserializer extends Deserializer<Role> {

	public RoleDeserializer() {
		super(Role.class);
	}

	@Override
	public Role deserialize(TreeNode treeNode, ObjectCodec codec) throws IOException {
		return LocalizedKeyValueHelper.deserialize(new Role(), treeNode)
				.withApplication(getText(treeNode.path("application")))
				.withResource(getText(treeNode.path("resource")));
	}
}
