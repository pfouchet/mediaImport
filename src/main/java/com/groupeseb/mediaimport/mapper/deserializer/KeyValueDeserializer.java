package com.groupeseb.mediaimport.mapper.deserializer;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.groupeseb.mediaimport.mapper.deserializer.Deserializer;
import com.groupeseb.ofs.core.model.commontypes.KeyValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Iterator;

@Slf4j
@Component
public class KeyValueDeserializer extends Deserializer<KeyValue> {

	protected KeyValueDeserializer() {
		super(KeyValue.class);
	}

	@Override
	public KeyValue deserialize(TreeNode treeNode, ObjectCodec codec) throws IOException {

		Iterator<String> keyValueIterator = treeNode.fieldNames();

		if (keyValueIterator.hasNext()) {
			KeyValue kv = new KeyValue();
			String key = keyValueIterator.next();

			kv.withKey(key);
			kv.withValue(getText(treeNode.path(key)));

			if (keyValueIterator.hasNext()) {
				log.warn("KeyValue should have only one key, first is {}, second is {}", key, keyValueIterator.next());
			}

			return kv;
		}

		return null;
	}
}
