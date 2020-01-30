package com.wildcodeschool.MMFCG.entity;


import com.wildcodeschool.MMFCG.entity.Discipline;
import com.wildcodeschool.MMFCG.entity.Region;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//Table Club
@Entity
public class Club {

	//Champs de la table club
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String address;
	private String ville;
	private String description;
	private String photo_url;
	private String logo_url;
	private String socialUrl;
	private String postalCode;
	private String phoneNumber;

	@Transient
	private MultipartFile logo;

	@Transient
	private MultipartFile photo;

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
	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	

	public String getSocialUrl() {
		return socialUrl;
	}

	public void setSocialUrl(String socialUrl) {
		this.socialUrl = socialUrl;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
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

	public MultipartFile getLogo() {
		return logo;
	}

	public MultipartFile getPhoto() {
		return photo;
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

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getPhoto_url() {
		return photo_url;
	}

	public void setPhoto_url(String photo_url) {
		this.photo_url = photo_url;
	}

	public String getLogo_url() {
		return logo_url;
	}

	public void setLogo_url(String logo_url) {
		this.logo_url = logo_url;
	}

	public void setLogo(MultipartFile logo) {
		this.logo = logo;
	}

	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}
}
