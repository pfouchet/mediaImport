package com.groupeseb.mediaimport.steps.reader;

import com.groupeseb.mediaimport.model.ApplianceDTO;
import com.groupeseb.mediaimport.model.DTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Component
@Slf4j
public class ApplianceReader implements IReader {

	private static final String RESOURCE_NAME = "appliances";

	@Override
	public Collection<DTO> getDTO(String filename) {

		List<DTO> appliances = new ArrayList<>();

		try (DirectoryStream<Path> allAppliances = Files.newDirectoryStream(Paths.get("src/main/resources",
		                                                                              filename))) {
			for (Path path : allAppliances) {
				try (DirectoryStream<Path> appliancePath = Files.newDirectoryStream(path)) {
					Iterator<Path> iterator = appliancePath.iterator();
					if (iterator.hasNext()) {
						Path applianceImage = iterator.next();

						appliances.add(new ApplianceDTO(StringUtils.substringAfterLast(path.toString(),
						                                                               File.separator),
						                                StringUtils.substringAfterLast(applianceImage.toString(),
						                                                               "."),
						                                new FileInputStream(applianceImage.toFile())));
					}
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return appliances;
	}

	@Override
	public boolean isApplicable(String resourceName) {
		return StringUtils.equalsIgnoreCase(resourceName, RESOURCE_NAME);
	}
}