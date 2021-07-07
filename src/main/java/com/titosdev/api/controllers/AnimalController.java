package com.titosdev.api.controllers;

import com.titosdev.api.models.Animal;
import com.titosdev.api.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/animal")
public class AnimalController {

    private final AnimalService animalService;

    @Autowired
    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }


    @GetMapping
    public List<Animal> getAnimals() {
        return animalService.getAnimals();
    }

    @PostMapping
    public void registerNewAnimal(@RequestBody Animal animal) {
        animalService.addNewAnimal(animal);
    }

    @DeleteMapping(path = "{animalId}")
    public void deleteAnimal(@PathVariable("animalId") Long animalId) {
        animalService.deleteAnimal(animalId);
    }

    @PutMapping(path = "{animalId}")
    public void updateAnimal(
            @PathVariable("animalId") Long animalId,
            @RequestParam(required = false) String name) {
        animalService.updateAnimal(animalId, name);
    }

}
