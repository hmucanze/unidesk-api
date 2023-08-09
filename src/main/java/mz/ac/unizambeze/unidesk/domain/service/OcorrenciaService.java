package mz.ac.unizambeze.unidesk.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mz.ac.unizambeze.unidesk.domain.exception.OcorrenciaInexistenteException;
import mz.ac.unizambeze.unidesk.domain.model.Ocorrencia;
import mz.ac.unizambeze.unidesk.domain.model.Utilizador;
import mz.ac.unizambeze.unidesk.domain.repository.OcorrenciaRepository;

@Service
public class OcorrenciaService {

	@Autowired
	private OcorrenciaRepository ocorrenciaRepository;
	
	@Autowired
	private UtilizadorService utilizadorService;
	
	public List<Ocorrencia> listar() {
		return ocorrenciaRepository.findAll();
	}
	
	public Ocorrencia buscarPorId(Long id) {
		return ocorrenciaRepository.findById(id).orElseThrow(
				() -> new OcorrenciaInexistenteException(id));
	}
	
	@Transactional
	public Ocorrencia salvar(Ocorrencia ocorrencia) {
		Utilizador utilizador = utilizadorService.buscarPorId(ocorrencia.getUtilizador().getId());
		
		ocorrencia.setUtilizador(utilizador);
		
		return ocorrenciaRepository.save(ocorrencia);
	}
	
	@Transactional
	public Ocorrencia actualizar(Ocorrencia ocorrencia, Long id) {
		Ocorrencia ocorrenciaRetornada = this.buscarPorId(id);
		
		BeanUtils.copyProperties(ocorrencia, ocorrenciaRetornada, "id", "dataCriacao");
		
		return ocorrenciaRetornada;
	}
	
	@Transactional
	public void remover(Long id) {
		try {
			ocorrenciaRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new OcorrenciaInexistenteException(id);
		}
	}
	
}
