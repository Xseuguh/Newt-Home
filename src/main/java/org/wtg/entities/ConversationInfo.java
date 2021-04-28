package org.wtg.entities;

import org.springframework.beans.factory.annotation.Value;

public interface ConversationInfo {

	public int getId_conversation();

	@Value("#{target.id_user}")
	public int getReceiverID();

	public String getNom();

	public String getPrenom();
}