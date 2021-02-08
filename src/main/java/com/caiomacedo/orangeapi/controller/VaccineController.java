package com.caiomacedo.orangeapi.controller;

import com.caiomacedo.orangeapi.entity.Vaccine;
import com.caiomacedo.orangeapi.service.PersonVaccineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class VaccineController {

    private final PersonVaccineService personVaccineService;
    VaccineController(PersonVaccineService personVaccineService) {
        this.personVaccineService = personVaccineService;
    }

    @PostMapping("/person/{id}/apply")
    ResponseEntity applyVaccine(@PathVariable Integer id, @RequestBody Vaccine vaccine) {
        return personVaccineService.addVaccine(id, vaccine);
    }

}
