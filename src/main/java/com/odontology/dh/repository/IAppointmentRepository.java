package com.odontology.dh.repository;

import com.odontology.dh.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query(value = "select * from appointments where appointmentDate = ?", nativeQuery = true)
    List<Appointment> findByDate(String appointmentDate);
}
