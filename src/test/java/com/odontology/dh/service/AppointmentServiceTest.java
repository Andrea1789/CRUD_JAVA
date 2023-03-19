package com.odontology.dh.service;

import com.odontology.dh.entity.Appointment;
import com.odontology.dh.entity.Dentist;
import com.odontology.dh.entity.Patient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class AppointmentServiceTest {

    @Autowired
    IPatientService patientService;

    @Autowired
    IDentistService dentistService;

    @Autowired
    IAppointmentService appointmentService;


    @Test
    public void AddNewAppointmentTest() throws ServiceException {

        Patient patient = new Patient();
        Dentist dentist = new Dentist();
        Appointment appointment = new Appointment();
        this.patientService.searchPatient(1L);
        this.dentistService.searchDentist(1L);
        appointment.setAppointmentDate("2023-02-15");

        this.appointmentService.addAppointment(1L, 1L,"2023-02-15");

        Assertions.assertNotNull(appointmentService.searchByDate("23-02-15"));
    }

}