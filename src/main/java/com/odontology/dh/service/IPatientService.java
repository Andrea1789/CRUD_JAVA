package com.odontology.dh.service;

import com.odontology.dh.dto.PatientDto;


import java.util.Set;


public interface IPatientService {


    PatientDto addPatient(PatientDto patientDto);

    PatientDto searchPatient(Long id);

    PatientDto searchByNameAndLastName(String name, String lastName);

    PatientDto updatePatient(PatientDto patientDto);


    void deletePatient(Long id) throws ServiceException;
    Set<PatientDto> showAll();
}
