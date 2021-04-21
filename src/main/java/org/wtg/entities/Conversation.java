package org.wtg.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Conversation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private Long id_conversation;
	private Long id_sender;
	private Long id_receiver;
	public Long getId_conversation() {
		return id_conversation;
	}
	public void setId_conversation(Long id_conversation) {
		this.id_conversation = id_conversation;
	}
	public Long getId_sender() {
		return id_sender;
	}
	public Conversation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Conversation(Long id_sender, Long id_receiver) {
		super();
		this.id_sender = id_sender;
		this.id_receiver = id_receiver;
	}
	public void setId_sender(Long id_sender) {
		this.id_sender = id_sender;
	}
	public Long getId_receiver() {
		return id_receiver;
	}
	public void setId_receiver(Long id_receiver) {
		this.id_receiver = id_receiver;
	}
}
