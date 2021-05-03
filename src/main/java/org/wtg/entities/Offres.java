package org.wtg.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	private String pays;
	private String ville;
	private int code_postal;
	private String adresse;
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
	
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public int getCode_postal() {
		return code_postal;
	}
	public void setCode_postal(int code_postal) {
		this.code_postal = code_postal;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	public Offres(Long id_user, String titre, String description, String pays, String ville, int code_postal,
			String adresse, Date date_debut, Date date_limite, Boolean pourvu) {
		super();
		this.id_user = id_user;
		this.titre = titre;
		this.description = description;
		this.pays = pays;
		this.ville = ville;
		this.code_postal = code_postal;
		this.adresse = adresse;
		this.date_debut = date_debut;
		this.date_limite = date_limite;
		this.pourvu = pourvu;
	}
	public Offres() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
