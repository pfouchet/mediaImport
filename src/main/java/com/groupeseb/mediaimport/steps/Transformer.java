package com.groupeseb.mediaimport.steps;

import com.groupeseb.bus.commonapi.model.lite.MediaLite;
import com.groupeseb.mediaimport.dcp.DCP;
import com.groupeseb.mediaimport.dcp.DCPMedia;
import com.groupeseb.mediaimport.exception.MediaImportException;
import com.groupeseb.mediaimport.model.TechniqueDTO;
import com.groupeseb.mediaimport.steps.mapper.Mapper;
import com.groupeseb.ofs.core.model.media.Media;
import com.groupeseb.ofs.core.model.resourcemedia.ResourceMedia;
import com.groupeseb.ofs.core.model.technique.Technique;
import com.squareup.okhttp.ResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class Transformer {

	private final DCP dcp;
	private final DCPMedia dcpMedia;
	private final MediaReader mediaReader;
	private final Mapper mapper;


	@Autowired
	public Transformer(DCP dcp, Mapper mapper, MediaReader mediaReader, DCPMedia dcpMedia) {
		this.dcp = dcp;
		this.mapper = mapper;
		this.mediaReader = mediaReader;
		this.dcpMedia = dcpMedia;
	}

	public Technique createTechnique(TechniqueDTO techniqueDTO) throws MediaImportException, IOException {
		Technique technique = dcp.getTechnique(techniqueDTO.getKey());
		TechniqueDTO.ResourceMediaDTO resourceMediaDTO = techniqueDTO.getMedias().get(0);

		technique.getMedias().add(createResourceMedia(resourceMediaDTO));

		return technique;
	}

	private MediaLite createMedia(String url) throws IOException {
		ResponseBody responseBody = mediaReader.read(url);
		return dcpMedia.createMedia(responseBody.byteStream());
	}

	private Media createMedia(TechniqueDTO.ResourceMediaDTO rmDTO) throws IOException {
		Media media = mapper.map(createMedia(rmDTO.getOriginalUrl()), Media.class);

		if (rmDTO.getThumbnailUrl() != null) {
			MediaLite thumbnailMedia = createMedia(rmDTO.getThumbnailUrl());
			media.setThumbnailUrl(thumbnailMedia.getThumbnail());
		}
		return media;
	}

	private ResourceMedia createResourceMedia(TechniqueDTO.ResourceMediaDTO rmDTO) throws IOException {
		ResourceMedia rm = new ResourceMedia();
		rm.setCaptionTitle(rmDTO.getCaptionTitle());
		rm.setMedia(createMedia(rmDTO));
		return dcp.createResourceMedia(rm);
	}
}
