package com.groupeseb.mediaimport;

import com.groupeseb.mediaimport.exception.MediaImportException;
import com.groupeseb.mediaimport.model.TechniqueDTO;
import com.groupeseb.mediaimport.steps.CSVReader;
import com.groupeseb.mediaimport.steps.Transformer;
import com.groupeseb.mediaimport.steps.Writer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import retrofit.RetrofitError;

import java.io.IOException;
import java.util.Collection;

@Slf4j
@Configuration
@EnableAutoConfiguration
@ComponentScan("com.groupeseb")
/* cannot use @Application because default componentScan does not
	look into com.groupeseb.csvtobean
 */
public class Application implements CommandLineRunner {

	@Autowired
	CSVReader reader;

	@Autowired
	Transformer transformer;

	@Autowired
	Writer writer;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {

		Collection<TechniqueDTO> techniques = reader.getCSVTechnique("input/CSVMedia_technique_UTF8.csv");

		for (TechniqueDTO techniqueDTO : techniques) {
			try {
				writer.write(transformer.createTechnique(techniqueDTO));
			} catch (MediaImportException | IOException | RetrofitError e) {
				log.error("Exception occurred while handling {}", techniqueDTO.getKey(), e);
			}
		}

	}
}
