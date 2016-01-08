package com.groupeseb.mediaimport.steps.reader;

import com.groupeseb.mediaimport.model.DTO;
import com.groupeseb.mediaimport.model.KitchenwareDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
@Slf4j
public class KitchenwareReader implements IReader {

	private static final String RESOURCE_NAME = "kitchenwares";

	@Override
	public Collection<DTO> getDTO(String filename) {

		List<DTO> appliances = new ArrayList<>();

		for (int i = 0; i < 10005; i++) {

			InputStream stream = getClass().getResourceAsStream(String.format("/%s/KITCHENWARE_%s/principal.png",
			                                                                  filename,
			                                                                  i));
			if (stream != null) {
				appliances.add(new KitchenwareDTO("KITCHENWARE_" + i, "png", stream));
			}
		}

		return appliances;
	}

	@Override
	public boolean isApplicable(String resourceName) {
		return StringUtils.equalsIgnoreCase(resourceName, RESOURCE_NAME);
	}
}