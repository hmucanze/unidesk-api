package mz.ac.unizambeze.unidesk.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mz.ac.unizambeze.unidesk.api.assembler.CategoriaInputModelDisassembler;
import mz.ac.unizambeze.unidesk.api.assembler.CategoriaOutputModelAssembler;
import mz.ac.unizambeze.unidesk.api.model.input.CategoriaInputModel;
import mz.ac.unizambeze.unidesk.api.model.output.CategoriaOutputModel;
import mz.ac.unizambeze.unidesk.domain.model.Categoria;
import mz.ac.unizambeze.unidesk.domain.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private CategoriaOutputModelAssembler categoriaOutputModelAssembler;
	
	@Autowired
	private CategoriaInputModelDisassembler categoriaInputModelDisassembler;
	
	@GetMapping
	public List<CategoriaOutputModel> listar() {
		List<Categoria> categorias = categoriaService.listar();
		
		return categoriaOutputModelAssembler.toCollectionModel(categorias);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoriaOutputModel> buscarPorId(@PathVariable Long id) {
		Categoria categoria = categoriaService.buscarPorId(id);
		
		return ResponseEntity.ok(categoriaOutputModelAssembler.toModel(categoria));
	}
	
	@PostMapping
	public ResponseEntity<CategoriaOutputModel> salvar(@RequestBody @Valid CategoriaInputModel categoriaInputModel) {
		Categoria categoria = categoriaInputModelDisassembler.toDomainObject(categoriaInputModel);
		
		CategoriaOutputModel categoriaOutputModel = categoriaOutputModelAssembler
				.toModel(categoriaService.salvar(categoria));
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(categoriaOutputModel);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CategoriaOutputModel> actualizar(@RequestBody @Valid CategoriaInputModel categoriaInputModel,
			@PathVariable Long id) {
		Categoria categoria = categoriaInputModelDisassembler.toDomainObject(categoriaInputModel);
		
		CategoriaOutputModel categoriaOutputModel = categoriaOutputModelAssembler
				.toModel(categoriaService.actualizar(categoria, id));
		
		return ResponseEntity.ok(categoriaOutputModel);
	}
	
	@DeleteMapping("/{id}")
	public void remover(@PathVariable Long id) {
		categoriaService.remover(id);
	}
	
}
