package com.caiomacedo.orangeapi.controller;

import com.caiomacedo.orangeapi.entity.Vaccine;
import com.caiomacedo.orangeapi.service.PersonVaccineService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class VaccineController {

    private final PersonVaccineService personVaccineService;
    VaccineController(PersonVaccineService personVaccineService) {
        this.personVaccineService = personVaccineService;
    }

    @PostMapping("/vaccine/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    void applyVaccine(@PathVariable Integer id, @RequestBody Vaccine vaccine) {
        personVaccineService.applyVaccine(id, vaccine);
    }

}
