package org.wtg.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Message implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private Long id_msg;
	private Long id_sender;
	private Long id_recipient;
	private String msg;
	private Date date_message;
	public Long getId_msg() {
		return id_msg;
	}
	public void setId_msg(Long id_msg) {
		this.id_msg = id_msg;
	}
	public Long getId_sender() {
		return id_sender;
	}
	public void setId_sender(Long id_sender) {
		this.id_sender = id_sender;
	}
	public Long getId_recipient() {
		return id_recipient;
	}
	public void setId_recipient(Long id_recipient) {
		this.id_recipient = id_recipient;
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
	public Message(Long id_sender, Long id_recipient, String msg, Date date_message) {
		super();
		this.id_sender = id_sender;
		this.id_recipient = id_recipient;
		this.msg = msg;
		this.date_message = date_message;
	}
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
