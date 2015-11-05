package com.groupeseb.mediaimport.steps.transformer;

import com.groupeseb.mediaimport.exception.nonterminal.MediaImportException;
import com.groupeseb.mediaimport.exception.terminal.NoTransformerException;
import com.groupeseb.mediaimport.model.DTO;
import com.groupeseb.ofs.core.model.commontypes.LocalizedKeyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

@Component
public class TransformerPicker {

	private final List<ITransformer> transformers;

	@Autowired
	public TransformerPicker(List<ITransformer> transformers) {
		this.transformers = transformers;
	}

	public LocalizedKeyValue transform(@NotNull DTO dto) throws IOException, MediaImportException {
		for (ITransformer transformer : transformers) {
			if (transformer.isApplicable(dto.getClass())) {
				return transformer.transform(dto);
			}
		}

		throw new NoTransformerException(dto.getClass().getSimpleName());
	}
}
