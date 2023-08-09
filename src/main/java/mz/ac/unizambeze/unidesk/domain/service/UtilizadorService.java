package mz.ac.unizambeze.unidesk.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mz.ac.unizambeze.unidesk.domain.exception.UtilizadorInexistenteException;
import mz.ac.unizambeze.unidesk.domain.model.Utilizador;
import mz.ac.unizambeze.unidesk.domain.repository.UtilizadorRepository;

@Service
public class UtilizadorService {

	@Autowired
	private UtilizadorRepository utilizadorRepository;
	
	public List<Utilizador> listar() {
		return utilizadorRepository.findAll();
	} 
	
	public Utilizador buscarPorId(Long id) {
		return utilizadorRepository.findById(id).orElseThrow(
				() -> new UtilizadorInexistenteException(id));
	}
	
	@Transactional
	public Utilizador salvar(Utilizador utilizador) {
		return utilizadorRepository.save(utilizador);
	}
	
	@Transactional
	public Utilizador actualizar(Utilizador utilizador, Long id) {
		Utilizador utilizadorRetornado = this.buscarPorId(id);
		
		BeanUtils.copyProperties(utilizadorRetornado, utilizadorRetornado, "id");
		
		return utilizadorRetornado;
	}
	
	@Transactional
	public void remover(Long id) {
		try {
			utilizadorRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new UtilizadorInexistenteException(id);
		}
	}
	
}
