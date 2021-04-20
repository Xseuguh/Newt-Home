package org.wtg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.wtg.entities.LiaisonOffreContrainte;

public interface LiaisonOffreContrainteRepository extends JpaRepository<LiaisonOffreContrainte, Long> {
	@Query("select lc from LiaisonOffreContrainte lc where lc.id_liaison = :x")
	public List <LiaisonOffreContrainte> findByIdLiaison(@Param("x")Long mc);

}
