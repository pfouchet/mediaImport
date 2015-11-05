package com.groupeseb.mediaimport.steps.reader;

import com.groupeseb.csvtobean.core.CSVParserService;
import com.groupeseb.csvtobean.exceptions.CSVToBeanException;
import com.groupeseb.mediaimport.exception.terminal.CSVParseException;
import com.groupeseb.mediaimport.model.DTO;
import com.groupeseb.mediaimport.model.TechniqueDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
@Slf4j
public class CSVReader implements IReader {

	private static final String RESOURCE_NAME = "techniques";
	private final CSVParserService parser;

	@Autowired
	public CSVReader(CSVParserService parser) {
		this.parser = parser;
	}

	@Override
	public Collection<DTO> getDTO(String filename) {
		List<TechniqueDTO> unfilteredTechniqueDTO = getTechniqueDTOs(filename);

		Collection<DTO> dtos = new ArrayList<>();

		for (TechniqueDTO techniqueDTO : unfilteredTechniqueDTO) {
			if (techniqueDTO.isMediaEmpty()) {
				log.info("Reference Data {} was filtered out", techniqueDTO.getKey());
			} else {
				dtos.add(techniqueDTO);
			}
		}

		return dtos;
	}

	private List<TechniqueDTO> getTechniqueDTOs(String filename) {
		List<TechniqueDTO> unfilteredTechniqueDTO;
		try {
			unfilteredTechniqueDTO = parser.csvToBeans(getClass().getClassLoader().getResourceAsStream(
					filename), TechniqueDTO.class);
		} catch (CSVToBeanException e) {
			throw new CSVParseException(e);
		}
		return unfilteredTechniqueDTO;
	}

	@Override
	public boolean isApplicable(String resourceName) {
		return StringUtils.equalsIgnoreCase(resourceName, RESOURCE_NAME);
	}
}
