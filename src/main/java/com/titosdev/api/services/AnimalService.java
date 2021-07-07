package com.titosdev.api.services;

import com.titosdev.api.models.Animal;
import com.titosdev.api.repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AnimalService {
    private final AnimalRepository animalRepository;

    @Autowired
    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<Animal> getAnimals() {
        return animalRepository.findAll();
    }

    public void addNewAnimal(Animal animal) {
        Optional<Animal> animalOptional = animalRepository.findAnimalByName(animal.getName());

        if(animalOptional.isPresent()) {
            throw new IllegalStateException("Animal Name taken");
        }

        animalRepository.save(animal);
    }

    public void deleteAnimal(Long animalId) {
        boolean exists = animalRepository.existsById(animalId);

        if(!exists) {
            throw new IllegalStateException("Animal with id " + animalId + " does not exist");
        }

        animalRepository.deleteById(animalId);
    }

    @Transactional
    public void updateAnimal(Long animalId, String name) {

        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new IllegalStateException(
                        "Animal with id " + animalId + " does not exist."
                ));

        if(name != null && name.length() > 0 && !Objects.equals(animal.getName(), name)) {
            animal.setName(name);
        }

    }
}
