package com.groupeseb.mediaimport.dcp;

import com.groupeseb.ofs.core.model.resourcemedia.ResourceMedia;
import com.groupeseb.ofs.core.model.technique.Technique;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

public interface DCP {

	@GET("/references/techniques/{id}")
	public Technique getTechnique(@Path("id") String id);

	@POST("/references/resourceMedia")
	public ResourceMedia createResourceMedia(@Body ResourceMedia resourceMedia);

	@PUT("/references/techniques/{techniqueId}")
	public Response updateTechiques(@Path("techniqueId") String techniqueId, @Body Technique technique);

}
