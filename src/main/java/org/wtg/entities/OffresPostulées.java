package org.wtg.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class OffresPostulées implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private Long id_postula;
	private Long id_offre;
	private Long id_user;
	private Boolean accepte;
	public Long getId_postula() {
		return id_postula;
	}
	public void setId_postula(Long id_postula) {
		this.id_postula = id_postula;
	}
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
	public Boolean getAccepte() {
		return accepte;
	}
	public void setAccepte(Boolean accepte) {
		this.accepte = accepte;
	}
	public OffresPostulées(Long id_offre, Long id_user, Boolean accepte) {
		super();
		this.id_offre = id_offre;
		this.id_user = id_user;
		this.accepte = accepte;
	}
	public OffresPostulées() {
		super();
		// TODO Auto-generated constructor stub
	}

}
