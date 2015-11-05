package com.groupeseb.mediaimport.steps;

import com.groupeseb.mediaimport.apis.DCP;
import com.groupeseb.ofs.core.model.commontypes.LocalizedKeyValue;
import lombok.extern.slf4j.Slf4j;
import org.atteo.evo.inflector.English;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Writer {

	private final DCP dcp;


	@Autowired
	public Writer(DCP dcp) {
		this.dcp = dcp;
	}

	public void write(LocalizedKeyValue lkv) {
		if (lkv != null) {
			dcp.updateLKV(getPlural(lkv.getClass()), lkv.getKey(), lkv);
		}
	}

	private static String getPlural(Class<? extends LocalizedKeyValue> lkvClazz) {
		return English.plural(lkvClazz.getSimpleName().toLowerCase());
	}

}
