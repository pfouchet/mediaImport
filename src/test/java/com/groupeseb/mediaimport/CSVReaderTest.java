package com.groupeseb.mediaimport;

import com.groupeseb.csvtobean.exceptions.CSVToBeanException;
import com.groupeseb.mediaimport.dto.TechniqueDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "/test.xml")
public class CSVReaderTest {

	@Autowired
	private CSVReader reader;

	@Test
	public void test() throws CSVToBeanException {
		List<TechniqueDTO> techniques = reader.getCSVTechnique("CSVMedia_technique_UTF8.csv");

		assertNotNull("techniques should not be null", techniques);
		assertEquals("techniques[0].key should not be null", "TECHNIQUE_101855", techniques.get(0).getKey());
		assertEquals("techniques[0].key should not be null", "http://monactifry.prod.apipro.groupe-seb.com/videos/58945.mp4", techniques.get(0).getMedias().get(0).getOriginalUrl());
	}
}
