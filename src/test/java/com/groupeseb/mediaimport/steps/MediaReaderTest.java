package com.groupeseb.mediaimport.steps;

import org.junit.Test;

import java.io.IOException;

public class MediaReaderTest {

	@Test
	public void test() throws IOException {
		MediaReader mediaReader = new MediaReader();

		mediaReader.read("http://monactifry.prod.apipro.groupe-seb.com/thumbs/original/58945.jpg");
	}

}
