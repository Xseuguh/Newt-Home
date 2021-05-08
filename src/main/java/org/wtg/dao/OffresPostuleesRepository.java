package org.wtg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.wtg.entities.OffresPostulees;

public interface OffresPostuleesRepository extends JpaRepository<OffresPostulees, Long> {
	@Query("select op from OffresPostulees op  where op.id_offre = :x")
	public List<OffresPostulees> findByIdOffre(@Param("x")Long id);
}
