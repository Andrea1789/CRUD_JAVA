package com.odontology.dh.repository;

import com.odontology.dh.entity.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface IDentistRepository extends JpaRepository<Dentist, Long> {
    @Query(value = "select * from dentists where registration = ?", nativeQuery = true)
    Optional<Dentist> findDentistByRegistration(Long registration);
}
