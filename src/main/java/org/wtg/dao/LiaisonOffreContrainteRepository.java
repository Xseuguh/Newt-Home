package org.wtg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.wtg.entities.Annonce;
import org.wtg.entities.LiaisonOffreContrainte;

public interface LiaisonOffreContrainteRepository extends JpaRepository<LiaisonOffreContrainte, Long> {
	@Query("select lc from LiaisonOffreContrainte lc where lc.id_liaison = :x")
	public List <LiaisonOffreContrainte> findByIdLiaison(@Param("x")Long mc);
	@Query("select lc from LiaisonOffreContrainte lc where lc.id_offre = :offre")
	public  List<LiaisonOffreContrainte> findByIdOffre(@Param("offre")Long mc);
	@Query(value = "SELECT o.id_offre, c.nom_contrainte FROM offres o INNER JOIN liaison_offre_contrainte lc ON o.id_offre = lc.id_offre INNER JOIN contraintes c ON lc.id_contrainte = c.id_contrainte WHERE o.id_offre = :idOffre", nativeQuery = true)
	public List<Annonce> findContraintesByIdOffre(@Param("idOffre") Long mc);


}
