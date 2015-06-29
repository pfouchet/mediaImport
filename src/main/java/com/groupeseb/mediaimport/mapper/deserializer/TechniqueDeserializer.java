package com.groupeseb.mediaimport.mapper.deserializer;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.groupeseb.mediaimport.mapper.deserializer.helper.LocalizedKeyValueHelper;
import com.groupeseb.ofs.core.model.technique.Technique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TechniqueDeserializer extends Deserializer<Technique> {

	private final KitchenwareDeserializer kitchenwareDeserializer;

	private final FoodDeserializer foodDeserializer;

	private final ResourceMediaDeserializer resourceMediaDeserializer;

	private final LocalizedKeyValueDeserializer localizedKeyValueDeserializer;

	@Autowired
	public TechniqueDeserializer(KitchenwareDeserializer kitchenwareDeserializer,
	                             FoodDeserializer foodDeserializer,
	                             ResourceMediaDeserializer resourceMediaDeserializer,
	                             LocalizedKeyValueDeserializer localizedKeyValueDeserializer) {
		super(Technique.class);
		this.kitchenwareDeserializer = kitchenwareDeserializer;
		this.foodDeserializer = foodDeserializer;
		this.resourceMediaDeserializer = resourceMediaDeserializer;
		this.localizedKeyValueDeserializer = localizedKeyValueDeserializer;
	}

	@Override
	public Technique deserialize(TreeNode treeNode, ObjectCodec codec) throws IOException {
		Technique technique = LocalizedKeyValueHelper.deserialize(new Technique(), treeNode);

		if (!treeNode.path("type").isMissingNode()) {
			technique.withType(localizedKeyValueDeserializer.deserialize(treeNode.path("type"), codec));
		}
		technique.withKitchenwares(ListDeserializer.deserialize(kitchenwareDeserializer, treeNode, "kitchenwares"));
		technique.withUtensils(ListDeserializer.deserialize(localizedKeyValueDeserializer, treeNode, "utensils"));
		technique.withFoods(ListDeserializer.deserialize(foodDeserializer, treeNode, "foods"));
		technique.withMedias(ListDeserializer.deserialize(resourceMediaDeserializer, treeNode, "medias"));

		return technique;
	}
}
