package mz.ac.unizambeze.unidesk.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import mz.ac.unizambeze.unidesk.domain.model.Prioridade;

@Getter
@Setter
public class OcorrenciaInputModel {
	
	@NotBlank
	private String ocorrenciaDescricao;
	
	@NotNull
	private Prioridade ocorrenciaPrioridade;
	
	@Valid
	@NotNull
	private CategoriaIdInput ocorrenciaCategoria;
	
	@Valid
	@NotNull
	private UtilizadorIdInput ocorrenciaUtilizador;

}
