package com.groupeseb.mediaimport.mapper.deserializer;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.groupeseb.mediaimport.mapper.deserializer.MarketDeserializer;
import com.groupeseb.mediaimport.mapper.deserializer.helper.LocalizedKeyValueHelper;
import com.groupeseb.ofs.core.model.lang.Lang;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LangDeserializer extends Deserializer<Lang> {

	private final com.groupeseb.mediaimport.mapper.deserializer.MarketDeserializer marketDeserializer;

	protected LangDeserializer() {
		super(Lang.class);
		this.marketDeserializer = new MarketDeserializer(this);
	}

	@Override
	public Lang deserialize(TreeNode treeNode, ObjectCodec codec) throws IOException {
		Lang lang = LocalizedKeyValueHelper.deserialize(new Lang(), treeNode);

		if (exists(treeNode.path("defaultMarket"))) {
			lang.withDefaultMarket(marketDeserializer.deserialize(treeNode.path("defaultMarket"), codec));
		}

		lang.withIsDefault(getBool(treeNode.path("isDefault")));

		return lang;
	}
}
