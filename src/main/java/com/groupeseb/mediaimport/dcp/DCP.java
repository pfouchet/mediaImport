package com.groupeseb.mediaimport.dcp;

import com.groupeseb.bus.commonapi.model.lite.MediaLite;
import com.groupeseb.ofs.core.model.resourcemedia.ResourceMedia;
import com.groupeseb.ofs.core.model.technique.Technique;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit.mime.TypedOutput;

import java.io.InputStream;

public interface DCP {

	@GET("/references/techniques/{id}")
	public Technique getTechnique(@Path("id") String id);

	@POST("/statics/upload")
	@Multipart
	public MediaLite createMedia(@Part("file")InputStream media);

	@POST("/references/resourceMedia")
	public ResourceMedia createResourceMedia(@Body ResourceMedia resourceMedia);

	@PUT("/references/techniques/{techniqueId}")
	public Response updateTechiques(@Path("techniqueId") String techniqueId, @Body Technique technique);

}
