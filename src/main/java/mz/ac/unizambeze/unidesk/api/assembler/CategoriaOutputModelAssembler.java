package mz.ac.unizambeze.unidesk.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mz.ac.unizambeze.unidesk.api.model.output.CategoriaOutputModel;
import mz.ac.unizambeze.unidesk.domain.model.Categoria;

@Component
public class CategoriaOutputModelAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public CategoriaOutputModel toModel(Categoria categoria) {
		return modelMapper.map(categoria, CategoriaOutputModel.class);
	}
	
	public List<CategoriaOutputModel> toCollectionModel(List<Categoria> categorias) {
		return categorias.stream()
				.map(categoria -> toModel(categoria))
				.collect(Collectors.toList());
	}

}
