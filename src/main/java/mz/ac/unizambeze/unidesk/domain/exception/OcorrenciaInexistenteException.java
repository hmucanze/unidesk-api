package mz.ac.unizambeze.unidesk.domain.exception;

public class OcorrenciaInexistenteException extends EntidadeInexistenteException {

	private static final long serialVersionUID = 1L;
	
	private static final String MSG_OCORRENCIA_INEXISTENTE = "Não existe registo de ocorrência com o ID %d";
	
	public OcorrenciaInexistenteException(String mensagem) {
		super(mensagem);
	}
	
	public OcorrenciaInexistenteException(Long ocorrenciaId) {
		this(String.format(MSG_OCORRENCIA_INEXISTENTE, ocorrenciaId));
	}

}
