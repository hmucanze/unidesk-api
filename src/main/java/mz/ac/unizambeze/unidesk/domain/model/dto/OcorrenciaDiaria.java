package mz.ac.unizambeze.unidesk.domain.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class OcorrenciaDiaria {
	
	private Date data;
	private Long totalOcorrencias;

}
