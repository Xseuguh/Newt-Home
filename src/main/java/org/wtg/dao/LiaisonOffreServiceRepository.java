package org.wtg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.wtg.entities.Annonce;
import org.wtg.entities.LiaisonOffreService;

public interface LiaisonOffreServiceRepository extends JpaRepository<LiaisonOffreService, Long> {
	@Query("select ls from LiaisonOffreService ls where ls.id_liaison = :x")
	public List <LiaisonOffreService> findByIdLiaison(@Param("x")Long mc);
	@Query("select ls from LiaisonOffreService ls where ls.id_offre = :x")
	public List <LiaisonOffreService> findByIdOffre(@Param("x")Long mc);
	@Query(value = "SELECT o.id_offre, s.nom_service FROM offres o INNER JOIN liaison_offre_service ls ON o.id_offre = ls.id_offre INNER JOIN services s ON ls.id_service = s.id_service WHERE o.id_offre = :idOffre", nativeQuery = true)
	public List<Annonce> findServicesByIdOffre(@Param("idOffre") Long mc);


}
