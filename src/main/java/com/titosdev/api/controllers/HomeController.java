package com.titosdev.api.controllers;

import com.titosdev.api.services.AnimalService;
import com.titosdev.api.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "home")
public class HomeController {

    private String message = "Welcome to the front page";
    private final StudentService studentService;
    private final AnimalService animalService;

    @Autowired
    public HomeController(StudentService studentService, AnimalService animalService) {
        this.studentService = studentService;
        this.animalService = animalService;
    }

    @GetMapping
    public String getWelcomeMessage(Model model) {
        model.addAttribute("message", message);
        model.addAttribute("students", studentService.getStudents());
        model.addAttribute("animals", animalService.getAnimals());
        return "welcome";
    }

    @GetMapping("/hello")
    public String mainWithParam( @RequestParam(name = "name", required = false, defaultValue = "") String name, Model model ) {
        model.addAttribute("message", name);
        return "welcome";
    }

}
