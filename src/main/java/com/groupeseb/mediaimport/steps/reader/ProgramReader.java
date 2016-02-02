package com.groupeseb.mediaimport.steps.reader;

import com.groupeseb.mediaimport.model.DTO;
import com.groupeseb.mediaimport.model.ProgramDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
@Slf4j
public class ProgramReader implements IReader {

	private static final String RESOURCE_NAME = "programs";

	@Override
	public Collection<DTO> getDTO(String filename) {

		List<DTO> appliances = new ArrayList<>();

		for (int i = 1000; i < 2000; i++) {

			InputStream stream = getClass().getResourceAsStream(String.format("/%s/PROGRAM_00%s/Principal.json.txt",
			                                                                  filename,
			                                                                  i));
			if (stream != null) {
				appliances.add(new ProgramDTO("PROGRAM_00" + i, "txt", stream));
			}
		}

		return appliances;
	}

	@Override
	public boolean isApplicable(String resourceName) {
		return StringUtils.equalsIgnoreCase(resourceName, RESOURCE_NAME);
	}
}