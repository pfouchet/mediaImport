package com.groupeseb.mediaimport.steps.transformer;

import com.groupeseb.mediaimport.apis.DCP;
import com.groupeseb.mediaimport.apis.DCPMedia;
import com.groupeseb.mediaimport.apis.MediaReader;
import com.groupeseb.mediaimport.model.ApplianceDTO;
import com.groupeseb.mediaimport.model.DTO;
import com.groupeseb.mediaimport.steps.mapper.Mapper;
import com.groupeseb.ofs.core.model.appliance.Appliance;
import com.groupeseb.ofs.core.model.commontypes.LocalizedKeyValue;
import com.groupeseb.ofs.core.model.media.Media;
import com.groupeseb.ofs.core.model.resourcemedia.ResourceMedia;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
public class ApplianceTransformer implements ITransformer {

	private final DCP dcp;
	private final MediaReader mediaReader;
	private final Mapper mapper;

	@Autowired
	public ApplianceTransformer(DCP dcp, Mapper mapper, MediaReader mediaReader, DCPMedia dcpMedia) {
		this.dcp = dcp;
		this.mapper = mapper;
		this.mediaReader = mediaReader;
	}

	private ResourceMedia createResourceMedia(ApplianceDTO media) throws IOException {
		ResourceMedia rm = new ResourceMedia();
		Media media1 = mapper.map(mediaReader.createMedia(media), Media.class);

		rm.setMedia(media1);
		rm.setUid(UUID.randomUUID().toString());
		rm.setIsCoverMedia(true);
		return rm;
	}

	public Appliance createAppliance(ApplianceDTO dto) throws IOException {
		Appliance appliance = dcp.getAppliance(dto.getKey());

		appliance.getMedias().clear();

		appliance.getMedias().add(createResourceMedia(dto));

		return appliance;
	}

	@Override
	public boolean isApplicable(Class<?> clazz) {
		return ApplianceDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public LocalizedKeyValue transform(DTO dto) throws IOException {
		return createAppliance((ApplianceDTO) dto);
	}
}
