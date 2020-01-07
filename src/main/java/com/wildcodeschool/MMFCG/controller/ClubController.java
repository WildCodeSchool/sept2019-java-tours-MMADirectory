package com.wildcodeschool.MMFCG.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wildcodeschool.MMFCG.entity.Club;
import com.wildcodeschool.MMFCG.entity.Discipline;
import com.wildcodeschool.MMFCG.entity.Region;
import com.wildcodeschool.MMFCG.repository.ClubRepository;
import com.wildcodeschool.MMFCG.repository.DisciplineRepository;
import com.wildcodeschool.MMFCG.repository.RegionRepository;


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
	
	
		
	//Retourne tous les clubs de la region admin
	@GetMapping("/listclubs")
	public String getClubByRegion(Model model, @RequestParam int region) {
		model.addAttribute("clubs", repository.findByRegion(region));
		return "listclubs";
	}
	
	//Choix de la region utilisateur
		@GetMapping("/listeClubs")
		public String GetClubAdmin(Model model, @RequestParam int region) {
			model.addAttribute("clubs", repository.findByRegion(region));
			return "listeClubs";
	}
	
	//Retourne le formulaire d'ajout de club
	
	@GetMapping("/club")
	@Secured("admin")
	public String getClub(Model model){
	
	        Club club = new Club();
	        
	        model.addAttribute("club", club);
	        model.addAttribute("allDisciplines", disciplineRepository.findAll());
		
				
	        return "club";
	}
	
	@PostMapping("/club")
    public String postRegister(@RequestParam Long clubId,
                               @RequestParam Long disciplineId) {
		
	    Optional<Club> optionalClub = repository.findById(clubId);
        if (optionalClub.isPresent()) {
        	Club club = optionalClub.get();

            Optional<Discipline> optionalDiscipline = disciplineRepository.findById(disciplineId);
            if (optionalDiscipline.isPresent()) {
            	Discipline discipline = optionalDiscipline.get();

                // appelle la méthode getDiscipline dans Club
                List<Discipline> disciplines;
                Method method = getMethod(club, "getClubs",
                        new Class[]{});
                if (method != null) {
                    try {
                    	disciplines = (List<Discipline>) method.invoke(club);
                    	disciplines.add(discipline);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }

                repository.save(club);
                disciplineRepository.save(discipline);
            }
        }
        return "club";
        
    }
		

	
	//Renvoie sur la liste mise a jour des clubs apres l'ajout d'un club 
//    @PostMapping("/club")
//    public String postClub( @RequestParam Club club, @RequestParam Discipline discipline  ) {
//    
//        repository.save(club);
//      
//        
//        return "redirect:/clubs";
//   
//        }    

	
	//editer un club

	@GetMapping("/club/edit/{id}")
    public String editClub(@RequestParam Long id) {
    	Club club = new Club();
        repository.save(club);

        return "edit";
        
    }
	
	
	//Supprime un club en fonction de son id puis renvoie la liste maj
    @GetMapping("/club/delete")
    public String deleteClub(@RequestParam Long id) {
    	Region region = new Region();
        repository.deleteById(id);

        return "redirect:/clubs";
    }
    
    public Method getMethod(Object obj, String methodName, Class[] args) {
        Method method;
        try {
            method = obj.getClass().getDeclaredMethod(methodName, args);
            return method;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
    
   
    
  
}
