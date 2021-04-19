package org.wtg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.wtg.entities.OffresPostulées;

public interface OffresPostuléesRepository extends JpaRepository<OffresPostulées, Long> {
	@Query("select op from OffresPostulées op  where op.id_offre = :x")
	public List<OffresPostulées> findByIdOffre(@Param("x")Long id);
}
