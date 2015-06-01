package com.groupeseb.mediaimport.dcp;

import com.groupeseb.bus.commonapi.model.lite.MediaLite;
import com.groupeseb.bus.commonapi.model.lite.ResourceMediaLite;
import com.groupeseb.ofs.core.model.resourcemedia.ResourceMedia;
import com.groupeseb.ofs.core.model.technique.Technique;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface DCP {

	@GET("/references/techniques/{id}")
	public Technique getTechniques(@Path("id") String id);

	@POST("/statics/upload")
	public MediaLite createMedia(@Body Technique technique);

	@POST("/references/resourceMedia")
	public ResourceMediaLite createResourceMedia(@Body ResourceMedia resourceMedia);

	@POST("/references/techniques")
	public Response updateTechiques(@Body Technique technique);

}
