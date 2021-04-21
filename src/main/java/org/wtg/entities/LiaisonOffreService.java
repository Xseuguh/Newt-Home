package org.wtg.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class LiaisonOffreService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private Long id_liaison;
	private Long id_offre;
	private Long id_service;
	public Long getId_liaison() {
		return id_liaison;
	}
	public void setId_liaison(Long id_liaison) {
		this.id_liaison = id_liaison;
	}
	public Long getId_offre() {
		return id_offre;
	}
	public void setId_offre(Long id_offre) {
		this.id_offre = id_offre;
	}
	public Long getId_service() {
		return id_service;
	}
	public void setId_service(Long id_service) {
		this.id_service = id_service;
	}
	public LiaisonOffreService(Long id_offre, Long id_service) {
		super();
		this.id_offre = id_offre;
		this.id_service = id_service;
	}
	public LiaisonOffreService() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
