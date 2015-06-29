package com.groupeseb.mediaimport.mapper.serializer;

import lombok.Data;

import java.util.List;

@Data
public class ListContainer<T> {
	private List<T> content;

	public ListContainer(List<T> content) {
		this.content = content;
	}
}
