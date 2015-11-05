package com.groupeseb.mediaimport.steps.transformer;

import com.groupeseb.mediaimport.exception.nonterminal.MediaImportException;
import com.groupeseb.mediaimport.model.DTO;
import com.groupeseb.ofs.core.model.commontypes.LocalizedKeyValue;

import java.io.IOException;

public interface ITransformer {

	boolean isApplicable(Class<?> clazz);

	LocalizedKeyValue transform(DTO dto) throws IOException, MediaImportException;
}
