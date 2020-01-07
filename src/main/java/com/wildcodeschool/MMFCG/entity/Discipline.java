package com.wildcodeschool.MMFCG.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

//Table discipline
@Entity
public class Discipline {
	
	//Champs de la table
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String nom;	
	
	@ManyToMany(mappedBy = "disciplines")
    private List<Club> clubs = new ArrayList<> ();
	

	//constructor 
	
	public Discipline() {	
	}
	
	
	//getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public List<Club> getClubs() {
		return clubs;
	}


	public void setClubs(List<Club> clubs) {
		this.clubs = clubs;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}





	

}
