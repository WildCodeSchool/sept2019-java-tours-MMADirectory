package com.wildcodeschool.MMFCG.controller;

import com.wildcodeschool.MMFCG.entity.Discipline;
import com.wildcodeschool.MMFCG.repository.ClubRepository;
import com.wildcodeschool.MMFCG.repository.DisciplineRepository;
import com.wildcodeschool.MMFCG.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


@Controller
public class ClubController {

	@Autowired
	private ClubRepository repository;
	@Autowired
	private RegionRepository regRepository;
	@Autowired
	private DisciplineRepository disciplineRepository;
	    
	
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
	public String getClubBySearch(Model model, @RequestParam String club){
		model.addAttribute("clubs", repository.findByName(club));
		return "liste_clubs_client";
	}
	
	@GetMapping("/disciplines")
	public String getDisciplines(Model model) {
		model.addAttribute("disciplines", disciplineRepository.findAll());
		return "liste_disciplines";
	}
	
	@GetMapping("/discipline/{id}")
	public String getClubByDiscipline(Model model, @PathVariable long id) {
		Optional<Discipline> discipline = disciplineRepository.findById(id);
		if(discipline.isPresent()) {
			model.addAttribute("discipline", discipline.get());
			return "club_by_discipline";
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Le club n'existe pas !");
	}
	
}
