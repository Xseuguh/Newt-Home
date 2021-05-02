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
	private int id_conversation;
	private int id_sender;
	private int id_receiver;

	public int getId_conversation() {
		return id_conversation;
	}

	public void setId_conversation(int id_conversation) {
		this.id_conversation = id_conversation;
	}

	public int getId_sender() {
		return id_sender;
	}
	public Conversation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Conversation(int id_sender, int id_receiver) {
		super();
		this.id_sender = id_sender;
		this.id_receiver = id_receiver;
	}

	public void setId_sender(int id_sender) {
		this.id_sender = id_sender;
	}

	public int getId_receiver() {
		return id_receiver;
	}

	public void setId_receiver(int id_receiver) {
		this.id_receiver = id_receiver;
	}
}
