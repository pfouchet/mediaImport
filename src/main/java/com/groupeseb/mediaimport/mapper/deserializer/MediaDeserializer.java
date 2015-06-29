package com.groupeseb.mediaimport.mapper.deserializer;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.groupeseb.mediaimport.mapper.deserializer.Deserializer;
import com.groupeseb.ofs.core.model.media.Media;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MediaDeserializer extends Deserializer<Media> {

	protected MediaDeserializer() {
		super(Media.class);
	}

	@Override
	public Media deserialize(TreeNode treeNode, ObjectCodec codec) throws IOException {
		return new Media();
	}
}
