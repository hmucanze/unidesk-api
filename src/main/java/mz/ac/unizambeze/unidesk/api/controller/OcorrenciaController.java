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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import mz.ac.unizambeze.unidesk.api.assembler.OcorrenciaInputModelDisassembler;
import mz.ac.unizambeze.unidesk.api.assembler.OcorrenciaOutputModelAssembler;
import mz.ac.unizambeze.unidesk.api.model.input.OcorrenciaInputModel;
import mz.ac.unizambeze.unidesk.api.model.output.OcorrenciaOutputModel;
import mz.ac.unizambeze.unidesk.domain.model.Ocorrencia;
import mz.ac.unizambeze.unidesk.domain.service.OcorrenciaService;

@RestController
@RequestMapping("/ocorrencias")
public class OcorrenciaController {
	
	@Autowired
	private OcorrenciaService ocorrenciaService;
	
	@Autowired
	private OcorrenciaOutputModelAssembler ocorrenciaOutputModelAssembler;
	
	@Autowired
	private OcorrenciaInputModelDisassembler ocorrenciaInputModelDisassembler;
	
	@GetMapping
	public List<OcorrenciaOutputModel> listar() {
		List<Ocorrencia> ocorrencias = ocorrenciaService.listar();
		
		return ocorrenciaOutputModelAssembler.toCollectionModel(ocorrencias);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OcorrenciaOutputModel> buscarPorId(@PathVariable Long id) {
		Ocorrencia ocorrencia = ocorrenciaService.buscarPorId(id);
		
		return ResponseEntity
				.ok(ocorrenciaOutputModelAssembler.toModel(ocorrencia));
	}
	
	@PostMapping
	public ResponseEntity<OcorrenciaOutputModel> salvar(@RequestBody @Valid OcorrenciaInputModel ocorrenciaInputModel) {
		Ocorrencia ocorrencia = ocorrenciaInputModelDisassembler.toDomainObject(ocorrenciaInputModel);
		
		OcorrenciaOutputModel ocorrenciaOutputModel = ocorrenciaOutputModelAssembler
				.toModel(ocorrenciaService.salvar(ocorrencia));
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(ocorrenciaOutputModel);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<OcorrenciaOutputModel> actualizar(@RequestBody @Valid OcorrenciaInputModel ocorrenciaInputModel,
			@PathVariable Long id) {
		Ocorrencia ocorrencia = ocorrenciaInputModelDisassembler.toDomainObject(ocorrenciaInputModel);
		
		OcorrenciaOutputModel ocorrenciaOutputModel = ocorrenciaOutputModelAssembler
				.toModel(ocorrenciaService.actualizar(ocorrencia, id));
		
		return ResponseEntity
				.ok(ocorrenciaOutputModel);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		ocorrenciaService.remover(id);
	}

}
