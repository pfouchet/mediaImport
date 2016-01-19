package com.groupeseb.mediaimport.steps.reader;

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
public class TechniqueCSVReader implements IReader {

	private static final String RESOURCE_NAME = "techniques";
	private final CSVReader reader;

	@Autowired
	public TechniqueCSVReader(CSVReader reader) {
		this.reader = reader;
	}

	@Override
	public Collection<DTO> getDTO(String filename) {
		List<TechniqueDTO> unfilteredTechniqueDTO = reader.getDTOs(filename, TechniqueDTO.class);

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

	@Override
	public boolean isApplicable(String resourceName) {
		return StringUtils.equalsIgnoreCase(resourceName, RESOURCE_NAME);
	}
}
