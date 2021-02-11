package com.caiomacedo.orangeapi.controller;

import com.caiomacedo.orangeapi.entity.Vaccine;
import com.caiomacedo.orangeapi.service.PersonVaccineService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class VaccineController {

    private final PersonVaccineService personVaccineService;

    VaccineController(PersonVaccineService personVaccineService) {
        this.personVaccineService = personVaccineService;
    }

    @PostMapping("/vaccine/{person_id}")
    @ResponseStatus(HttpStatus.CREATED)
    void applyVaccine(@PathVariable Integer person_id, @Valid @RequestBody Vaccine vaccine) {
        personVaccineService.applyVaccine(person_id, vaccine);
    }

    @GetMapping("/vaccine/{name}")
    List<Vaccine> findVaccineByName(@PathVariable String name) {
        return personVaccineService.findVaccineByName(name);
    }

    @GetMapping("vaccine/mail/{email}")
    List<Vaccine> findVaccineByPersonEmail(@PathVariable String email) {
        return personVaccineService.findVaccineByPersonEmail(email);
    }

    @GetMapping("vaccine/cpf/{cpf}")
    List<Vaccine> findVaccineByPersonCpf(@PathVariable String cpf) {
        return personVaccineService.findVaccineByPersonCpf(cpf);
    }
}
