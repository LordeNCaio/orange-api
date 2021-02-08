package com.caiomacedo.orangeapi.repository;

import com.caiomacedo.orangeapi.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Integer> {

}
