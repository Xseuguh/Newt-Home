package org.wtg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.wtg.entities.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo, Long> {
	
	@Query("SELECT u FROM UserInfo u WHERE u.mail = ?1")
    public UserInfo findByMail(String email);

}
