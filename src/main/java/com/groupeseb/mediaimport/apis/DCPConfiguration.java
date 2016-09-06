package com.groupeseb.mediaimport.apis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.groupeseb.bus.common.model.authentication.UserAuthenticationRequest;
import com.squareup.okhttp.OkHttpClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

import java.util.concurrent.TimeUnit;


@Component
@Slf4j
public class DCPConfiguration {

	private final String host;
	private final String apiKey;

	@Autowired
	public DCPConfiguration(@Value("${dcp.host:http://dev.api.openfoodsystem.com/common-api}") String host,
	                        @Value("puVdQHQNS3rjx2e8RDWMhkTmyGkXLCdC") String apiKey
	) {
		this.apiKey = apiKey;
		this.host = host;
	}

	String getAuthorization(String login, String password) {
		UserAuthenticationRequest userRequest = new UserAuthenticationRequest(login, password, null);
		return new RestAdapter.Builder()
				.setEndpoint(host)
				.setRequestInterceptor(new ApiKeyInterceptor())
				.setClient(new OkClient(okHttpClient()))
				.setLogLevel(RestAdapter.LogLevel.FULL)
				.setLog(new ApplicationLog())
				.build().create(DCPSecurity.class)
				.login(userRequest)
				.getToken();
	}

	@Bean
	DCP dcp(@Value("${dcp.login}") String login,
	        @Value("${dcp.password}") String password) {

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

		OkHttpClient httpClient = okHttpClient();
//		httpClient.setProxy(new Proxy(Proxy.Type.SOCKS, new InetSocketAddress("gateway.seb.zscaler.net", 80)));

		final String authorization = getAuthorization(login, password);

		return new RestAdapter.Builder()
				.setEndpoint(host)
				.setRequestInterceptor(new RequestInterceptor() {
					@Override
					public void intercept(RequestFacade request) {
						request.addHeader("apiKey", apiKey);
						request.addHeader("Authorization", "Bearer " + authorization);
					}
				})
				.setClient(new OkClient(httpClient))
				.setLogLevel(RestAdapter.LogLevel.FULL)
				.setLog(new ApplicationLog())
				.setConverter(new GsonConverter(gson))
				.build().create(DCP.class);
	}

	private static OkHttpClient okHttpClient() {
		OkHttpClient httpClient = new OkHttpClient();
		httpClient.setConnectTimeout(100, TimeUnit.MINUTES);
		httpClient.setReadTimeout(100, TimeUnit.MINUTES);
		httpClient.setWriteTimeout(100, TimeUnit.MINUTES);
		return httpClient;
	}

	@Bean
	DCPMedia dcpMedia() {

		return new RestAdapter.Builder()
				.setEndpoint(host)
				.setRequestInterceptor(new ApiKeyInterceptor())
				.setClient(new OkClient(okHttpClient()))
				.setLogLevel(RestAdapter.LogLevel.HEADERS_AND_ARGS)
				.setLog(new ApplicationLog())
				.build().create(DCPMedia.class);
	}

	private static class ApplicationLog implements RestAdapter.Log {
		@Override
		public void log(String message) {
			log.info(message);
		}
	}

	private class ApiKeyInterceptor implements RequestInterceptor {
		@Override
		public void intercept(RequestFacade request) {
			request.addHeader("apiKey", apiKey);
		}
	}
}
