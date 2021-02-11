package com.caiomacedo.orangeapi.controller;

import com.caiomacedo.orangeapi.entity.Person;
import com.caiomacedo.orangeapi.service.PersonVaccineService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @GetMapping("/person/mail/{email}")
    @ResponseStatus(HttpStatus.FOUND)
    Person findPersonEmail(@PathVariable String email) {
        return personVaccineService.findPersonEmail(email);
    }

    @GetMapping("/person/cpf/{cpf}")
    @ResponseStatus(HttpStatus.FOUND)
    Person findPersonCpf(@PathVariable String cpf) {
        return personVaccineService.findPersonCpf(cpf);
    }

    @PostMapping("/person")
    @ResponseStatus(HttpStatus.CREATED)
    void addPerson(@Valid @RequestBody Person person) {
        personVaccineService.addPerson(person);
    }

    @DeleteMapping("/person/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deletePerson(@PathVariable Integer id) {
        personVaccineService.deletePerson(id);
    }

}
