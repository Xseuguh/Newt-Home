package org.wtg.entities;

import java.io.Serializable;
import java.sql.Date;

public class MessageInfoGet implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message, origine;
	private Date horodatage;

	public MessageInfoGet(String message, String origine, Date horodatage) {
		this.setMessage(message);
		this.setOrigine(origine);
		this.setHorodatage(horodatage);
	}

	public Date getHorodatage() {
		return horodatage;
	}

	public void setHorodatage(Date horodatage) {
		this.horodatage = horodatage;
	}

	public String getOrigine() {
		return origine;
	}

	public void setOrigine(String origine) {
		this.origine = origine;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
