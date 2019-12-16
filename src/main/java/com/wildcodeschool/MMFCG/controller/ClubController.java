package com.wildcodeschool.MMFCG.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wildcodeschool.MMFCG.entity.Club;
import com.wildcodeschool.MMFCG.entity.Region;
import com.wildcodeschool.MMFCG.repository.ClubRepository;
import com.wildcodeschool.MMFCG.repository.RegionRepository;

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
	
	//Retourne tous les clubs de la region
	@GetMapping("/clubs")
	public String getClubByRegion(Model model, @RequestParam int region) {
		model.addAttribute("clubs", repository.findByRegion(region));
		return "clubs";
	}
	
	
	//Retourne le formulaire d'ajout de club
	@GetMapping("/club")
	@Secured("admin")
	public String getClub(Model model){
		Club club = new Club();
		model.addAttribute("club", club);
		return "club";
	}
	
	//Renvoie sur la liste mise a jour des clubs apres l'ajout d'un club 
    @PostMapping("/club")
    public String postClub(@ModelAttribute Club club) {

        repository.save(club);
        return "redirect:/";
    }

    //Supprime un club en fonction de son id puis renvoie la liste maj
    @GetMapping("/club/delete")
    public String deleteClub(@RequestParam Long id) {
    	Region region = new Region();
        repository.deleteById(id);

        return "redirect:/";
    }
}
