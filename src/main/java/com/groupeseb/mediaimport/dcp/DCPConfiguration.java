package com.groupeseb.mediaimport.dcp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

@Component
@Slf4j
public class DCPConfiguration {

	private final String host;
	private final String apiKey;

	@Autowired
	public DCPConfiguration(@Value("${dcp.host}") String host,
	                        @Value("puVdQHQNS3rjx2e8RDWMhkTmyGkXLCdC") final String apiKey) {
		this.apiKey = apiKey;
		this.host = host;
	}

	@Bean
	DCP dcp() {
		return new RestAdapter.Builder()
				.setEndpoint(host)
				.setRequestInterceptor(new RequestInterceptor() {
					@Override
					public void intercept(RequestFacade request) {
						request.addHeader("apiKey", apiKey);
						request.addHeader("Accept", "application/json");
						request.addHeader("Content-type", "application/json");
					}
				})
				.setLogLevel(RestAdapter.LogLevel.FULL)
				.setLog(new RestAdapter.Log() {
					@Override
					public void log(String message) {
						log.info(message);
					}
				})
				.build().create(DCP.class);
	}

	@Bean
	DCPMedia dcpMedia() {
		return new RestAdapter.Builder()
				.setEndpoint(host)
				.setRequestInterceptor(new RequestInterceptor() {
					@Override
					public void intercept(RequestFacade request) {
						request.addHeader("apiKey", apiKey);
						request.addHeader("Accept", "application/json");
					}
				})
				.setLogLevel(RestAdapter.LogLevel.HEADERS_AND_ARGS)
				.setLog(new RestAdapter.Log() {
					@Override
					public void log(String message) {
						log.info(message);
					}
				})
				.build().create(DCPMedia.class);
	}
}
