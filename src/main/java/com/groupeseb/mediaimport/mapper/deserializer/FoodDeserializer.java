package com.groupeseb.mediaimport.mapper.deserializer;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.groupeseb.mediaimport.mapper.deserializer.ListDeserializer;
import com.groupeseb.mediaimport.mapper.deserializer.helper.LocalizationHelper;
import com.groupeseb.mediaimport.mapper.deserializer.helper.LocalizedKeyValueHelper;
import com.groupeseb.ofs.core.model.food.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class FoodDeserializer extends Deserializer<Food> {

	private final ResourceMediaDeserializer resourceMediaDeserializer;

	private final LocalizedKeyValueDeserializer localizedKeyValueDeserializer;

	@Autowired
	public FoodDeserializer(ResourceMediaDeserializer resourceMediaDeserializer,
	                        LocalizedKeyValueDeserializer localizedKeyValueDeserializer) {
		super(Food.class);
		this.resourceMediaDeserializer = resourceMediaDeserializer;
		this.localizedKeyValueDeserializer = localizedKeyValueDeserializer;
	}

	@Override
	public Food deserialize(TreeNode treeNode, ObjectCodec codec) throws IOException {
		Food food = LocalizedKeyValueHelper.deserialize(new Food(), treeNode);

		food.withSummary(getText(treeNode.path("summary")));

		if (exists(treeNode.path("parentFood"))) {
			food.withParentFood(deserialize(treeNode.path("parentFood"), codec));
		}

		food.withMayBeReplacedByList(com.groupeseb.mediaimport.mapper.deserializer.ListDeserializer.deserialize(this, treeNode, "mayBeReplacedByList"));
		food.withAssociatedFoods(com.groupeseb.mediaimport.mapper.deserializer.ListDeserializer.deserialize(this, treeNode, "associatedFoods"));
		food.withTypesOfUse(com.groupeseb.mediaimport.mapper.deserializer.ListDeserializer.deserialize(localizedKeyValueDeserializer, treeNode, "typesOfUse"));
		food.withSeasonalities(com.groupeseb.mediaimport.mapper.deserializer.ListDeserializer.deserialize(localizedKeyValueDeserializer, treeNode, "seasonalities"));
		food.withChildFoods(com.groupeseb.mediaimport.mapper.deserializer.ListDeserializer.deserialize(this, treeNode, "childFoods"));
		food.withMedias(ListDeserializer.deserialize(resourceMediaDeserializer, treeNode, "medias"));

		if (exists(treeNode.path("goodFor"))) {
			food.withGoodFor(LocalizationHelper.getLocalizations("goodFor", treeNode));
		}

		return food;
	}
}
