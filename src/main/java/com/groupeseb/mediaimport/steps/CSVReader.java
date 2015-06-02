package com.groupeseb.mediaimport.steps;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.groupeseb.csvtobean.core.CSVParserService;
import com.groupeseb.csvtobean.exceptions.CSVToBeanException;
import com.groupeseb.mediaimport.model.TechniqueDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@Slf4j
public class CSVReader {

	private final CSVParserService parser;

	@Autowired
	public CSVReader(CSVParserService parser) {
		this.parser = parser;
	}

	public Collection<TechniqueDTO> getCSVTechnique(String filename) throws CSVToBeanException {
		return Collections2.filter(parser.csvToBeans(getClass().getClassLoader().getResourceAsStream(filename), TechniqueDTO.class), new Predicate<TechniqueDTO>() {
			@Override
			public boolean apply(TechniqueDTO input) {
				if (input.getMedias().isEmpty() || input.getMedias().get(0).getOriginalUrl().isEmpty()) {
					log.info("Reference Data {} was filtered out", input.getKey());
					return false;
				}
				return true;
			}
		});
	}

}
