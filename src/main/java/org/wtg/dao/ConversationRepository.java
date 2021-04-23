package org.wtg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.wtg.entities.Conversation;
import org.wtg.entities.ConversationInfo;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
	@Query(value = "SELECT id_conversation, id_user ,nom,prenom FROM conversation AS c INNER JOIN user_info AS u ON c.id_sender = u.id_user OR c.id_receiver = u.id_user WHERE (c.id_sender = :id_user OR c.id_receiver = :id_user) AND NOT u.id_user = :id_user ", nativeQuery = true)
	public List<ConversationInfo> findConversationsByUserID(@Param("id_user") Integer userID);

	@Transactional
	@Modifying
	@Query(value = "INSERT INTO Conversation (id_sender, id_receiver) VALUES (:id_sender,:id_receiver)", nativeQuery = true)
	public void addConversation(@Param("id_sender") Integer senderID, @Param("id_receiver") Integer receiverID);

	@Query(value = "SELECT LAST_INSERT_ID()", nativeQuery = true)
	public int getIDLastInsert();

	@Query(value = "SELECT id_conversation FROM conversation AS c WHERE (c.id_sender = :id_user AND c.id_receiver = :id_receiver) OR (c.id_sender = :id_receiver AND c.id_receiver = :id_user)", nativeQuery = true)
	public Integer getConversationByIdCouple(@Param("id_user") Integer userID,
			@Param("id_receiver") Integer receiverID);
}
