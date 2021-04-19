package org.wtg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.wtg.entities.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
	@Query("select u from UserInfo u  where u.nom like :x")
	public List<UserInfo> findByName(@Param("x")String mc);

}
