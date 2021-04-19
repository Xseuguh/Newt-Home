package org.wtg.dao;

import java.util.List;

import org.wtg.entities.Contraintes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContraintesRepository extends JpaRepository<Contraintes, Long> {
	@Query("select c from Contraintes c  where c.nom_contrainte like :x")
	public List<Contraintes> findByName(@Param("x")String mc);

}
