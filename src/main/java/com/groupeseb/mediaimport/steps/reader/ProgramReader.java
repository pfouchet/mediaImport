package com.groupeseb.mediaimport.steps.reader;

import com.groupeseb.mediaimport.model.DTO;
import com.groupeseb.mediaimport.model.MediaDTO;
import com.groupeseb.mediaimport.model.ProgramDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

		try {
			for (Path path : Files.newDirectoryStream(Paths.get("src/main/resources", filename))) {
				MediaDTO principal = getImage(path.resolve("Principal.png"), true);
				MediaDTO second = getImage(path.resolve("Ingredients.png"), false);
				if (principal != null || second != null) {
					programs.add(new ProgramDTO(StringUtils.substringAfterLast(path.toString(), File.separator),
					                            principal,
					                            second));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return programs;
	}

	private MediaDTO getImage(Path path, boolean isCover) {
		try {
			return new MediaDTO("png", new FileInputStream(path.toFile()), isCover);
		} catch (FileNotFoundException e) {
			return null;
		}
	}

	@Override
	public boolean isApplicable(String resourceName) {
		return StringUtils.equalsIgnoreCase(resourceName, RESOURCE_NAME);
	}
}