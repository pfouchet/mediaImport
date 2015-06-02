package com.groupeseb.mediaimport.dcp;

import com.groupeseb.bus.commonapi.model.lite.MediaLite;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;

import java.io.InputStream;

public interface DCPMedia {

	@POST("/statics/upload")
	@Multipart
	public MediaLite createMedia(@Part("file")InputStream media);

}
