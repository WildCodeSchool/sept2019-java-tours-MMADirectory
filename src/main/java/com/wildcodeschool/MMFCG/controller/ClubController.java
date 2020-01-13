package com.wildcodeschool.MMFCG.controller;

import com.wildcodeschool.MMFCG.repository.ClubRepository;
import com.wildcodeschool.MMFCG.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class ClubController {

	@Autowired
	private ClubRepository repository;
	@Autowired
	private RegionRepository regRepository;

	    
	
	//Choix de la region
	@GetMapping("/")
	public String region(Model model) {
		model.addAttribute("regions", regRepository.findAll());
		return "index";
	}

	
	//Choix de la region utilisateur
		@GetMapping("/clubs")
		public String GetClubAdmin(Model model, @RequestParam int region) {
			model.addAttribute("clubs", repository.findByRegion(region));
			return "liste_clubs_client";
	}
	
	//Retourne les fiches détaillées des clubs 
		@GetMapping("/club") 
		public String getClubById(@RequestParam Long id, Model model) { 
			model.addAttribute("fichesClub", repository.getOne(id));
			return "fichesClub"; 
	}

	//Recherche de club par nom
	@GetMapping("/search/club")
	public String GetClubBySearch(Model model, @RequestParam String club){
		model.addAttribute("clubs", repository.findByName(club));
		return "liste_clubs_client";
	}
}
