package com.groupeseb.mediaimport;

import com.google.common.base.Preconditions;
import com.groupeseb.mediaimport.exception.nonterminal.AlreadyImportedException;
import com.groupeseb.mediaimport.exception.nonterminal.MediaImportException;
import com.groupeseb.mediaimport.model.DTO;
import com.groupeseb.mediaimport.steps.Writer;
import com.groupeseb.mediaimport.steps.reader.ReaderPicker;
import com.groupeseb.mediaimport.steps.transformer.TransformerPicker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import retrofit.RetrofitError;

import java.io.IOException;
import java.util.Collection;

@Slf4j
@EnableAutoConfiguration
@ComponentScan({"com.groupeseb.mediaimport", "com.groupeseb.csvtobean", "com.groupeseb.bus.commonapi.parser"})
/* cannot use @Application because default componentScan does not
	look into com.groupeseb.csvtobean
 */
public class Application implements CommandLineRunner {

	@Autowired
	private ReaderPicker reader;

	@Autowired
	private TransformerPicker transformerPicker;

	@Autowired
	private Writer writer;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	/**
	 * Program argument may be one of the following
	 *
	 * input/5_3_6_2_Media_technique_UTF8.csv
	 * input/appliances
	 * input/kitchenwares
	 * input/programs
	 */
	@Override
	public void run(String... strings) throws Exception {

		Collection<DTO> dtos = reader.getDTOs(
				Preconditions.checkNotNull(strings[0], "Resource name should be supplied to the program"),
				Preconditions.checkNotNull(strings[1], "source file path should be supplied to the program"));

		log.info("There are {} {} to import", dtos.size(), strings[0]);

		int failCounter = 0;
		int skipCounter = 0;
		for (DTO dto : dtos) {
			try {
				writer.write(transformerPicker.transform(dto));
			} catch (MediaImportException | IOException | RetrofitError e) {
				log.error("Exception occurred while handling {}", dto.getKey(), e);
				failCounter++;
			} catch (AlreadyImportedException ignored) {
				skipCounter++;
			}
		}
		log.warn("There were {} {} in error", strings[0], failCounter);
		log.warn("There were {} {} skipped", strings[0], skipCounter);

	}
}
