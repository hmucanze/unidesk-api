package mz.ac.unizambeze.unidesk.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mz.ac.unizambeze.unidesk.api.model.output.OcorrenciaOutputModel;
import mz.ac.unizambeze.unidesk.domain.model.Ocorrencia;

@Component
public class OcorrenciaOutputModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public OcorrenciaOutputModel toModel(Ocorrencia ocorrencia) {
		return modelMapper.map(ocorrencia, OcorrenciaOutputModel.class);
	}
	
	public List<OcorrenciaOutputModel> toCollectionModel(List<Ocorrencia> ocorrencias) {
		return ocorrencias.stream()
				.map(ocorrencia -> toModel(ocorrencia))
				.collect(Collectors.toList());
	}
}
