package com.groupeseb.mediaimport.steps.reader;

import com.groupeseb.mediaimport.exception.terminal.NoReaderException;
import com.groupeseb.mediaimport.model.DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class ReaderPicker {

	private final List<IReader> readers;

	@Autowired
	public ReaderPicker(List<IReader> readers) {
		this.readers = readers;
	}

	public Collection<DTO> getDTOs(String resourceName, String filename) {
		for (IReader reader : readers) {
			if (reader.isApplicable(resourceName)) {
				return reader.getDTO(filename);
			}
		}

		throw new NoReaderException(resourceName);
	}
}
