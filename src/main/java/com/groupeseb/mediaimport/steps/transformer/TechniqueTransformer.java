package com.groupeseb.mediaimport.steps.transformer;

import com.groupeseb.bus.commonapi.model.lite.MediaLite;
import com.groupeseb.mediaimport.apis.DCP;
import com.groupeseb.mediaimport.apis.DCPMedia;
import com.groupeseb.mediaimport.apis.MediaReader;
import com.groupeseb.mediaimport.exception.nonterminal.MediaImportException;
import com.groupeseb.mediaimport.model.DTO;
import com.groupeseb.mediaimport.model.TechniqueDTO;
import com.groupeseb.mediaimport.steps.mapper.Mapper;
import com.groupeseb.ofs.core.model.commontypes.LocalizedKeyValue;
import com.groupeseb.ofs.core.model.media.Media;
import com.groupeseb.ofs.core.model.resourcemedia.ResourceMedia;
import com.groupeseb.ofs.core.model.technique.Technique;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
public class TechniqueTransformer implements ITransformer {

	private final DCP dcp;
	private final MediaReader mediaReader;
	private final Mapper mapper;

	@Autowired
	public TechniqueTransformer(DCP dcp, Mapper mapper, MediaReader mediaReader, DCPMedia dcpMedia) {
		this.dcp = dcp;
		this.mapper = mapper;
		this.mediaReader = mediaReader;
	}

	public Technique createTechnique(TechniqueDTO techniqueDTO) throws MediaImportException, IOException {
		Technique technique = dcp.getTechnique(techniqueDTO.getKey());
		TechniqueDTO.ResourceMediaDTO resourceMediaDTO = techniqueDTO.getMedias().get(0);

		technique.getMedias().clear();
		technique.getMedias().add(createResourceMedia(resourceMediaDTO));

		return technique;
	}

	private Media createMedia(TechniqueDTO.ResourceMediaDTO rmDTO) throws IOException {
		Media media = mapper.map(mediaReader.createMedia(rmDTO.getOriginalUrl()), Media.class);

		if (StringUtils.isNotBlank(rmDTO.getThumbnailUrl())) {
			MediaLite thumbnailMedia = mediaReader.createMedia(rmDTO.getThumbnailUrl());
			media.setThumbnailUrl(thumbnailMedia.getThumbnail());
		}
		return media;
	}

	private ResourceMedia createResourceMedia(TechniqueDTO.ResourceMediaDTO rmDTO) throws IOException {
		ResourceMedia rm = new ResourceMedia();
		rm.setCaptionTitle(rmDTO.getCaptionTitle());
		rm.setMedia(createMedia(rmDTO));
		rm.setUid(UUID.randomUUID().toString());
		rm.setIsCoverMedia(true);
		return rm;
	}

	@Override
	public boolean isApplicable(Class<?> clazz) {
		return TechniqueDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public LocalizedKeyValue transform(DTO dto) throws IOException, MediaImportException {
		return createTechnique((TechniqueDTO) dto);
	}
}
