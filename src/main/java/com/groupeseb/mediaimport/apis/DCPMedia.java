package com.groupeseb.mediaimport.apis;

import com.groupeseb.bus.commonapi.model.lite.MediaLite;
import retrofit.http.Headers;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.mime.TypedOutput;

public interface DCPMedia {

	@POST("/statics/upload")
	@Headers("Accept: application/json")
	@Multipart
	MediaLite createMedia(@Part("file") TypedOutput media);

}
