package com.caiomacedo.orangeapi.repository;

import com.caiomacedo.orangeapi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findByEmailAndCpf(String email, String cpf);
}
