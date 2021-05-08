package org.wtg.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.wtg.entities.JoinOffresUsers;
import org.wtg.entities.Offres;

public interface OffresRepository extends JpaRepository<Offres, Long> {
	@Query("select o from Offres o  where o.titre like :x")
	public List<Offres> findByName(@Param("x") String mc);

	@Query(value = "SELECT o.id_offre, o.id_user , u.nom, u.prenom FROM offres o INNER JOIN user_info u ON o.id_user = u.id_user  WHERE o.id_offre = :idOffre", nativeQuery = true)
	public List<JoinOffresUsers> findUserByIdOffre(@Param("idOffre") Long mc);

	@Query("select o from Offres o  where o.titre like :x or o.description like :x")
	public List<Offres> findOffreByNameOrDescription(@Param("x") String mc);
	
	@Query("select o from Offres o  where o.ville like :x or o.pays like :x")
	public List<Offres> findOffreByLocation(@Param("x") String lieu);

	@Query(nativeQuery = true, value = "SELECT DISTINCT o.* FROM offres o INNER JOIN liaison_offre_service ls ON o.id_offre = ls.id_offre INNER JOIN liaison_offre_contrainte lc ON o.id_offre = lc.id_offre WHERE ((ls.id_service IN :lserv  AND lc.id_contrainte IN :lcont)  AND o.titre LIKE :mc  AND o.description LIKE :mc AND o.ville LIKE :lieu AND o.pays LIKE :lieu)")
	public List<Offres> findAvanceeTousChamps(@Param("mc") String mc, @Param("lcont") long[] listeContraintes,
			@Param("lserv") long[] listeServices, @Param("lieu") String lieu);
	
	@Query(nativeQuery = true, value = "SELECT DISTINCT o.* FROM offres o WHERE ((o.titre LIKE :mc  OR o.description LIKE :mc) AND (o.ville LIKE :lieu OR o.pays LIKE :lieu))")
	public List<Offres> findByMCLocation(@Param("mc") String mc, @Param("lieu") String lieu);
	
	@Query(nativeQuery = true, value = "SELECT DISTINCT o.* FROM offres o INNER JOIN liaison_offre_contrainte lc ON o.id_offre = lc.id_offre WHERE lc.id_contrainte IN :lcont AND (o.titre LIKE :mc  OR o.description LIKE :mc) ")
	public List<Offres> findByContrainteMC(@Param("mc") String mc, @Param("lcont") long[] listeContraintes);
	
	@Query(nativeQuery = true, value = "SELECT DISTINCT o.* FROM offres o INNER JOIN liaison_offre_contrainte lc ON o.id_offre = lc.id_offre WHERE lc.id_contrainte IN :lcont AND (o.ville LIKE :lieu  OR o.pays LIKE :lieu) ")
	public List<Offres> findByContrainteLocation(@Param("lcont") long[] listeContraintes, @Param("lieu") String lieu);
	
	@Query(nativeQuery = true, value = "SELECT DISTINCT o.* FROM offres o INNER JOIN liaison_offre_service ls ON o.id_offre = ls.id_offre INNER JOIN liaison_offre_contrainte lc ON o.id_offre = lc.id_offre WHERE (ls.id_service IN :lserv  AND lc.id_contrainte IN :lcont)")
	public List<Offres> findByContrainteService(@Param("lcont") long[] listeContraintes,
			@Param("lserv") long[] listeServices);
	
	@Query(nativeQuery = true, value = "SELECT DISTINCT o.* FROM offres o INNER JOIN liaison_offre_contrainte lc ON o.id_offre = lc.id_offre WHERE (lc.id_contrainte IN :lcont AND (o.titre LIKE :mc  OR o.description LIKE :mc) AND (o.ville LIKE :lieu OR o.pays LIKE :lieu))")
	public List<Offres> findByContrainteMcLocation(@Param("mc") String mc, @Param("lcont") long[] listeContraintes, @Param("lieu") String lieu);
	
	@Query(nativeQuery = true, value = "SELECT DISTINCT o.* FROM offres o INNER JOIN liaison_offre_contrainte lc ON o.id_offre = lc.id_offre WHERE lc.id_contrainte IN :lcont")
	public List<Offres> findByContrainte(@Param("lcont") long[] listeContraintes);
	
	@Query(nativeQuery = true, value = "SELECT DISTINCT o.* FROM offres o INNER JOIN liaison_offre_service ls ON o.id_offre = ls.id_offre  WHERE ls.id_service IN :lserv")
	public List<Offres> findByService(@Param("lserv") long[] listeServices);
	
	@Query(nativeQuery = true, value = "SELECT DISTINCT o.* FROM offres o INNER JOIN liaison_offre_service ls ON o.id_offre = ls.id_offre WHERE ls.id_service IN :lserv AND (o.ville LIKE :lieu  OR o.pays LIKE :lieu) ")
	public List<Offres> findByServiceLocation(@Param("lieu") String lieu, @Param("lserv") long[] listeServices);
	
	@Query(nativeQuery = true, value = "SELECT DISTINCT o.* FROM offres o INNER JOIN liaison_offre_service ls ON o.id_offre = ls.id_offre WHERE ls.id_service IN :lserv AND (o.titre LIKE :mc  OR o.description LIKE :mc) ")
	public List<Offres> findByServiceMC(@Param("mc") String mc, @Param("lserv") long[] listeServices);
	
	@Query(nativeQuery = true, value = "SELECT DISTINCT o.* FROM offres o INNER JOIN liaison_offre_service ls ON o.id_offre = ls.id_offre WHERE (ls.id_service IN :lserv AND (o.titre LIKE :mc  OR o.description LIKE :mc ) AND (o.ville LIKE :lieu OR o.pays LIKE :lieu))")
	public List<Offres> findByServiceMcLocation(@Param("mc") String mc, @Param("lserv") long[] listeServices, @Param("lieu") String lieu);
	
	@Query(nativeQuery = true, value = "SELECT DISTINCT o.* FROM offres o INNER JOIN liaison_offre_service ls ON o.id_offre = ls.id_offre INNER JOIN liaison_offre_contrainte lc ON o.id_offre = lc.id_offre WHERE (ls.id_service IN :lserv  AND lc.id_contrainte IN :lcont AND (o.ville LIKE :lieu OR o.pays LIKE :lieu))")
	public List<Offres> findByContrainteServiceLocation(@Param("lcont") long[] listeContraintes,
			@Param("lserv") long[] listeServices, @Param("lieu") String lieu);
	
	@Query(nativeQuery = true, value = "SELECT DISTINCT o.* FROM offres o INNER JOIN liaison_offre_service ls ON o.id_offre = ls.id_offre INNER JOIN liaison_offre_contrainte lc ON o.id_offre = lc.id_offre WHERE (ls.id_service IN :lserv  AND lc.id_contrainte IN :lcont AND (o.titre LIKE :mc OR o.description LIKE :mc))")
	public List<Offres> findByContrainteServiceMC(@Param("lcont") long[] listeContraintes,
			@Param("lserv") long[] listeServices, @Param("mc") String mc);
	
}