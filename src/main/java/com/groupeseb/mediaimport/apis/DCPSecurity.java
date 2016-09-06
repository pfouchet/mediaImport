package com.groupeseb.mediaimport.apis;

import com.groupeseb.bus.common.model.authentication.UserAuthenticationRequest;
import com.groupeseb.bus.common.model.authentication.UserAuthenticationResponse;
import retrofit.http.Body;
import retrofit.http.Headers;
import retrofit.http.POST;

public interface DCPSecurity {

	@POST("/login")
	@Headers("Content-type: application/json")
	UserAuthenticationResponse login(@Body UserAuthenticationRequest request);

}
