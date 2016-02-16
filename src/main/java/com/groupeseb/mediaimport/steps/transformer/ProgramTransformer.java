package com.groupeseb.mediaimport.steps.transformer;

import com.groupeseb.mediaimport.apis.DCP;
import com.groupeseb.mediaimport.apis.DCPMedia;
import com.groupeseb.mediaimport.apis.MediaReader;
import com.groupeseb.mediaimport.model.DTO;
import com.groupeseb.mediaimport.model.MediaDTO;
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
	public ProgramTransformer(DCP dcp, MediaReader mediaReader, DCPMedia dcpMedia, Mapper mapper) {
		this.dcp = dcp;
		this.mediaReader = mediaReader;
		this.mapper = mapper;
	}

	public Program createProgram(ProgramDTO dto) throws IOException {
		Program program = dcp.getProgram(dto.getKey());

		if (dto.getExtraData() != null) {
			program.setExtraDataFile(mediaReader.createText(dto.getExtraData()).getOriginal());
		}

		program.getMedias().clear();
		for (MediaDTO mediaDTO : dto.getMedia()) {
			program.getMedias().add(createResourceMedia(mediaDTO));
		}

		return program;
	}

	private ResourceMedia createResourceMedia(MediaDTO rmDTO) throws IOException {
		ResourceMedia rm = new ResourceMedia();
		rm.setMedia(mapper.map(mediaReader.createMedia(rmDTO), Media.class));
		rm.setUid(UUID.randomUUID().toString());
		rm.setIsCoverMedia(rmDTO.isCoverMedia());
		return rm;
	}

	@Override
	public boolean isApplicable(Class<?> clazz) {
		return ProgramDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public LocalizedKeyValue transform(DTO dto) throws IOException {
		return createProgram((ProgramDTO) dto);
	}
}
