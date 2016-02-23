package com.groupeseb.mediaimport.apis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
	                        @Value("6oS72zebYRv590jA5MhwE8W84YgXd4GL") String apiKey
	) {
		this.apiKey = apiKey;
		this.host = host;

	}

	@Bean
	DCP dcp() {

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

		OkHttpClient httpClient = new OkHttpClient();
		httpClient.setConnectTimeout(100, TimeUnit.MINUTES);
		httpClient.setReadTimeout(100, TimeUnit.MINUTES);
		httpClient.setWriteTimeout(100, TimeUnit.MINUTES);
//		httpClient.setProxy(new Proxy(Proxy.Type.SOCKS, new InetSocketAddress("gateway.seb.zscaler.net", 80)));

		return new RestAdapter.Builder()
				.setEndpoint(host)
				.setRequestInterceptor(new RequestInterceptor() {
					@Override
					public void intercept(RequestFacade request) {
						request.addHeader("apiKey", apiKey);
					}
				})
				.setClient(new OkClient(httpClient))
				.setLogLevel(RestAdapter.LogLevel.FULL)
				.setLog(new ApplicationLog())
				.setConverter(new GsonConverter(gson))
				.build().create(DCP.class);
	}

	@Bean
	DCPMedia dcpMedia() {

		OkHttpClient httpClient = new OkHttpClient();
		httpClient.setConnectTimeout(100, TimeUnit.MINUTES);
		httpClient.setReadTimeout(100, TimeUnit.MINUTES);
		httpClient.setWriteTimeout(100, TimeUnit.MINUTES);

		return new RestAdapter.Builder()
				.setEndpoint(host)
				.setRequestInterceptor(new RequestInterceptor() {
					@Override
					public void intercept(RequestFacade request) {
						request.addHeader("apiKey", apiKey);
						request.addHeader("Accept", "application/json");
					}
				})
				.setClient(new OkClient(httpClient))
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
}
