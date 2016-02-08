package com.groupeseb.mediaimport.apis;

import com.groupeseb.bus.commonapi.model.lite.MediaLite;
import com.groupeseb.mediaimport.model.LKVFileDTO;
import com.groupeseb.mediaimport.model.MediaDTO;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import retrofit.mime.TypedOutput;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class MediaReader {

	private final DCPMedia dcpMedia;
	private final boolean shouldUseProxy;

	@Autowired
	public MediaReader(DCPMedia dcpMedia,
	                   @Value("${use.apipro.proxy}") boolean shouldUseProxy) {
		this.dcpMedia = dcpMedia;
		this.shouldUseProxy = shouldUseProxy;
	}

	private ResponseBody read(String url) throws IOException {
		if (url != null && !url.isEmpty()) {

			OkHttpClient client = new OkHttpClient();

			client.setConnectTimeout(100, TimeUnit.MINUTES);
			client.setReadTimeout(100, TimeUnit.MINUTES);
			client.setWriteTimeout(100, TimeUnit.MINUTES);
			if (shouldUseProxy) {
				client.setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("gateway.seb.zscaler.net", 80)));
			}

			Request request = new Request.Builder()
					.url(url)
					.build();

			Response response = client.newCall(request).execute();
			if (!response.isSuccessful()) {
				throw new IOException(response.message());
			}
			return response.body();
		}

		return null;
	}

	public MediaLite createMedia(String url) throws IOException {
		ResponseBody responseBody = read(url);
		return dcpMedia.createMedia(new MediaTypedOutput(StringUtils.substringAfterLast(url, "."),
		                                                 responseBody.contentType(),
		                                                 responseBody.bytes()));
	}

	public MediaLite createMedia(LKVFileDTO dto) throws IOException {
		return dcpMedia.createMedia(new MediaTypedOutput(dto.getExtension(),
		                                                 MediaType.parse("image/png"),
		                                                 IOUtils.toByteArray(dto.getInputStream())));
	}

	public MediaLite createText(MediaDTO dto) throws IOException {
		return dcpMedia.createMedia(new MediaTypedOutput(dto.getExtension(),
		                                                 MediaType.parse("text/plain"),
		                                                 IOUtils.toByteArray(dto.getInputStream())));
	}

	public MediaLite createMedia(MediaDTO dto) throws IOException {
		return dcpMedia.createMedia(new MediaTypedOutput(dto.getExtension(),
		                                                 MediaType.parse("image/png"),
		                                                 IOUtils.toByteArray(dto.getInputStream())));
	}

	public static class MediaTypedOutput implements TypedOutput {

		private final String extension;

		private final MediaType mediaType;
		private final byte[] bytes;

		public MediaTypedOutput(String extension, MediaType mediaType, byte[] bytes) {
			this.extension = extension;
			this.mediaType = mediaType;
			this.bytes = bytes;
		}

		@Override
		public String fileName() {
			return "file." + extension;
		}

		@Override
		public String mimeType() {
			return mediaType.toString();
		}

		@Override
		public long length() {
			return bytes.length;
		}

		@Override
		public void writeTo(OutputStream out) throws IOException {
			out.write(bytes);
		}
	}

}
