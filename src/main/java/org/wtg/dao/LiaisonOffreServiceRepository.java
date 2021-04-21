package org.wtg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.wtg.entities.LiaisonOffreService;

public interface LiaisonOffreServiceRepository extends JpaRepository<LiaisonOffreService, Long> {
	@Query("select ls from LiaisonOffreService ls where ls.id_liaison = :x")
	public List <LiaisonOffreService> findByIdLiaison(@Param("x")Long mc);

}
