package org.wtg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.wtg.entities.Conversation;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
	@Query("select cv from Conversation cv  where cv.id_conversation = :x")
	public List<Conversation> findByIdConversation(@Param("x")Long id);
}
