package com.odontology.dh.service;

import com.odontology.dh.dto.PatientDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class PatientServiceTest {

    private final IPatientService patientService;

    @Autowired
    PatientServiceTest(IPatientService patientService) {
        this.patientService = patientService;
    }

    @Test
    public void addPatientTest(){
        PatientDto patientDto = new PatientDto();
        patientDto.setName("Walter");
        patientDto.setLastName("White");
        patientDto.setDni(678L);
        patientService.addPatient(patientDto);

        assertNotNull(patientService.searchByNameAndLastName("Walter", "White"));
    }
}

