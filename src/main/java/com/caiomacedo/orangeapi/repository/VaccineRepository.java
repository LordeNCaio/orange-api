package com.caiomacedo.orangeapi.repository;

import com.caiomacedo.orangeapi.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Integer> {
    List<Vaccine> findAllByName(String name);
}
