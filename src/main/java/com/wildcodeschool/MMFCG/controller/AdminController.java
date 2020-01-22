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
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;



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
    public void FileUploadController(StorageService storageService) {
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
	    	storageService.store(club.getLogo());
	    	club.setLogo_url("/files/" + club.getLogo().getOriginalFilename());
	    	storageService.store(club.getPhoto());
	    	club.setPhoto_url("/files/" + club.getPhoto().getOriginalFilename());

			repository.save(club);


			return "redirect:/admin";

		}

		//Supprime un club en fonction de son id puis renvoie la liste maj
	    @GetMapping("/admin/delete")
	    public String deleteClub(@RequestParam Long id) {
	    	Region region = new Region();
	        repository.deleteById(id);

	        return "redirect:/admin";
	    }
	    
	    //Modifie un club en fonction de son id
	    @GetMapping("/admin/update")
	    public String getClubUpdate(Model model,@RequestParam Long id) {
	    	model.addAttribute("club", repository.findById(id));
	    	return "update_club";
	    }
	    
	    @PostMapping("/admin/update")
		public String postClubUpdate( @ModelAttribute Club club) {
	    	storageService.store(club.getLogo());
	    	club.setLogo_url("/files/" + club.getLogo().getOriginalFilename());
	    	storageService.store(club.getPhoto());
	    	club.setPhoto_url("/files/" + club.getPhoto().getOriginalFilename());

			repository.save(club);
			return "redirect:/admin";

		}

	    @ExceptionHandler(StorageFileNotFoundException.class)
	    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
	        return ResponseEntity.notFound().build();
	    }
	    
	    @GetMapping("/files/{filename:.+}")
	    @ResponseBody
	    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
	        Resource file = storageService.loadAsResource(filename);
	        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
	                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	    }
}
