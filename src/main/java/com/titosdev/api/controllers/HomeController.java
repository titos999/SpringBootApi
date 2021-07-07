package com.titosdev.api.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(path = "home")
public class HomeController {

    private String message = "Welcome to the front page";
    private List<String> tasks = Arrays.asList("a", "b", "c", "d", "e", "f", "g");

    @GetMapping
    public String getWelcomeMessage(Model model) {
        model.addAttribute("message", message);
        model.addAttribute("tasks", tasks);
        return "welcome";
    }

    @GetMapping("/hello")
    public String mainWithParam( @RequestParam(name = "name", required = false, defaultValue = "") String name, Model model ) {
        model.addAttribute("message", name);
        return "welcome";
    }
}
