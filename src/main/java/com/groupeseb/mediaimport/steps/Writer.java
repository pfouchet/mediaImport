package com.groupeseb.mediaimport.steps;

import com.groupeseb.mediaimport.dcp.DCP;
import com.groupeseb.mediaimport.exception.MediaImportException;
import com.groupeseb.ofs.core.model.technique.Technique;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Writer {

	private final DCP dcp;


	@Autowired
	public Writer(DCP dcp) {
		this.dcp = dcp;
	}

	public void write(Technique technique) throws MediaImportException {
		dcp.updateTechiques(technique.getKey(), technique);
	}
}