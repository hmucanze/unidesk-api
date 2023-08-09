package mz.ac.unizambeze.unidesk.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UtilizadorInputModel {
	
	@NotBlank
	private String utilizadorNome;

}
