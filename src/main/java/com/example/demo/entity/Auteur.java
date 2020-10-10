package com.example.demo.entity;

import java.util.ArrayList;


import java.util.Date;
import java.util.List;



import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Auteur")
public class Auteur {
	
	@Id
	private String id;
	
	private String Nom;
	private String Prenom;
	private String Adresse;
	private String Grade;
	private String Organisme;
	
	
	@DBRef
	private List<Publication> pubs=new ArrayList<Publication>();
	
	
	private int nbr;
	
	
	
	public int getNbr() {
		return nbr;
	}
	public void setNbr(int nbr) {
		this.nbr = nbr;
	}
	public List<Publication> getPubs() {
		return pubs;
	}
	public void setPubs(List<Publication> pubs) {
		this.pubs = pubs;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	public String getPrenom() {
		return Prenom;
	}
	public void setPrenom(String prenom) {
		Prenom = prenom;
	}
	
	public String getAdresse() {
		return Adresse;
	}
	public void setAdresse(String adresse) {
		Adresse = adresse;
	}
	public String getGrade() {
		return Grade;
	}
	public void setGrade(String grade) {
		Grade = grade;
	}
	public String getOrganisme() {
		return Organisme;
	}
	public void setOrganisme(String organisme) {
		Organisme = organisme;
	}
	/*@Override
	public String toString() {
		return "Auteur [Id=" + id + ", Nom=" + Nom + ", Prenom=" + Prenom + ", Adresse=" + Adresse + ", Grade=" + Grade
				+ ", Organisme=" + Organisme + ", pubs=" + pubs + "]";
	}*/
	@Override
	public String toString() {
		return "Auteur [id=" + id + ", Nom=" + Nom + ", Prenom=" + Prenom + ", Adresse=" + Adresse + ", Grade=" + Grade
				+ ", Organisme=" + Organisme + ", pubs=" + pubs + ", nbr=" + nbr + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Adresse == null) ? 0 : Adresse.hashCode());
		result = prime * result + ((Grade == null) ? 0 : Grade.hashCode());
		result = prime * result + ((Nom == null) ? 0 : Nom.hashCode());
		result = prime * result + ((Organisme == null) ? 0 : Organisme.hashCode());
		result = prime * result + ((Prenom == null) ? 0 : Prenom.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + nbr;
		result = prime * result + ((pubs == null) ? 0 : pubs.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Auteur other = (Auteur) obj;
		if (Adresse == null) {
			if (other.Adresse != null)
				return false;
		} else if (!Adresse.equals(other.Adresse))
			return false;
		if (Grade == null) {
			if (other.Grade != null)
				return false;
		} else if (!Grade.equals(other.Grade))
			return false;
		if (Nom == null) {
			if (other.Nom != null)
				return false;
		} else if (!Nom.equals(other.Nom))
			return false;
		if (Organisme == null) {
			if (other.Organisme != null)
				return false;
		} else if (!Organisme.equals(other.Organisme))
			return false;
		if (Prenom == null) {
			if (other.Prenom != null)
				return false;
		} else if (!Prenom.equals(other.Prenom))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nbr != other.nbr)
			return false;
		if (pubs == null) {
			if (other.pubs != null)
				return false;
		} else if (!pubs.equals(other.pubs))
			return false;
		return true;
	}
	
	
	
	
	
	

}
