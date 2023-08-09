package mz.ac.unizambeze.unidesk.domain.model;

import java.util.Arrays;
import java.util.List;

public enum StatusOcorrencia {
	PENDENTE("Pendente"),
	EM_RESOLUCAO("Em Resolução", PENDENTE),
	RESOLVIDO("Resolvido", EM_RESOLUCAO),
	CANCELADO("Cancelado", PENDENTE, EM_RESOLUCAO);
	
	private String descricao;
	
	private List<StatusOcorrencia> statusAnteriores;
	
	private StatusOcorrencia(String descricao, StatusOcorrencia... statusAnteriores) {
		this.descricao = descricao;
		this.statusAnteriores = Arrays.asList(statusAnteriores);
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public boolean naoPodeAlterarPara(StatusOcorrencia novoStatus) {
		return !novoStatus.statusAnteriores.contains(this);
	}

}
