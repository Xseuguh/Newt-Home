package org.wtg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.wtg.entities.JoinOffresUsers;
import org.wtg.entities.Offres;
public interface OffresRepository extends JpaRepository<Offres, Long> {
	@Query("select o from Offres o  where o.titre like :x")
	public List<Offres> findByName(@Param("x")String mc);
	
	@Query(value = "SELECT o.id_offre, o.id_user , u.nom, u.prenom FROM offres o INNER JOIN user_info u ON o.id_user = u.id_user  WHERE o.id_offre = :idOffre", nativeQuery = true)
	public List<JoinOffresUsers> findUserByIdOffre(@Param("idOffre") Long mc);

	@Query(value = "SELECT * FROM offres WHERE offres.id_user = :userID", nativeQuery = true)
	public List<Offres> findByUserId(@Param("userID") Long userID);
	
	@Transactional
	@Modifying
	@Query("UPDATE Offres o set o.pourvu = TRUE where o.id_offre = :offreID")
	public void setPourvuToTrue(@Param("offreID") long offreID);

	@Transactional
	@Modifying
	@Query("DELETE FROM Offres o WHERE o.id_user = :userID AND o.id_offre = :offreID")
	public void removeOffer(@Param("offreID") long offreID, @Param("userID") long userID);

}