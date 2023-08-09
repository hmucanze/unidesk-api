package mz.ac.unizambeze.unidesk.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mz.ac.unizambeze.unidesk.api.model.input.CategoriaInputModel;
import mz.ac.unizambeze.unidesk.domain.model.Categoria;

@Component
public class CategoriaInputModelDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Categoria toDomainObject(CategoriaInputModel categoriaInputModel) {
		return modelMapper.map(categoriaInputModel, Categoria.class);
	}

}
