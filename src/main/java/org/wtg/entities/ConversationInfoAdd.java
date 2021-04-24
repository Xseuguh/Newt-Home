package org.wtg.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConversationInfoAdd {

	@JsonProperty(value = "receiverID", required = true)
	private Long receiverID;

	public Long getReceiverID() {
		return receiverID;
	}

	public void setReceiverID(Long receiverID) {
		this.receiverID = receiverID;
	}

}