package com.wildcodeschool.MMFCG.controller;

import com.wildcodeschool.MMFCG.entity.Club;
import com.wildcodeschool.MMFCG.entity.Discipline;
import com.wildcodeschool.MMFCG.entity.Region;
import com.wildcodeschool.MMFCG.repository.ClubRepository;
import com.wildcodeschool.MMFCG.repository.DisciplineRepository;
import com.wildcodeschool.MMFCG.repository.RegionRepository;
import com.wildcodeschool.MMFCG.storage.StorageFileNotFoundException;
import com.wildcodeschool.MMFCG.storage.StorageService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.thymeleaf.expression.Strings;

import java.util.Optional;
import java.util.Random;


@Controller
public class AdminController {

	@Autowired
	private ClubRepository repository;
	@Autowired
	private RegionRepository regRepository;
	@Autowired
	private DisciplineRepository disciplineRepository;


    private StorageService storageService;
	private Strings RandomStringUtils;

	@Autowired
    public void AdminController(StorageService storageService) {
        this.storageService = storageService;
    }

    
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
				model.addAttribute("disciplines", disciplineRepository.findAll());
				model.addAttribute("regions", regRepository.findAll());
		        return "club";
		}

		//Renvoie sur la liste mise a jour des clubs apres l'ajout d'un club
		@PostMapping("/admin/edit")
		public String postClub( @ModelAttribute Club club) {
			Random rand =  new Random();
			String logoFilename = "logo." + club.getLogo().getOriginalFilename().split("\\.")[1];
			String photoFilename = rand.nextInt(5000)+"."+club.getPhoto().getOriginalFilename().split("\\.")[1];

			club.setId(repository.save(club).getId());

			storageService.store(club.getLogo(), logoFilename , club.getId());
			club.setLogo_url("/files/" + club.getId()+ "/"+ logoFilename);
			storageService.store(club.getPhoto(),  photoFilename, club.getId());
			club.setPhoto_url("/files/" + club.getId()+ "/" + photoFilename);

			repository.save(club);
			return "redirect:/admin";

		}

		//Supprime un club en fonction de son id puis renvoie la liste maj
	    @GetMapping("/admin/delete")
	    public String deleteClub(@RequestParam Long id) {
	        repository.deleteById(id);

	        return "redirect:/admin";
	    }
	    
	    //Récupère les infos d'un club en fonction de son id
	    @GetMapping("/admin/modify")
	    public String getClubUpdate(Model model,@RequestParam Long id) {
			Optional<Club> club = repository.findById(id);
			if(club.isPresent()) {
				model.addAttribute("regions", regRepository.findAll());
				model.addAttribute("club", club.get());
				model.addAttribute("disciplines", disciplineRepository.findAll());
				return "modifyClub";
			}

			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Le club n'existe pas !");
	    }

	    //Enregistre les modifications d'un club
	    @PostMapping("/admin/modify")
		public String postClubUpdate( @ModelAttribute Club club) {
			Random rand =  new Random();

			String logoFilename = "logo." + club.getLogo().getOriginalFilename().split(".")[1];
			String photoFilename = rand.nextInt(5000)+"."+club.getPhoto().getOriginalFilename().split(".")[1];

			storageService.store(club.getLogo(), logoFilename , club.getId());
			club.setLogo_url("/files/" + club.getId()+ "/"+ logoFilename);
			storageService.store(club.getPhoto(),  photoFilename, club.getId());
			club.setPhoto_url("/files/" + club.getId()+ "/" + photoFilename);

			repository.save(club);
			return "redirect:/admin";

		}

	    @ExceptionHandler(StorageFileNotFoundException.class)
	    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
	        return ResponseEntity.notFound().build();
	    }
	    
	    @GetMapping("/files/{clubId}/{filename:.+}")
	    @ResponseBody
	    public ResponseEntity<Resource> serveFile(@PathVariable int clubId, @PathVariable String filename) {
	        Resource file = storageService.loadAsResource(clubId + "/"+filename);
	        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
	                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	    }

}
