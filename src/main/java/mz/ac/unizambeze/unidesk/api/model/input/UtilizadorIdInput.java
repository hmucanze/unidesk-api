package mz.ac.unizambeze.unidesk.api.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UtilizadorIdInput {

	@NotNull
	private Long utilizadorId;
	
}
