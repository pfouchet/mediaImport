package com.groupeseb.mediaimport.util;

import com.groupeseb.ofs.core.model.media.Media;
import com.groupeseb.ofs.core.model.resourcemedia.ResourceMedia;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public final class DeletionLogger {

	private DeletionLogger() {
	}

	public static void logDataForDeletion(List<ResourceMedia> resourceMedias) {
		if (resourceMedias != null) {
			for (ResourceMedia resourceMedia : resourceMedias) {
				Media media = resourceMedia.getMedia();
				if (media != null) {
					log.info("ResourceMedia : {}, Media {}, S4 original : {}, S4 thubmnail {}",
					         resourceMedia.getUid(),
					         media.getKey(),
					         media.getOriginalUrl(),
					         media.getThumbnailUrl());
				}
			}
		}
	}
}


