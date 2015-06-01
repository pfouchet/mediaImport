package com.groupeseb.mediaimport.steps;

import com.groupeseb.csvtobean.core.CSVParserService;
import com.groupeseb.csvtobean.exceptions.CSVToBeanException;
import com.groupeseb.mediaimport.model.TechniqueDTO;
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

	public List<TechniqueDTO> getCSVTechnique(String filename) throws CSVToBeanException {
		return parser.csvToBeans(getClass().getClassLoader().getResourceAsStream(filename), TechniqueDTO.class);
	}

}
