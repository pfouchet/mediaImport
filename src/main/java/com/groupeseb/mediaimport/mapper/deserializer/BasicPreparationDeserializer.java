package com.groupeseb.mediaimport.mapper.deserializer;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.groupeseb.mediaimport.mapper.deserializer.helper.LocalizationHelper;
import com.groupeseb.mediaimport.mapper.deserializer.helper.LocalizedKeyValueHelper;
import com.groupeseb.ofs.core.model.basicpreparation.BasicPreparation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class BasicPreparationDeserializer extends Deserializer<BasicPreparation> {

	private final com.groupeseb.mediaimport.mapper.deserializer.ResourceMediaDeserializer resourceMediaDeserializer;

	@Autowired
	protected BasicPreparationDeserializer(ResourceMediaDeserializer resourceMediaDeserializer) {
		super(BasicPreparation.class);
		this.resourceMediaDeserializer = resourceMediaDeserializer;
	}

	@Override
	public BasicPreparation deserialize(TreeNode treeNode, ObjectCodec codec) throws IOException {
		BasicPreparation basicPreparation = LocalizedKeyValueHelper.deserialize(new BasicPreparation(), treeNode);

		basicPreparation.withIngredients(getText(treeNode.path("ingredients")));
		basicPreparation.withInstructions(getText(treeNode.path("instructions")));

		basicPreparation.withGoodFor(LocalizationHelper.getLocalizations("goodFor", treeNode));

		basicPreparation.withMedias(ListDeserializer.deserialize(resourceMediaDeserializer, treeNode, "medias"));

		return basicPreparation;
	}
}
