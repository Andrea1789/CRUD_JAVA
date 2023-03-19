package com.odontology.dh.repository;

import com.odontology.dh.entity.Dentist;
import com.odontology.dh.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPatientRepository extends JpaRepository<Patient, Long> {

    @Query(value = "select * from patients where name = ?1 and last_name = ?2", nativeQuery = true)
    Optional<Patient> findByNameAndLastName(String name, String lastName);
}
