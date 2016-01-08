package com.groupeseb.mediaimport.steps.transformer;

import com.groupeseb.mediaimport.apis.DCP;
import com.groupeseb.mediaimport.apis.DCPMedia;
import com.groupeseb.mediaimport.apis.MediaReader;
import com.groupeseb.mediaimport.model.DTO;
import com.groupeseb.mediaimport.model.KitchenwareDTO;
import com.groupeseb.mediaimport.model.LKVFileDTO;
import com.groupeseb.mediaimport.steps.mapper.Mapper;
import com.groupeseb.ofs.core.model.commontypes.LocalizedKeyValue;
import com.groupeseb.ofs.core.model.kitchenware.Kitchenware;
import com.groupeseb.ofs.core.model.media.Media;
import com.groupeseb.ofs.core.model.resourcemedia.ResourceMedia;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
public class KitchenwareTransformer implements ITransformer {

	private final DCP dcp;
	private final MediaReader mediaReader;
	private final Mapper mapper;

	@Autowired
	public KitchenwareTransformer(DCP dcp, Mapper mapper, MediaReader mediaReader, DCPMedia dcpMedia) {
		this.dcp = dcp;
		this.mapper = mapper;
		this.mediaReader = mediaReader;
	}

	private ResourceMedia createResourceMedia(LKVFileDTO media) throws IOException {
		ResourceMedia rm = new ResourceMedia();
		Media media1 = mapper.map(mediaReader.createMedia(media), Media.class);

		rm.setMedia(media1);
		rm.setUid(UUID.randomUUID().toString());
		return rm;
	}

	public Kitchenware createKitchenware(LKVFileDTO dto) throws IOException {
		Kitchenware lkv = dcp.getKitchenware(dto.getKey());

		lkv.getMedias().clear();

		lkv.getMedias().add(createResourceMedia(dto));

		return lkv;
	}

	@Override
	public boolean isApplicable(Class<?> clazz) {
		return KitchenwareDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public LocalizedKeyValue transform(DTO dto) throws IOException {
		return createKitchenware((LKVFileDTO) dto);
	}
}
