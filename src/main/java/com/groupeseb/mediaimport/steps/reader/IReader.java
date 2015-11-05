package com.groupeseb.mediaimport.steps.reader;

import com.groupeseb.mediaimport.model.DTO;

import java.util.Collection;

public interface IReader {

	boolean isApplicable(String resourceName);

	Collection<DTO> getDTO(String filename);
}
