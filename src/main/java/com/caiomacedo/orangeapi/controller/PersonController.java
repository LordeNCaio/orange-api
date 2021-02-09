package com.caiomacedo.orangeapi.controller;

import com.caiomacedo.orangeapi.entity.Person;
import com.caiomacedo.orangeapi.service.PersonVaccineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {

    private final PersonVaccineService personVaccineService;
    PersonController(PersonVaccineService personVaccineService) {
        this.personVaccineService = personVaccineService;
    }

    @GetMapping("/person/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    Person findPerson(@PathVariable Integer id) {
        return personVaccineService.findPerson(id);
    }

    @PostMapping("/person")
    @ResponseStatus(HttpStatus.CREATED)
    void addPerson(@RequestBody Person person) {
        personVaccineService.addPerson(person);
    }

    @DeleteMapping("/person/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deletePerson(@PathVariable Integer id) {
        personVaccineService.deletePerson(id);
    }

}
