package mz.ac.unizambeze.unidesk.domain.exception;

public class UtilizadorInexistenteException extends EntidadeInexistenteException {
	
	private static final long serialVersionUID = 1L;
	
	private static final String MSG_UTILIZADOR_INEXISTENTE = "Não existe registo de utilizador com ID %d";

	public UtilizadorInexistenteException(String mensagem) {
		super(mensagem);
	}
	
	public UtilizadorInexistenteException(Long utilizadorId) {
		this(String.format(MSG_UTILIZADOR_INEXISTENTE, utilizadorId));
	}
}
