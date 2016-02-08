package com.groupeseb.mediaimport.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProgramDTO implements DTO {

	private final String key;
	private final List<MediaDTO> media = new ArrayList<>();
	private final MediaDTO extraData;

	public ProgramDTO(MediaDTO extraData, String key, MediaDTO... media) {
		this.key = key;
		this.extraData = extraData;

		for (MediaDTO mediaDTO : media) {
			if (mediaDTO != null) {
				this.media.add(mediaDTO);
			}
		}
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public boolean isMediaEmpty() {
		return false;
	}
}
