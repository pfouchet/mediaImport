package com.groupeseb.mediaimport.mapper.deserializer.helper;

import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.groupeseb.mediaimport.mapper.deserializer.DeserializationException;
import com.groupeseb.ofs.core.model.commontypes.Localization;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LocalizationHelper {

	private LocalizationHelper() {
		/* empty on purpose */
	}

	public static List<Localization> getLocalizations(String localizationFieldName, TreeNode treeNode) throws DeserializationException {

		List<Localization> localizations = new ArrayList<>();

		Iterator<String> marketIterator = treeNode.path(localizationFieldName).fieldNames();

		while (marketIterator.hasNext()) {
			String market = marketIterator.next();
			Iterator<String> langIterator = treeNode.path(localizationFieldName).path(market).fieldNames();

			if (!langIterator.hasNext()) {
				throw new DeserializationException(String.format("%s.%s.language is missing", localizationFieldName, market));
			}

			while (langIterator.hasNext()) {
				String lang = langIterator.next();
				localizations.add(new Localization().withLang(lang).withMarket(market).withValue(((TextNode) treeNode.path(localizationFieldName).path(market).path(lang)).textValue()));
			}
		}

		return localizations;
	}

}
