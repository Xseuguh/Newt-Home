package org.wtg.entities;

import java.io.Serializable;
import java.sql.Date;

public class MessageInfoGet implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message, origine;
	private Date hotrodatage;

	public MessageInfoGet(String message, String origine, Date horodatage) {
		this.setMessage(message);
		this.setOrigine(origine);
		this.setHotrodatage(horodatage);
	}

	public Date getHotrodatage() {
		return hotrodatage;
	}

	public void setHotrodatage(Date hotrodatage) {
		this.hotrodatage = hotrodatage;
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
