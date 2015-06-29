package com.groupeseb.mediaimport.mapper.deserializer;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.groupeseb.mediaimport.mapper.deserializer.Deserializer;
import com.groupeseb.mediaimport.mapper.deserializer.MediaDeserializer;
import com.groupeseb.mediaimport.mapper.deserializer.helper.LocalizationHelper;
import com.groupeseb.ofs.core.model.resourcemedia.ResourceMedia;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class ResourceMediaDeserializer extends Deserializer<ResourceMedia> {

	private final MediaDeserializer mediaDeserializer;

	@Autowired
	public ResourceMediaDeserializer(MediaDeserializer mediaDeserializer) {
		super(ResourceMedia.class);
		this.mediaDeserializer = mediaDeserializer;
	}

	@Override
	public ResourceMedia deserialize(TreeNode treeNode, ObjectCodec codec) throws IOException {
		ResourceMedia resourceMedia = new ResourceMedia();

		resourceMedia.withUid(getText(treeNode.path("uid")));
		resourceMedia.withCaptionText(LocalizationHelper.getLocalizations("captionText", treeNode));
		resourceMedia.withCaptionTitle(LocalizationHelper.getLocalizations("captionTitle", treeNode));
		resourceMedia.withIsCoverMedia(getBool(treeNode.path("isCoverMedia")));
		if(exists(treeNode.path("media"))) {
			resourceMedia.withMedia(mediaDeserializer.deserialize(treeNode.path("media"), codec));
		}
		resourceMedia.withTags(getListText(treeNode.path("tags")));
		resourceMedia.withOrderInTheSlideshow(getInt(treeNode.path("orderInTheSlideshow")));
		return resourceMedia;
	}
}
