package com.caiomacedo.orangeapi.controller;

import com.caiomacedo.orangeapi.entity.Person;
import com.caiomacedo.orangeapi.service.PersonVaccineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {

    private final PersonVaccineService personVaccineService;
    PersonController(PersonVaccineService personVaccineService) {
        this.personVaccineService = personVaccineService;
    }

    @GetMapping("/person/{id}")
    ResponseEntity findPerson(@PathVariable Integer id) {
        return personVaccineService.findPerson(id);
    }

    @PostMapping("/person")
    ResponseEntity<Object> addPerson(@RequestBody Person person) {
        return personVaccineService.addPerson(person);
    }

    @DeleteMapping("/person/del/{id}")
    ResponseEntity deletePerson(@PathVariable Integer id) {
        return personVaccineService.deletePerson(id);
    }

}
