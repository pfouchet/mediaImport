package com.groupeseb.mediaimport.model;

import com.groupeseb.csvtobean.annotations.CSVField;
import com.groupeseb.csvtobean.annotations.CSVSegment;
import com.groupeseb.csvtobean.segment.CSVSegmentProcessor;
import com.groupeseb.ofs.core.model.commontypes.Localization;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class TechniqueDTO {

	@CSVField
	private String key;

	@CSVSegment(parameterType = ResourceMediaDTO.class)
	private List<ResourceMediaDTO> medias;

	@Data
	public static class ResourceMediaDTO {

		@CSVField(key = "media.originalUrl")
		private String originalUrl;

		@CSVField(key = "media.thumbnailUrl")
		private String thumbnailUrl;

		@CSVSegment(
				parameterType = Localization.class,
				processor = LocalizationProcessor.class,
				key = "captionTitle\\.localizations"
		)
		protected List<Localization> captionTitle;
	}

	public static class LocalizationProcessor implements CSVSegmentProcessor {
		@Override
		public <T> Object parseValue(List<Map<String, String>> list, Class<T> aClass) {
			List<Localization> localizations = new ArrayList<>();

			for (Map<String, String> inputField : list) {
				Localization localization = new Localization();
				localization.setLang(inputField.get("lang"));
				localization.setMarket(inputField.get("market"));
				localization.setValue(inputField.get("value"));


				if ((localization.getLang() != null && !localization.getLang().isEmpty())
						&& (localization.getMarket() != null && !localization.getMarket().isEmpty())
						&& (localization.getValue() != null && !localization.getValue().isEmpty())) {
					localizations.add(localization);
				}
			}

			return localizations;
		}
	}
}
