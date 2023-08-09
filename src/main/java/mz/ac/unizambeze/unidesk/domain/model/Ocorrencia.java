package mz.ac.unizambeze.unidesk.domain.model;

import java.time.OffsetDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;
import mz.ac.unizambeze.unidesk.domain.exception.NegocioException;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "ocorrencia")
public class Ocorrencia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String codigo;
	
	private String descricao;
	
	@Enumerated(EnumType.STRING)
	private Prioridade prioridade;
	
	@Enumerated(EnumType.STRING)
	private StatusOcorrencia status = StatusOcorrencia.PENDENTE;
	
	@CreationTimestamp
	@Column(name = "data_criacao")
	private OffsetDateTime dataCriacao;
	
	@Column(name = "data_resolucao")
	private OffsetDateTime dataResolucao;
	
	@Column(name = "data_cancelamento")
	private OffsetDateTime dataCancelamento;
	
	@Column(name = "data_fechamento")
	private OffsetDateTime dataFechamento;
	
	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name = "utilizador_id")
	private Utilizador utilizador;
	
	@PrePersist
	private void adicionarCodigo() {
		this.setCodigo(UUID.randomUUID().toString());
	}
	
	public void resolver() {
		this.setStatus(StatusOcorrencia.EM_RESOLUCAO);
		this.setDataResolucao(OffsetDateTime.now());
	}
	
	public void cancelar() {
		this.setStatus(StatusOcorrencia.CANCELADO);
		this.setDataCancelamento(OffsetDateTime.now());
	}
	
	public void fechar() {
		this.setStatus(StatusOcorrencia.RESOLVIDO);
		this.setDataFechamento(OffsetDateTime.now());
	}
	
	private void setStatus(StatusOcorrencia novoStatus) {
		if(this.getStatus().naoPodeAlterarPara(novoStatus)) {
			if(this.getStatus().equals(novoStatus)) {
				throw new NegocioException(
						String.format("O Status da ocorrência %s não pode alterar para o Status %s pois a mesma já está %s",
						this.getCodigo(),
						novoStatus.getDescricao(),
						this.getStatus().getDescricao()));
			}
			
			throw new NegocioException(String.format("A ocorrência %s não pode passar de Status %s para Status %s", 
					this.getCodigo(),
					this.getStatus(),
					novoStatus.getDescricao()));
		}
		this.status = novoStatus;
	}
	
}
