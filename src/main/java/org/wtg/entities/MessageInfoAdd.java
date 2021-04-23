package org.wtg.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageInfoAdd {

	@JsonProperty(value = "conversationID", required = false)
	private Integer conversationID;

	@JsonProperty(value = "receiverID", required = false)
	private Integer receiverID;

	@JsonProperty(value = "message", required = true)
	private String message;

	public Integer getConversationID() {
		return conversationID;
	}

	public void setConversationID(Integer conversationID) {
		this.conversationID = conversationID;
	}

	public Integer getReceiverID() {
		return receiverID;
	}

	public void setReceiverID(Integer receiverID) {
		this.receiverID = receiverID;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}



}
