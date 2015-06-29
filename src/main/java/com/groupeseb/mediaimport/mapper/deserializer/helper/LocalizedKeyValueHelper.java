package com.groupeseb.mediaimport.mapper.deserializer.helper;

import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.node.ValueNode;
import com.groupeseb.mediaimport.mapper.deserializer.DeserializationException;
import com.groupeseb.ofs.core.model.commontypes.LocalizedKeyValue;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LocalizedKeyValueHelper {

	private LocalizedKeyValueHelper() {
		/* empty on purpose */
	}

	public static <T extends LocalizedKeyValue> T deserialize(T lkv, TreeNode treeNode) throws DeserializationException {
		if (!treeNode.path("key").isMissingNode()) {
			lkv.withKey(((ValueNode) treeNode.path("key")).textValue());
		}

		lkv.setName(LocalizationHelper.getLocalizations("name", treeNode));
		return lkv;
	}

	public static <T extends LocalizedKeyValue> List<T> deserialize(String fieldName, Class<T> parameterType, TreeNode treeNode) throws DeserializationException {
		TreeNode lkvListTreeNode = treeNode.path(fieldName);
		Iterator<String> keyLKVIterator = lkvListTreeNode.fieldNames();
		List<T> lkvs = new ArrayList<>();

		while (keyLKVIterator.hasNext()) {
			lkvs.add(deserialize(BeanUtils.instantiate(parameterType), lkvListTreeNode.path(keyLKVIterator.next())));
		}

		return lkvs;
	}

}
