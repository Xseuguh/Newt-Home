package org.wtg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.wtg.entities.JoinOffresOffresPostulees;
import org.wtg.entities.OffresPostulées;
import org.wtg.entities.UserInfoLight;

public interface OffresPostuléesRepository extends JpaRepository<OffresPostulées, Long> {
	@Query("select op from OffresPostulées op where op.id_offre = :x")
	public List<OffresPostulées> findByIdOffre(@Param("x") Long id);

	@Query(value = "SELECT o.*, op.id_postula, op.accepte from offres AS o INNER JOIN offres_postulées AS op ON o.id_offre = op.id_offre WHERE op.id_user = :userID", nativeQuery = true)
	public List<JoinOffresOffresPostulees> findByUserId(@Param("userID") Long userID);

	@Query(value = "SELECT u.id_user,u.nom,u.prenom FROM user_info u INNER JOIN offres_postulées op ON op.id_user = u.id_user WHERE op.id_offre = :idOffre", nativeQuery = true)
	public List<UserInfoLight> findUserWhoApply(@Param("idOffre") Long offreID);

	@Transactional
	@Modifying
	@Query("UPDATE OffresPostulées op SET op.accepte = true WHERE op.id_user = :userID AND op.id_offre = :offreID")
	public void setAccepteToTrue(@Param("offreID") long offreID, @Param("userID") long userID);

	@Transactional
	@Modifying
	@Query("DELETE FROM OffresPostulées op WHERE op.id_user = :userID AND op.id_offre = :offreID")
	public void removeUserFromOffer(@Param("offreID") long offreID, @Param("userID") long userID);

}
