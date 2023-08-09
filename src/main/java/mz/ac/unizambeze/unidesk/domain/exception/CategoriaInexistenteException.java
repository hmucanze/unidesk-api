package mz.ac.unizambeze.unidesk.domain.exception;

public class CategoriaInexistenteException extends EntidadeInexistenteException {
	
	private static final String MSG_CATEGORIA_INEXISTENTE = "Não existe registo de Categoria com ID %d";

	private static final long serialVersionUID = 1L;

	public CategoriaInexistenteException(String mensagem) {
		super(mensagem);
	}
	
	public CategoriaInexistenteException(Long categoriaId) {
		this(String.format(MSG_CATEGORIA_INEXISTENTE, categoriaId));
	}


}
