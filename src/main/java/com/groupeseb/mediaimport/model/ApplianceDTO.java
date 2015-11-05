package com.groupeseb.mediaimport.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.InputStream;

@Data
@AllArgsConstructor
public class ApplianceDTO {

	private String key;

	private String extension;

	private InputStream inputStream;

}
