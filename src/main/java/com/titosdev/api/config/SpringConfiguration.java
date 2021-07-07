package com.titosdev.api.config;

import com.titosdev.api.models.Animal;
import com.titosdev.api.models.Student;
import com.titosdev.api.repositories.AnimalRepository;
import com.titosdev.api.repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import javax.sql.DataSource;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class SpringConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix="spring.second-datasource")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    CommandLineRunner studentCommandLineRunner(StudentRepository repository) {
        return args -> {
            Student ntinos = new Student(1L,
                    "Chris",
                    "chris@gmail.com",
                    LocalDate.of(2001, Month.JUNE, 24)
            );

            Student xristos = new Student(
                    "Nick",
                    "nick@gmail.com",
                    LocalDate.of(1994, Month.MARCH, 24)
            );

            List<Student> studentList = new ArrayList<>();
            studentList.add(ntinos);
            studentList.add(xristos);

            repository.saveAll(studentList);
        };
    }

    @Bean
    CommandLineRunner animalCommandLineRunner(AnimalRepository repository) {
        return args -> {
            Animal dog = new Animal(1L,
                    "Dog"
            );

            Animal cat = new Animal(
                    "Cat"
            );

            List<Animal> animalList = new ArrayList<>();
            animalList.add(dog);
            animalList.add(cat);

            repository.saveAll(animalList);
        };
    }


}
