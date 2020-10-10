package com.example.demo.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Publication")
public class Publication {
	
	@Id
	private String id;
	
	private String Titre;
	private Date DatePub;
	
	private String resume;
	
	private List<String> motcles=new ArrayList<String>();
	
	@DBRef
	private List<Auteur> auteurs;
	
	private String categorie;
	private String discipline;
	
	

	
	public List<String> getMotcles() {
		return motcles;
	}

	public void setMotcles(List<String> motcles) {
		this.motcles = motcles;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getDiscipline() {
		return discipline;
	}

	public void setDiscipline(String discipline) {
		this.discipline = discipline;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitre() {
		return Titre;
	}

	public void setTitre(String titre) {
		Titre = titre;
	}

	public Date getDatePub() {
		return DatePub;
	}

	public void setDatePub(Date datePub) {
		DatePub = datePub;
	}

	

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public List<Auteur> getAuteurs() {
		return auteurs;
	}

	public void setAuteurs(List<Auteur> auteurs) {
		this.auteurs = auteurs;
	}

	

	@Override
	public String toString() {
		return "Publication [id=" + id + ", Titre=" + Titre + ", DatePub=" + DatePub + ", resume=" + resume
				+ ", motcles=" + motcles + ", auteurs=" + auteurs + ", categorie=" + categorie + ", discipline="
				+ discipline + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DatePub == null) ? 0 : DatePub.hashCode());
		result = prime * result + ((Titre == null) ? 0 : Titre.hashCode());
		result = prime * result + ((auteurs == null) ? 0 : auteurs.hashCode());
		result = prime * result + ((categorie == null) ? 0 : categorie.hashCode());
		result = prime * result + ((discipline == null) ? 0 : discipline.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((motcles == null) ? 0 : motcles.hashCode());
		result = prime * result + ((resume == null) ? 0 : resume.hashCode());
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
		Publication other = (Publication) obj;
		if (DatePub == null) {
			if (other.DatePub != null)
				return false;
		} else if (!DatePub.equals(other.DatePub))
			return false;
		if (Titre == null) {
			if (other.Titre != null)
				return false;
		} else if (!Titre.equals(other.Titre))
			return false;
		if (auteurs == null) {
			if (other.auteurs != null)
				return false;
		} else if (!auteurs.equals(other.auteurs))
			return false;
		if (categorie == null) {
			if (other.categorie != null)
				return false;
		} else if (!categorie.equals(other.categorie))
			return false;
		if (discipline == null) {
			if (other.discipline != null)
				return false;
		} else if (!discipline.equals(other.discipline))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (motcles == null) {
			if (other.motcles != null)
				return false;
		} else if (!motcles.equals(other.motcles))
			return false;
		if (resume == null) {
			if (other.resume != null)
				return false;
		} else if (!resume.equals(other.resume))
			return false;
		return true;
	}

	
	
	
	
	

}
