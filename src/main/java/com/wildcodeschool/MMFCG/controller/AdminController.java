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
			String photo1Filename = rand.nextInt(5000)+"."+club.getPhoto1().getOriginalFilename().split("\\.")[1];
			String photo2Filename = rand.nextInt(5000)+"."+club.getPhoto2().getOriginalFilename().split("\\.")[1];
			String photo3Filename = rand.nextInt(5000)+"."+club.getPhoto3().getOriginalFilename().split("\\.")[1];

			club.setId(repository.save(club).getId());

			storageService.store(club.getLogo(), logoFilename , club.getId());
			club.setLogo_url("/files/" + club.getId()+ "/"+ logoFilename);

			storageService.store(club.getPhoto1(),  photo1Filename, club.getId());
			club.setPhoto1_url("/files/" + club.getId()+ "/" + photo1Filename);

			storageService.store(club.getPhoto2(),  photo2Filename, club.getId());
			club.setPhoto2_url("/files/" + club.getId()+ "/" + photo2Filename);

			storageService.store(club.getPhoto3(),  photo3Filename, club.getId());
			club.setPhoto3_url("/files/" + club.getId()+ "/" + photo3Filename);

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
			String[] splitedLogoName = club.getLogo().getOriginalFilename().split(".");
			String splitedPhoto1Name = rand.nextInt(5000)+"."+club.getPhoto1().getOriginalFilename();
			String splitedPhoto2Name = rand.nextInt(5000)+"."+club.getPhoto2().getOriginalFilename();
			String splitedPhoto3Name = rand.nextInt(5000)+"."+club.getPhoto3().getOriginalFilename();

			String logoFilename = "logo.";

			if(splitedLogoName == null || splitedLogoName.length <2){
				logoFilename += ".png";
			}else{
				logoFilename += splitedLogoName[1];
			}

			String photoFilename = "photo.";
			if(splitedPhoto1Name == null || splitedPhoto1Name.length() <2){
				logoFilename += ".png";
			}else{
				logoFilename += splitedPhoto1Name;
			}
			if(splitedPhoto2Name == null || splitedPhoto2Name.length() <2){
				logoFilename += ".png";
			}else{
				logoFilename += splitedPhoto1Name;
			}
			if(splitedPhoto3Name == null || splitedPhoto3Name.length() <2){
				logoFilename += ".png";
			}else{
				logoFilename += splitedPhoto1Name;
			}
			storageService.store(club.getLogo(), logoFilename , club.getId());
			club.setLogo_url("/files/" + club.getId()+ "/"+ logoFilename);

			storageService.store(club.getPhoto1(),  photoFilename, club.getId());
			club.setPhoto1_url("/files/" + club.getId()+ "/" + photoFilename);
			storageService.store(club.getPhoto2(),  photoFilename, club.getId());
			club.setPhoto2_url("/files/" + club.getId()+ "/" + photoFilename);
			storageService.store(club.getPhoto3(),  photoFilename, club.getId());
			club.setPhoto3_url("/files/" + club.getId()+ "/" + photoFilename);

			repository.save(club);
			return "redirect:/admin";

		}
		@GetMapping("/admin/waiting")
		public String waitingClubs(Model model){
			model.addAttribute("clubs", repository.findAllFalse());
			return "waiting_clubs";
		}


		@GetMapping("/admin/valid/{id}")
		public String getClubValid(@PathVariable long id, Model model){
		Optional<Club> club = repository.findById(id);
		if(club.isPresent()){
			model.addAttribute("club", club.get());
			return "waiting_club_detail";
		}
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Le club n'existe pas !");
		}

		@PostMapping("/admin/valid/{id}")
		public String postClubValid(@PathVariable long id, @ModelAttribute Club club){
			club.setValide(true);
			repository.save(club);
			return "redirect:/admin/waiting";
		}

		@GetMapping("/admin/unvalid/{id}")
		public String unvalidClub(@PathVariable long id){
			repository.deleteById(id);
			return "redirect:/admin/waiting";
		}

		@GetMapping("/register")
		public String formProposeClub(Model model){
			model.addAttribute("club", new Club());
			model.addAttribute("disciplines", disciplineRepository.findAll());
			model.addAttribute("regions", regRepository.findAll());
			return "register";
		}

		@PostMapping("/register")
		public String postClubRegister(@ModelAttribute Club club){
			Random rand =  new Random();
			String logoFilename = "logo." + club.getLogo().getOriginalFilename().split("\\.")[1];
			String photo1Filename = rand.nextInt(5000)+"."+club.getPhoto1().getOriginalFilename().split("\\.")[1];
			String photo2Filename = rand.nextInt(5000)+"."+club.getPhoto2().getOriginalFilename().split("\\.")[1];
			String photo3Filename = rand.nextInt(5000)+"."+club.getPhoto3().getOriginalFilename().split("\\.")[1];

			club.setId(repository.save(club).getId());

			storageService.store(club.getLogo(), logoFilename , club.getId());
			club.setLogo_url("/files/" + club.getId()+ "/"+ logoFilename);

			storageService.store(club.getPhoto1(),  photo1Filename, club.getId());
			club.setPhoto1_url("/files/" + club.getId()+ "/" + photo1Filename);

			storageService.store(club.getPhoto2(),  photo2Filename, club.getId());
			club.setPhoto2_url("/files/" + club.getId()+ "/" + photo2Filename);

			storageService.store(club.getPhoto3(),  photo3Filename, club.getId());
			club.setPhoto3_url("/files/" + club.getId()+ "/" + photo3Filename);
			repository.save(club);
			return "valide";
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
