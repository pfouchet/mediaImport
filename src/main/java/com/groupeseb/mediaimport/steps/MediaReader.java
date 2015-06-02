package com.groupeseb.mediaimport.steps;

import com.groupeseb.bus.commonapi.model.lite.MediaLite;
import com.groupeseb.mediaimport.dcp.DCPMedia;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retrofit.mime.TypedOutput;

import java.io.IOException;
import java.io.OutputStream;

@Slf4j
@Component
public class MediaReader {

	private final DCPMedia dcpMedia;

	@Autowired
	public MediaReader(DCPMedia dcpMedia) {
		this.dcpMedia = dcpMedia;
	}

	private ResponseBody read(String url) throws IOException {
		if (url != null && !url.isEmpty()) {

			OkHttpClient client = new OkHttpClient();

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
		return dcpMedia.createMedia(new MediaTypedOutput(responseBody.contentType(), responseBody.bytes()));
	}

	public static class MediaTypedOutput implements TypedOutput {

		private final MediaType mediaType;
		private final byte[] bytes;

		public MediaTypedOutput(MediaType mediaType, byte[] bytes) {
			this.mediaType = mediaType;
			this.bytes = bytes;
		}

		@Override
		public String fileName() {
			return "file";
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
