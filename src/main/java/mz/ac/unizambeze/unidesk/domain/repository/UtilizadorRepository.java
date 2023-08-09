package mz.ac.unizambeze.unidesk.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mz.ac.unizambeze.unidesk.domain.model.Utilizador;

@Repository
public interface UtilizadorRepository extends JpaRepository<Utilizador, Long> {

}
