package org.wtg.entities;

import java.sql.Date;

public interface JoinOffresOffresPostulees {

	public Long getId_offre();

	public Long getId_user();

	public String getTitre();

	public String getDescription();

	public String getPays();

	public String getVille();

	public int getCode_postal();

	public String getAdresse();

	public Date getDate_debut();

	public Date getDate_limite();

	public boolean getPourvu();

	public Long getId_postula();

	public int getAccepte();

}
