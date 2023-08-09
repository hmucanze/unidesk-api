package mz.ac.unizambeze.unidesk.api.exceptionHandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	
	RECURSO_NAO_EXISTENTE("Recurso não encontrado", "recurso-nao-encontrado"),
	ENTIDADE_EM_USO("Entidade em Uso", "entidade-em-uso"),
	DADOS_INVALIDOS("Dados Inválidos", "dados-invalidos"),
	NEGOCIO_EXCEPTION("Violação de regra de negócio", "erro-de-negocio"),
	MENSAGEM_INCOMPREENSIVEL("Mensagem incompreensível", "mensagem-incompreensivel"),
	PARAMETRO_INVALIDO("Parâmetro inválido", "parametro-invalido");
	
	String title;
	String uri;
	
	private ProblemType(String title, String path) {
		this.title = title;
		this.uri = "https://unidesk.unizambeze.ac.mz/" + path;
	}

}
