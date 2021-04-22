package org.wtg.entities;
import java.io.Serializable;
//import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity


public class Contraintes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private Long id_contrainte;
	private String nom_contrainte;
	public String getNom_contrainte() {
		return nom_contrainte;
	}
	public void setNom_contrainte(String nom_contrainte) {
		this.nom_contrainte = nom_contrainte;
	}
	
	
	public Contraintes(String nom_contrainte) {
		super();
		this.nom_contrainte = nom_contrainte;
	}
	public Contraintes() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId_contrainte() {
		return id_contrainte;
	}
	public void setId_contrainte(Long id_contrainte) {
		this.id_contrainte = id_contrainte;
	}
	
	

}
