package com.titosdev.api.repositories;

import com.titosdev.api.models.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    @Query("SELECT s FROM Animal s WHERE s.name = ?1")
    Optional<Animal> findAnimalByName(String name);
}
