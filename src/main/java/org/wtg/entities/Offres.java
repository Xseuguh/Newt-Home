package org.wtg.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Offres implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private Long id_offre;
	private Long id_user;
	private String titre;
	private String description;
	private Long id_contrainte;
	private Long id_service;
	private Date date_debut;
	private Date date_limite;
	private Boolean pourvu;
	public Long getId_offre() {
		return id_offre;
	}
	public void setId_offre(Long id_offre) {
		this.id_offre = id_offre;
	}
	public Long getId_user() {
		return id_user;
	}
	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getId_contrainte() {
		return id_contrainte;
	}
	public void setId_contrainte(Long id_contrainte) {
		this.id_contrainte = id_contrainte;
	}
	public Long getId_service() {
		return id_service;
	}
	public void setId_service(Long id_service) {
		this.id_service = id_service;
	}
	public Date getDate_debut() {
		return date_debut;
	}
	public void setDate_debut(Date date_debut) {
		this.date_debut = date_debut;
	}
	public Date getDate_limite() {
		return date_limite;
	}
	public void setDate_limite(Date date_limite) {
		this.date_limite = date_limite;
	}
	public Boolean getPourvu() {
		return pourvu;
	}
	public void setPourvu(Boolean pourvu) {
		this.pourvu = pourvu;
	}
	public Offres(Long id_user, String titre, String description, Long id_contrainte, Long id_service, Date date_debut,
			Date date_limite, Boolean pourvu) {
		super();
		this.id_user = id_user;
		this.titre = titre;
		this.description = description;
		this.id_contrainte = id_contrainte;
		this.id_service = id_service;
		this.date_debut = date_debut;
		this.date_limite = date_limite;
		this.pourvu = pourvu;
	}
	public Offres() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
