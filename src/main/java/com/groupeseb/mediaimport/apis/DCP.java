package com.groupeseb.mediaimport.apis;

import com.groupeseb.ofs.core.model.technique.Technique;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.PUT;
import retrofit.http.Path;

public interface DCP {

	@GET("/references/techniques/{id}")
	Technique getTechnique(@Path("id") String id);

	@PUT("/references/techniques/{techniqueId}")
	Response updateTechiques(@Path("techniqueId") String techniqueId, @Body Technique technique);

}
