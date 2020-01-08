package com.wildcodeschool.MMFCG.controller;

import com.wildcodeschool.MMFCG.entity.Club;
import com.wildcodeschool.MMFCG.entity.Region;
import com.wildcodeschool.MMFCG.repository.ClubRepository;
import com.wildcodeschool.MMFCG.repository.DisciplineRepository;
import com.wildcodeschool.MMFCG.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class AdminController {

	@Autowired
	private ClubRepository repository;
	@Autowired
	private RegionRepository regRepository;
	@Autowired
	private DisciplineRepository disciplineRepository;


	//Retourne tous les clubs de la region admin
	@RequestMapping("/admin")
	public String getClubByRegion(Model model) {
		model.addAttribute("clubs", repository.findAll());
		return "edit";
	}
	
	//Retourne le formulaire d'ajout de club
	@GetMapping("/admin/edit")
	@Secured("admin")
	public String getClub(Model model){
	
	        Club club = new Club();
	        
	        model.addAttribute("club", club);
	        //model.addAttribute("allDisciplines", disciplineRepository.findAll());
		
				
	        return "club";
	}

	//Renvoie sur la liste mise a jour des clubs apres l'ajout d'un club
	@PostMapping("/admin/edit")
	public String postClub( @ModelAttribute Club club) {

		repository.save(club);


		return "redirect:/admin";

	}

	//Supprime un club en fonction de son id puis renvoie la liste maj
    @GetMapping("/admin/club/delete")
    public String deleteClub(@RequestParam Long id) {
    	Region region = new Region();
        repository.deleteById(id);

        return "redirect:/admin";
    }
    

    
   
    
  
}
