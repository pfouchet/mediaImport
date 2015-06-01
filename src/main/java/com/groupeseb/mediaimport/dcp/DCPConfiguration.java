package com.groupeseb.mediaimport.dcp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

@Component
@Slf4j
public class DCPConfiguration {

	private final RestAdapter restAdapter;

	@Autowired
	public DCPConfiguration(@Value("${dcp.host}") String host,
	                        @Value("puVdQHQNS3rjx2e8RDWMhkTmyGkXLCdC") final String apiKey) {
		restAdapter = new RestAdapter.Builder()
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
						log.debug(message);
					}
				})
				.build();
	}

	@Bean
	DCP dcp() {
		return restAdapter.create(DCP.class);
	}
}
