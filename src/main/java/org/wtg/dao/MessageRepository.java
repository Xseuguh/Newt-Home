package org.wtg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.wtg.entities.Message;

public interface MessageRepository  extends JpaRepository<Message, Long> {
	@Query("select m from Message m  where m.id_conversation = :x")
	public List<Message> findByIdConversation(@Param("x")Long id);

}