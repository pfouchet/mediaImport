package com.groupeseb.mediaimport.steps.reader;

import com.groupeseb.csvtobean.core.CSVParserService;
import com.groupeseb.csvtobean.exceptions.CSVToBeanException;
import com.groupeseb.mediaimport.exception.terminal.CSVParseException;
import com.groupeseb.mediaimport.model.DTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class CSVReader {

	private final CSVParserService parser;

	@Autowired
	public CSVReader(CSVParserService parser) {
		this.parser = parser;
	}

	public <T extends DTO> List<T> getDTOs(String filename, Class<T> clazz) {
		List<T> unfilteredTechniqueDTO;
		try {
			unfilteredTechniqueDTO = parser.csvToBeans(getClass().getClassLoader().getResourceAsStream(
					filename), clazz);
		} catch (CSVToBeanException e) {
			throw new CSVParseException(e);
		}
		return unfilteredTechniqueDTO;
	}
}
