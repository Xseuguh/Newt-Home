package org.wtg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.wtg.entities.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
	@Query("select u from UserInfo u  where u.nom like :x")
	public List<UserInfo> findByName(@Param("x") String mc);

	@Query("SELECT u FROM UserInfo u WHERE u.mail = ?1")
	public UserInfo findByMail(String email);

	@Transactional
	@Modifying
	@Query("UPDATE UserInfo u set u.mail = :email where u.id_user = :userID")
	public void updateEmail(@Param("email") String email, @Param("userID") long id);

	@Transactional
	@Modifying
	@Query("UPDATE UserInfo u set u.password = :password where u.id_user = :userID")
	public void updatePassword(@Param("password") String password, @Param("userID") long id);

	@Query("SELECT u.password FROM UserInfo u  where u.id_user = :userID")
	public String getPassword(@Param("userID") long id);

}
