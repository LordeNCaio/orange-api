package com.caiomacedo.orangeapi.service;

import com.caiomacedo.orangeapi.entity.Person;
import com.caiomacedo.orangeapi.entity.Vaccine;
import com.caiomacedo.orangeapi.repository.PersonRepository;
import com.caiomacedo.orangeapi.repository.VaccineRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PersonVaccineService {

    private final PersonRepository personRepository;
    private final VaccineRepository vaccineRepository;

    PersonVaccineService(PersonRepository personRepository, VaccineRepository vaccineRepository) {
        this.personRepository = personRepository;
        this.vaccineRepository = vaccineRepository;
    }

    public ResponseEntity findPerson(Integer id) {
        if(personRepository.findById(id).isPresent()) {
            var res = personRepository.findById(id).get();
            return ResponseEntity.status(HttpStatus.FOUND).body(res);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found");
    }

    public ResponseEntity<Object> addPerson(Person person) {
        if(person.getName().length() == 0 || person.getEmail().length() == 0 || person.getCpf().length() == 0 || person.getBornAt() == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Person is invalid");
        if(personRepository.findByEmailAndCpf(person.getEmail(), person.getCpf()).isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Person already exists");
        personRepository.save(person);
        return ResponseEntity.status(HttpStatus.CREATED).body("Person successfully created");
    }

    public ResponseEntity deletePerson(Integer id) {
        if(personRepository.findById(id).isPresent()) {
            personRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("The given person has been deleted");
        }
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("No Persons found");
    }

    public ResponseEntity<Object> addVaccine(Integer person_id, Vaccine vaccine) {
        if(personRepository.findById(person_id).isPresent()){
            var person = personRepository.getOne(person_id);
            if(vaccine.getName().length() != 0 && vaccine.getAppliedAt() != null) {
                person.addVaccines(vaccine);
                personRepository.save(person);
                return ResponseEntity.status(HttpStatus.CREATED).body("Vaccine successfully created");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Vaccine is invalid");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found");
    }
}
