package org.wtg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.wtg.entities.Offres;
public interface OffresRepository extends JpaRepository<Offres, Long> {
	@Query("select o from Offres o  where o.titre like :x")
	public List<Offres> findByName(@Param("x")String mc);

}