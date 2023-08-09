package mz.ac.unizambeze.unidesk.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mz.ac.unizambeze.unidesk.api.model.input.OcorrenciaInputModel;
import mz.ac.unizambeze.unidesk.domain.model.Ocorrencia;

@Component
public class OcorrenciaInputModelDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Ocorrencia toDomainObject(OcorrenciaInputModel ocorrenciaInputModel) {
		return modelMapper.map(ocorrenciaInputModel, Ocorrencia.class);
	}

}
