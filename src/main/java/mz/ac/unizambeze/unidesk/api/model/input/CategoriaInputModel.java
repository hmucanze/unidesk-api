package mz.ac.unizambeze.unidesk.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaInputModel {

	@NotBlank
	private String categoriaNome;
	
}
