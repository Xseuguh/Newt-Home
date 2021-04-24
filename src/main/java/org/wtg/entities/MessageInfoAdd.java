package org.wtg.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageInfoAdd {

	@JsonProperty(value = "conversationID", required = true)
	private Long conversationID;


	@JsonProperty(value = "message", required = true)
	private String message;

	public Long getConversationID() {
		return conversationID;
	}

	public void setConversationID(Long conversationID) {
		this.conversationID = conversationID;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
