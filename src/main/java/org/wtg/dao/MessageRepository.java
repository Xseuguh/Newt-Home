package org.wtg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.wtg.entities.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
	@Query(value = "SELECT * FROM Message AS m WHERE m.id_conversation = :id_conversation", nativeQuery = true)
	public List<Message> findMessagesByConversationID(@Param("id_conversation") Integer conversationID);

	@Transactional
	@Modifying
	@Query(value = "INSERT INTO Message (id_conversation, msg, id_origine) VALUES (:id_conversation,:msg,:id_origine)", nativeQuery = true)
	public void addMessage(@Param("id_conversation") Integer conversationID, @Param("msg") String message,
			@Param("id_origine") Integer origineID);

}