package com.wildcodeschool.MMFCG.entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

//Table Club
@Entity
public class Club {
	
	//Champs de la table club
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String address;
	private String logo;
	private String photo;
	private String description;
	
	//Clé étrangère vers la table region
	@ManyToOne
    @JoinColumn(name ="FK_RegionId")
    private Region region;
	
	//relation avec la table discipline
	
	@ManyToMany
    @JoinTable(name = "club_discipline",
            joinColumns = @JoinColumn(name = "club_id"),
            inverseJoinColumns = @JoinColumn(name = "discipline_id"))
	private List<Discipline> disciplines = new ArrayList<>();
	
	
	//Constructeur
	public Club() {	
	}

	//Getter & Setter
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public Region getRegion() {
		return region;
	}

	
	public void setRegion(Region region) {
		this.region = region;
	}


	public List<Discipline> getDisciplines() {
		return disciplines;
	}

	public void setDisciplines(List<Discipline> disciplines) {
		this.disciplines = disciplines;
	}
	

	

}
