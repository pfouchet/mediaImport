package com.groupeseb.mediaimport.mapper.deserializer;

import com.fasterxml.jackson.core.TreeNode;
import com.groupeseb.mediaimport.mapper.deserializer.Deserializer;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class ListDeserializer {

	public static <T> List<T> deserialize(Deserializer<T> deserializer, TreeNode treeNode, String fieldName) throws IOException {
		List<T> deserializedList = new ArrayList<>();

		Iterator<String> keys = treeNode.path(fieldName).fieldNames();

		while (keys.hasNext()) {
			String key = keys.next();
			deserializedList.add(deserializer.deserialize(treeNode.path(fieldName).path(key), null));
		}

		return deserializedList;

	}
}