package com.groupeseb.mediaimport.dcp;

import com.groupeseb.bus.commonapi.model.lite.MediaLite;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.mime.TypedOutput;

public interface DCPMedia {

	@POST("/statics/upload")
	@Multipart
	public MediaLite createMedia(@Part("file") TypedOutput media);

}
