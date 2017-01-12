package com.groupeseb.mediaimport.apis;

import com.groupeseb.ofs.core.model.appliance.Appliance;
import com.groupeseb.ofs.core.model.commontypes.LocalizedKeyValue;
import com.groupeseb.ofs.core.model.kitchenware.Kitchenware;
import com.groupeseb.ofs.core.model.program.Program;
import com.groupeseb.ofs.core.model.technique.Technique;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.PATCH;
import retrofit.http.Path;

public interface DCP {

	@GET("/references/techniques/{id}")
	@Headers("Accept: application/x-extended+json")
	Technique getTechnique(@Path("id") String id);

	@GET("/references/appliances/{id}")
	@Headers("Accept: application/x-extended+json")
	Appliance getAppliance(@Path("id") String id);

	@GET("/references/programs/{id}")
	@Headers("Accept: application/x-extended+json")
	Program getProgram(@Path("id") String id);

	@GET("/references/kitchenwares/{id}")
	Kitchenware getKitchenware(@Path("id") String id);

	@PATCH("/references/{resource}/{lkvKey}")
	@Headers("Content-type: application/x-extended+json; charset=utf-8")
	Response updateLKV(@Path("resource") String resource, @Path("lkvKey") String lkvKey, @Body LocalizedKeyValue lkv);
}
