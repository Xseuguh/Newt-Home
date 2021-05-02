package org.wtg.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Message implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id_msg;
	private Long id_conversation;
	private String msg;
	private Date date_message;
	private Long id_origine;

	public Long getId_msg() {
		return id_msg;
	}

	public void setId_msg(Long id_msg) {
		this.id_msg = id_msg;
	}

	public Long getId_conversation() {
		return id_conversation;
	}

	public void setId_conversation(Long id_conversation) {
		this.id_conversation = id_conversation;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Date getDate_message() {
		return date_message;
	}

	public void setDate_message(Date date_message) {
		this.date_message = date_message;
	}

	public Long getId_origine() {
		return id_origine;
	}

	public void setId_origine(Long id_origine) {
		this.id_origine = id_origine;
	}

	public Message(Long id_conversation, String msg, Date date_message, Long id_origine) {
		super();
		this.id_conversation = id_conversation;
		this.msg = msg;
		this.date_message = date_message;
		this.id_origine = id_origine;
	}

	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

}
