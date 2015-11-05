package com.groupeseb.mediaimport.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.InputStream;

@Data
@AllArgsConstructor
public class ApplianceDTO implements DTO {

	private String key;

	private String extension;

	private InputStream inputStream;

	@Override
	public boolean isMediaEmpty() {
		return inputStream != null;
	}
}
