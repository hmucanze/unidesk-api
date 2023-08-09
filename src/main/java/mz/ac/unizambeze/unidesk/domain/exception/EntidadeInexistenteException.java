package mz.ac.unizambeze.unidesk.domain.exception;

public class EntidadeInexistenteException extends NegocioException {

	private static final long serialVersionUID = 1L;
	
	public EntidadeInexistenteException(String mensagem) {
		super(mensagem);
	}


}
