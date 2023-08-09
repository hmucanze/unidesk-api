package mz.ac.unizambeze.unidesk.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import mz.ac.unizambeze.unidesk.domain.filter.OcorrenciaDiariaFilter;
import mz.ac.unizambeze.unidesk.domain.model.dto.OcorrenciaDiaria;

@Repository
public interface OcorrenciaRepositoryQueries {
	
	public List<OcorrenciaDiaria> listarOcorrenciasDiarias(OcorrenciaDiariaFilter filter, String timeOffset);

}
