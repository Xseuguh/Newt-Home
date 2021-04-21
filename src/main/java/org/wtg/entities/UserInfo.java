package org.wtg.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity

public class UserInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private Long id_user;
	private String nom;
	private String prenom;
	private Date birthday;
	private String mail;
	private String password;
	private Boolean admin;
	public Long getId_user() {
		return id_user;
	}
	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getAdmin() {
		return admin;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	public UserInfo(String nom, String prenom, Date birthday, String mail, String password, Boolean admin) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.birthday = birthday;
		this.mail = mail;
		this.password = password;
		this.admin = admin;
	}
	public UserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
