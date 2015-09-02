package com.groupeseb.mediaimport.apis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.groupeseb.bus.commonapi.parser.deserializer.Deserializer;
import com.groupeseb.bus.commonapi.parser.serializer.AbstractRefDataSerializer;
import com.squareup.okhttp.OkHttpClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.JacksonConverter;

import java.util.List;
import java.util.concurrent.TimeUnit;


@Component
@Slf4j
public class DCPConfiguration {

	private final String host;
	private final String apiKey;
	private final ObjectMapper mapper;

	@Autowired
	public DCPConfiguration(@Value("${dcp.host:http://dev.api.openfoodsystem.com/common-api}") String host,
	                        @Value("puVdQHQNS3rjx2e8RDWMhkTmyGkXLCdC") String apiKey,
	                        List<AbstractRefDataSerializer<?>> refDataSerializers,
	                        List<Deserializer<?>> deserializers
	) {
		this.apiKey = apiKey;
		this.host = host;

		SimpleModule module = new SimpleModule();

		for (StdSerializer<?> serializer : refDataSerializers) {
			module.addSerializer(serializer);
		}

		for (Deserializer<?> deserializer : deserializers) {
			module.addDeserializer(deserializer.getParameterizedClass(), deserializer);
		}

		mapper = new ObjectMapper();
		mapper.registerModule(module);
	}

	@Bean
	DCP dcp() {

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
						request.addHeader("Content-type", "application/json");
					}
				})
				.setConverter(new JacksonConverter(mapper))
				.setClient(new OkClient(httpClient))
				.setLogLevel(RestAdapter.LogLevel.FULL)
				.setLog(new ApplicationLog())
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
