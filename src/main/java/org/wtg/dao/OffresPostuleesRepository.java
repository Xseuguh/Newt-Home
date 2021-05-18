package org.wtg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.wtg.entities.JoinOffresOffresPostulees;
import org.wtg.entities.OffresPostulees;
import org.wtg.entities.UserInfoLight;

public interface OffresPostuleesRepository extends JpaRepository<OffresPostulees, Long> {

	@Query("select op from OffresPostulees op where op.id_offre = :x")
	public List<OffresPostulees> findByIdOffre(@Param("x") Long id);

	@Query(value = "SELECT o.*, op.id_postula, op.accepte from offres AS o INNER JOIN offres_postulees AS op ON o.id_offre = op.id_offre WHERE op.id_user = :userID", nativeQuery = true)
	public List<JoinOffresOffresPostulees> findByUserId(@Param("userID") Long userID);

	@Query(value = "SELECT u.id_user,u.nom,u.prenom FROM user_info u INNER JOIN offres_postulees op ON op.id_user = u.id_user WHERE op.id_offre = :idOffre", nativeQuery = true)
	public List<UserInfoLight> findUserWhoApply(@Param("idOffre") Long offreID);

	@Transactional
	@Modifying
	@Query("UPDATE OffresPostulees op SET op.accepte = true WHERE op.id_user = :userID AND op.id_offre = :offreID")
	public void setAccepteToTrue(@Param("offreID") long offreID, @Param("userID") long userID);

	@Transactional
	@Modifying
	@Query("DELETE FROM OffresPostulees op WHERE op.id_user = :userID AND op.id_offre = :offreID")
	public void removeUserFromOffer(@Param("offreID") long offreID, @Param("userID") long userID);
	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO offres_postulees (id_offre, id_user) VALUES ( :idOffre , :idUserConnecte)", nativeQuery = true)
	public void applyToAnOffer(@Param("idOffre") Long idOffre, @Param("idUserConnecte") Long idUserConnecte);
}
