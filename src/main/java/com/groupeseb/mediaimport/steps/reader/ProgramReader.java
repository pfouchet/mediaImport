package com.groupeseb.mediaimport.steps.reader;

import com.groupeseb.mediaimport.model.DTO;
import com.groupeseb.mediaimport.model.MediaDTO;
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

		List<DTO> programs = new ArrayList<>();

		for (int i = 1000; i < 2000; i++) {

			String index = String.valueOf(i);
			MediaDTO extraData = getExtraData(index, filename);
			MediaDTO principal = getMainImage(index, filename);
			MediaDTO second = getOtherImage(index, filename, "Ingredients.png");

			if (extraData != null || principal != null || second != null) {
				programs.add(new ProgramDTO(extraData, "PROGRAM_00" + index, principal, second));
			}
		}

		return programs;
	}

	private MediaDTO getExtraData(String i, String directory) {
		InputStream stream = getClass().getResourceAsStream(String.format("/%s/PROGRAM_00%s/Principal.json.txt",
		                                                                  directory,
		                                                                  i));
		if (stream != null) {
			return new MediaDTO("txt", stream, false);
		}

		return null;
	}

	private MediaDTO getMainImage(String i, String directory) {
		InputStream stream = getClass().getResourceAsStream(String.format("/%s/PROGRAM_00%s/Principal.png",
		                                                                  directory,
		                                                                  i));
		if (stream != null) {
			return new MediaDTO("png", stream, true);
		}

		return null;

	}

	private MediaDTO getOtherImage(String i, String directory, String name) {
		InputStream stream = getClass().getResourceAsStream(String.format("/%s/PROGRAM_00%s/%s",
		                                                                  directory,
		                                                                  i,
		                                                                  name));
		if (stream != null) {
			return new MediaDTO("png", stream, false);
		}

		return null;
	}

	@Override
	public boolean isApplicable(String resourceName) {
		return StringUtils.equalsIgnoreCase(resourceName, RESOURCE_NAME);
	}
}