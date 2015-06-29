package com.groupeseb.mediaimport.mapper.deserializer;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.groupeseb.mediaimport.mapper.deserializer.ListDeserializer;
import com.groupeseb.mediaimport.mapper.deserializer.helper.LocalizedKeyValueHelper;
import com.groupeseb.ofs.core.model.market.Market;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MarketDeserializer extends Deserializer<Market> {

	private final LangDeserializer langDeserializer;

	@Autowired
	public MarketDeserializer(LangDeserializer langDeserializer) {
		super(Market.class);
		this.langDeserializer = langDeserializer;
	}

	@Override
	public Market deserialize(TreeNode treeNode, ObjectCodec codec) throws IOException {
		Market market = new Market();

		market.withLangs(ListDeserializer.deserialize(langDeserializer, treeNode, "langs"));
		return LocalizedKeyValueHelper.deserialize(market, treeNode);
	}

}
