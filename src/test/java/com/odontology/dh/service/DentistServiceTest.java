package com.odontology.dh.service;

import com.odontology.dh.dto.DentistDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class DentistServiceTest {

    private final IDentistService dentistService;


    @Autowired
    DentistServiceTest(IDentistService dentistService) {
        this.dentistService = dentistService;
    }

   @Test
    public void addDentistTest(){

        dentistService.addDentist(654L, "Hank", "Pinkman");

        assertNotNull(dentistService.searchDentist(1l));

    }



}