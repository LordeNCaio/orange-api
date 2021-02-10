package com.caiomacedo.orangeapi.service;

import com.caiomacedo.orangeapi.entity.Person;
import com.caiomacedo.orangeapi.entity.Vaccine;
import com.caiomacedo.orangeapi.exception.PersonAlreadyExistsException;
import com.caiomacedo.orangeapi.exception.PersonNotFoundException;
import com.caiomacedo.orangeapi.exception.VaccineNotFoundException;
import com.caiomacedo.orangeapi.repository.PersonRepository;
import com.caiomacedo.orangeapi.repository.VaccineRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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

    public List<Vaccine> findVaccineByName(String name) {
        return vaccineRepository.findAllByName(name);
    }

    public void applyVaccine(Integer person_id, Vaccine vaccine) {
        Person p = findPerson(person_id);
        vaccine.setPerson(p);
        vaccine.setAppliedAt(LocalDate.now());
        p.addVaccines(vaccine);
        personRepository.save(p);
    }
}
