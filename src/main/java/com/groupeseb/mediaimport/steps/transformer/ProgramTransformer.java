package com.groupeseb.mediaimport.steps.transformer;

import com.groupeseb.mediaimport.apis.DCP;
import com.groupeseb.mediaimport.apis.DCPMedia;
import com.groupeseb.mediaimport.apis.MediaReader;
import com.groupeseb.mediaimport.model.DTO;
import com.groupeseb.mediaimport.model.LKVFileDTO;
import com.groupeseb.mediaimport.model.ProgramDTO;
import com.groupeseb.ofs.core.model.commontypes.LocalizedKeyValue;
import com.groupeseb.ofs.core.model.program.Program;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class ProgramTransformer implements ITransformer {

	private final DCP dcp;
	private final MediaReader mediaReader;

	@Autowired
	public ProgramTransformer(DCP dcp, MediaReader mediaReader, DCPMedia dcpMedia) {
		this.dcp = dcp;
		this.mediaReader = mediaReader;
	}

	public Program createProgram(LKVFileDTO dto) throws IOException {
		Program program = dcp.getProgram(dto.getKey());
		program.setExtraDataFile(mediaReader.createText(dto).getOriginal());
		return program;
	}

	@Override
	public boolean isApplicable(Class<?> clazz) {
		return ProgramDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public LocalizedKeyValue transform(DTO dto) throws IOException {
		return createProgram((LKVFileDTO) dto);
	}
}
