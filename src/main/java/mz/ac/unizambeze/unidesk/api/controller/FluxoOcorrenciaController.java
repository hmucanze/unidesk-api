package mz.ac.unizambeze.unidesk.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import mz.ac.unizambeze.unidesk.domain.service.FluxoOcorrenciaService;

@RestController
@RequestMapping("/ocorrencias/{id}")
public class FluxoOcorrenciaController {
	
	@Autowired
	private FluxoOcorrenciaService fluxoOcorrenciaService;

	@PutMapping("/resolucao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void resolver(@PathVariable Long id) {
		fluxoOcorrenciaService.resolver(id);
	}
	
	@PutMapping("cancelamento")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void cancelar(@PathVariable Long id) {
		fluxoOcorrenciaService.cancelar(id);
	}
	
	@PutMapping("fechamento")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void fechar(@PathVariable Long id) {
		fluxoOcorrenciaService.fechar(id);
	}
}
