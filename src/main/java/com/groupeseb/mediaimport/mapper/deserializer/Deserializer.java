package com.groupeseb.mediaimport.mapper.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ValueNode;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Deserializer<T> extends StdDeserializer<T> {
	protected Deserializer(Class<?> vc) {
		super(vc);
	}

	public Class getParameterizedClass() {
		return (Class) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public T deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
		return deserialize(jp.readValueAsTree(), jp.getCodec());
	}

	protected final <J> J deserialize(ObjectCodec codec, TreeNode treeNode, Class<J> clazz) throws IOException {
		if(exists(treeNode)) {
			return codec.readValue(treeNode.traverse(codec), clazz);
		}
		return null;
	}

	public abstract T deserialize(TreeNode treeNode, ObjectCodec codec) throws IOException;

	private ValueNode getValueNodeOrNull(TreeNode node) {
		if (ValueNode.class.isAssignableFrom(node.getClass())
				&& !node.isMissingNode()
				&& !((ValueNode) node).isNull()) {
			return (ValueNode) node;
		} else {
			return null;
		}
	}

	protected final boolean exists(TreeNode node) {
		return !node.isMissingNode() && !((JsonNode) node).isNull();
	}

	protected final Integer getInt(TreeNode node) {
		ValueNode valueNode = getValueNodeOrNull(node);
		if (valueNode == null) {
			return null;
		} else {
			return valueNode.intValue();
		}
	}

	protected final Double getDouble(TreeNode node) {
		ValueNode valueNode = getValueNodeOrNull(node);
		if (valueNode == null) {
			return null;
		} else {
			return valueNode.doubleValue();
		}
	}

	protected final String getText(TreeNode node) {
		ValueNode valueNode = getValueNodeOrNull(node);
		if (valueNode == null) {
			return null;
		} else {
			return valueNode.textValue();
		}
	}

	@SuppressWarnings("unchecked")
	protected final List<String> getListText(TreeNode node) {
		List<String> textList = new ArrayList<>();
		if (exists(node) && node.isArray()) {
			Iterator<JsonNode> elementsIt = ((ArrayNode) node).elements();
			while (elementsIt.hasNext()) {
				textList.add(elementsIt.next().asText());
			}
		}
		return textList;
	}

	protected final <J> List<J> getList(com.groupeseb.mediaimport.mapper.deserializer.Deserializer<J> deserializer, TreeNode node, ObjectCodec codec) throws IOException {
		List<J> list = new ArrayList<>();
		if (exists(node) && node.isArray()) {
			Iterator<JsonNode> elementsIt = ((ArrayNode) node).elements();
			while (elementsIt.hasNext()) {
				list.add(deserializer.deserialize(elementsIt.next(), codec));
			}
		}
		return list;
	}

	protected final Boolean getBool(TreeNode node) {
		ValueNode valueNode = getValueNodeOrNull(node);
		if (valueNode == null) {
			return null;
		} else {
			return valueNode.booleanValue();
		}
	}

}
