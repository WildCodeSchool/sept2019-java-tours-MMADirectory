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
	private String photo1_url;
	private String logo_url;
	private String photo2_url;
	private String photo3_url;
	private String socialUrl;
	private String postalCode;
	private String phoneNumber;
	private boolean valide;

	@Transient
	private MultipartFile logo;

	@Transient
	private MultipartFile photo1;

	@Transient
	private MultipartFile photo2;

	@Transient
	private MultipartFile photo3;

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

	public String getPhoto1_url() {
		return photo1_url;
	}

	public void setPhoto1_url(String photo1_url) {
		this.photo1_url = photo1_url;
	}

	public String getPhoto2_url() {
		return photo2_url;
	}

	public void setPhoto2_url(String photo2_url) {
		this.photo2_url = photo2_url;
	}

	public String getPhoto3_url() {
		return photo3_url;
	}

	public void setPhoto3_url(String photo3_url) {
		this.photo3_url = photo3_url;
	}


	public MultipartFile getLogo() {
		return logo;
	}

	public void setLogo(MultipartFile logo) {
		this.logo = logo;
	}

	public MultipartFile getPhoto1() {
		return photo1;
	}

	public void setPhoto1(MultipartFile photo1) {
		this.photo1 = photo1;
	}

	public MultipartFile getPhoto2() {
		return photo2;
	}

	public void setPhoto2(MultipartFile photo2) {
		this.photo2 = photo2;
	}

	public MultipartFile getPhoto3() {
		return photo3;
	}

	public void setPhoto3(MultipartFile photo3) {
		this.photo3 = photo3;
	}

	public String getLogo_url() {
		return logo_url;
	}

	public void setLogo_url(String logo_url) {
		this.logo_url = logo_url;
	}

	public boolean isValide() {
		return valide;
	}

	public void setValide(boolean valide) {
		this.valide = valide;
	}
}
