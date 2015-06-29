package com.groupeseb.mediaimport.mapper.deserializer;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.groupeseb.mediaimport.mapper.deserializer.ListDeserializer;
import com.groupeseb.mediaimport.mapper.deserializer.ResourceMediaDeserializer;
import com.groupeseb.mediaimport.mapper.deserializer.helper.LocalizationHelper;
import com.groupeseb.ofs.core.model.partner.Partner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PartnerDeserializer extends Deserializer<Partner> {

	private final com.groupeseb.mediaimport.mapper.deserializer.ResourceMediaDeserializer resourceMediaDeserializer;

	@Autowired
	public PartnerDeserializer(ResourceMediaDeserializer resourceMediaDeserializer) {
		super(Partner.class);
		this.resourceMediaDeserializer = resourceMediaDeserializer;
	}

	@Override
	public Partner deserialize(TreeNode treeNode, ObjectCodec codec) throws IOException {
		Partner partner = new Partner();

		partner.withKey(getText(treeNode.path("key")));
		partner.withWebsite(getText(treeNode.path("website")));
		partner.withName(LocalizationHelper.getLocalizations("name", treeNode));
		partner.withMedias(ListDeserializer.deserialize(resourceMediaDeserializer, treeNode, "medias"));

		return partner;
	}
}
