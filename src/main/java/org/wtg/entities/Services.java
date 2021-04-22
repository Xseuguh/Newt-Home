package org.wtg.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity

public class Services implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private Long id_service;
	private String nom_service;
	public Long getId_service() {
		return id_service;
	}
	public void setId_service(Long id_service) {
		this.id_service = id_service;
	}
	public String getNom_service() {
		return nom_service;
	}
	public void setNom_service(String nom_service) {
		this.nom_service = nom_service;
	}
	public Services() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Services(String nom_service) {
		super();
		this.nom_service = nom_service;
	}
	

}
