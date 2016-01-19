package com.groupeseb.mediaimport.steps.transformer;

import com.groupeseb.mediaimport.apis.DCP;
import com.groupeseb.mediaimport.apis.DCPMedia;
import com.groupeseb.mediaimport.apis.MediaReader;
import com.groupeseb.mediaimport.model.DTO;
import com.groupeseb.mediaimport.model.LKVFileDTO;
import com.groupeseb.mediaimport.model.ProgramDTO;
import com.groupeseb.mediaimport.steps.mapper.Mapper;
import com.groupeseb.ofs.core.model.commontypes.LocalizedKeyValue;
import com.groupeseb.ofs.core.model.media.Media;
import com.groupeseb.ofs.core.model.program.Program;
import com.groupeseb.ofs.core.model.resourcemedia.ResourceMedia;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
public class ProgramTransformer implements ITransformer {

	private final DCP dcp;
	private final MediaReader mediaReader;
	private final Mapper mapper;

	@Autowired
	public ProgramTransformer(DCP dcp, Mapper mapper, MediaReader mediaReader, DCPMedia dcpMedia) {
		this.dcp = dcp;
		this.mapper = mapper;
		this.mediaReader = mediaReader;
	}

	private ResourceMedia getUrl(LKVFileDTO media) throws IOException {
		ResourceMedia rm = new ResourceMedia();
		Media media1 = mapper.map(mediaReader.createText(media), Media.class);

		rm.setMedia(media1);
		rm.setUid(UUID.randomUUID().toString());
		rm.setIsCoverMedia(true);
		return rm;
	}

	public Program createProgram(LKVFileDTO dto) throws IOException {
		Program program = dcp.getProgram(dto.getKey());

		// TODO program getUrl
		getUrl(dto);
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
