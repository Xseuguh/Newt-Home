package org.wtg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.wtg.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
//	@Query("SELECT r FROM Role WHERE r.name =?1")
//	public Role findByName(String name);
}
