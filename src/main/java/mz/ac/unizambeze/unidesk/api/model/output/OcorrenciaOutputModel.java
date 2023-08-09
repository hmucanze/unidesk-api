package mz.ac.unizambeze.unidesk.api.model.output;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;
import mz.ac.unizambeze.unidesk.domain.model.Prioridade;
import mz.ac.unizambeze.unidesk.domain.model.StatusOcorrencia;

@Getter
@Setter
public class OcorrenciaOutputModel {

	private Long ocorrenciaId;
	private String ocorrenciaCodigo;
	private String ocorrenciaDescricao;
	private Prioridade ocorrenciaPrioridade;
	private StatusOcorrencia ocorrenciaStatus;
	private OffsetDateTime dataCriacao;
	private OffsetDateTime dataResolucao;
	private OffsetDateTime dataCancelamento;
	private OffsetDateTime dataFechamento;
	private CategoriaOutputModel ocorrenciaCategoria;
	private UtilizadorOutputModel ocorrenciaUtilizador;
}
