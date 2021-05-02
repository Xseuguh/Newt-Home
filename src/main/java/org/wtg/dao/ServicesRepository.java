package org.wtg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.wtg.entities.Services;

public interface ServicesRepository  extends JpaRepository<Services, Long> {
	@Query("select s from Services s  where s.nom_service like :x")
	public List<Services> findByName(@Param("x")String mc);
	@Query("select s from Services s  where s.id_service like :x")
	public List<Services> findByIdService(@Param("x")Long mc);

}
