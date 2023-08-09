package mz.ac.unizambeze.unidesk.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mz.ac.unizambeze.unidesk.domain.model.Ocorrencia;

@Service
public class FluxoOcorrenciaService {
	
	@Autowired
	private OcorrenciaService ocorrenciaService;
	
	@Transactional
	public void resolver(Long id) {
		Ocorrencia ocorrencia = ocorrenciaService.buscarPorId(id);
		ocorrencia.resolver();
	}
	
	@Transactional
	public void fechar(Long id) {
		Ocorrencia ocorrencia = ocorrenciaService.buscarPorId(id);
		ocorrencia.fechar();
	}
	
	@Transactional
	public void cancelar(Long id) {
		Ocorrencia ocorrencia = ocorrenciaService.buscarPorId(id);
		ocorrencia.cancelar();
	}

}
