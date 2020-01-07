//package com.wildcodeschool.MMFCG.controller;
//
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.wildcodeschool.MMFCG.entity.Club;
//import com.wildcodeschool.MMFCG.entity.Discipline;
//import com.wildcodeschool.MMFCG.repository.ClubRepository;
//import com.wildcodeschool.MMFCG.repository.DisciplineRepository;
//
//
//public class HomeController {
//	@Autowired
//    private ClubRepository clubRepository;
//
//    
//    @Autowired
//    private DisciplineRepository disciplineRepository;
//
//    @GetMapping("/")
//    public String getClubs(Model out) {
//
//        out.addAttribute("clubs", clubRepository.findAll());
//
//        return "clubs";
//    }
//
//    @GetMapping("/club")
//    public String getRegister(Model out,
//                              @RequestParam Long idClub) {
//
//        Optional<Club> optionalClub = clubRepository.findById(idClub);
//        Club club = new Club();
//        if (optionalClub.isPresent()) {
//            club = optionalClub.get();
//        }
//        out.addAttribute("club", club);
//        out.addAttribute("allClubs", disciplineRepository.findAll());
//
//        // call the method getCourses in Wizard
//        List<Discipline> disciplines = new ArrayList<>();
//        Method method = getMethod(club, "getDisciplines",
//                new Class[]{});
//        if (method != null) {
//            try {
//                disciplines = (List<Discipline>) method.invoke(club);
//            } catch (IllegalAccessException | InvocationTargetException e) {
//                e.printStackTrace();
//            }
//        }
//        out.addAttribute("clubDisciplines", disciplines);
//
//        return "register";
//    }
//
//    @PostMapping("/club")
//    public String postRegister(@RequestParam Long idClub,
//                               @RequestParam Long idDiscipline) {
//
//        Optional<Club> optionalclub = clubRepository.findById(idClub);
//        if (optionalclub.isPresent()) {
//            Club club = optionalclub.get();
//
//            Optional<Discipline> optionalDiscipline = disciplineRepository.findById(idDiscipline);
//            if (optionalDiscipline.isPresent()) {
//                Discipline discipline = optionalDiscipline.get();
//
//                // call the method getCourses in Wizard
//                List<Discipline> disciplines;
//                Method method = getMethod(club, "getDisciplines",
//                        new Class[]{});
//                if (method != null) {
//                    try {
//                        disciplines = (List<Discipline>) method.invoke(club);
//                        disciplines.add(discipline);
//                    } catch (IllegalAccessException | InvocationTargetException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                clubRepository.save(club);
//            }
//        }
//
//        return "redirect:/club/?idClub=" + idClub;
//    }
//
//    public Method getMethod(Object obj, String methodName, Class[] args) {
//        Method method;
//        try {
//            method = obj.getClass().getDeclaredMethod(methodName, args);
//            return method;
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//}
