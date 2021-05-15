package org.wtg.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Settings {

	@JsonProperty(value = "email", required = true)
	private String mail;

	@JsonProperty(value = "oldPassword", required = false)
	private String oldPassword;
	@JsonProperty(value = "newPassword", required = false)
	private String newPassword;


	public String getEmail() {
		return mail;
	}

	public void setEmail(String email) {
		this.mail = email;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String password) {
		this.oldPassword = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String password) {
		this.newPassword = password;
	}

}
