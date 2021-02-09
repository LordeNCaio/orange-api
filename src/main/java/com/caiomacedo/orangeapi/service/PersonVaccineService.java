package com.caiomacedo.orangeapi.service;

import com.caiomacedo.orangeapi.entity.Person;
import com.caiomacedo.orangeapi.entity.Vaccine;
import com.caiomacedo.orangeapi.exception.PersonAlreadyExistsException;
import com.caiomacedo.orangeapi.exception.PersonNotFoundException;
import com.caiomacedo.orangeapi.exception.VaccineNameInvalidException;
import com.caiomacedo.orangeapi.repository.PersonRepository;
import com.caiomacedo.orangeapi.repository.VaccineRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PersonVaccineService {

    private final PersonRepository personRepository;
    private final VaccineRepository vaccineRepository;

    PersonVaccineService(PersonRepository personRepository, VaccineRepository vaccineRepository) {
        this.personRepository = personRepository;
        this.vaccineRepository = vaccineRepository;
    }

    public Person findPerson(Integer id) {
        return personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
    }

    public void addPerson(Person person) {
        if (personRepository.findByEmail(person.getEmail()).isPresent() || personRepository.findByCpf(person.getCpf()).isPresent()) {
            throw new PersonAlreadyExistsException();
        }
        personRepository.save(person);
    }

    public void deletePerson(Integer id) {
        Person person = findPerson(id);
        personRepository.delete(person);
    }

    public void applyVaccine(Integer person_id, Vaccine vaccine) {
        Person person = findPerson(person_id);
        if (vaccine.getName().length() == 0) {
            throw new VaccineNameInvalidException();
        }
        vaccine.setAppliedAt(LocalDate.now());
        person.addVaccines(vaccine);
        personRepository.save(person);
    }
}
