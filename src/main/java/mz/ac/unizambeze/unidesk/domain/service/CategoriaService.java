package mz.ac.unizambeze.unidesk.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mz.ac.unizambeze.unidesk.domain.exception.CategoriaInexistenteException;
import mz.ac.unizambeze.unidesk.domain.exception.EntidadeEmUsoException;
import mz.ac.unizambeze.unidesk.domain.model.Categoria;
import mz.ac.unizambeze.unidesk.domain.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	private static final String MSG_CATEGORIA_EM_USO = "Categoria de Código %d não pode ser removida pois está em uso";
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public List<Categoria> listar() {
		return categoriaRepository.findAll();
	}
	
	public Categoria buscarPorId(Long id) {
		return categoriaRepository.findById(id).orElseThrow(
				() -> new CategoriaInexistenteException(id));
	}
	
	@Transactional
	public Categoria salvar(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	@Transactional
	public Categoria actualizar(Categoria categoria, Long id) {
		Categoria categoriaRetornada = this.buscarPorId(id);
		
		BeanUtils.copyProperties(categoria, categoriaRetornada, "id");
		
		return categoriaRetornada;
	}
	
	@Transactional
	public void remover(Long id) {
		try { 
			categoriaRepository.deleteById(id);
			categoriaRepository.flush();
		} catch(EmptyResultDataAccessException e) {
			throw new CategoriaInexistenteException(id);
		} catch(DataIntegrityViolationException e) { 
			throw new EntidadeEmUsoException(
					String.format(MSG_CATEGORIA_EM_USO, id));
		}
	}

}
