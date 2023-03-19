package com.odontology.dh.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.odontology.dh.dto.PatientDto;
import com.odontology.dh.entity.Patient;
import com.odontology.dh.repository.IPatientRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PatientService implements IPatientService{

    private static final Logger logger = LogManager.getLogger(PatientService.class);


    @Autowired
    IPatientRepository patientRepository;

    @Autowired
    ObjectMapper oMapper;


    @Override
    public PatientDto addPatient(PatientDto patientDto){
        Patient patient;

        if(!(patientDto.getDni() == null)){
            patient = oMapper.convertValue(patientDto, Patient.class);
        } else {
            try {
                throw new ServiceException("The patient must have a valid DNI");
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }
        }
                return oMapper.convertValue(patient, PatientDto.class);
    }

    @Override
    public PatientDto searchPatient(Long id) {
        PatientDto patientDto;
        Optional<Patient> patient = patientRepository.findById(id);
        if(patient.isPresent()){
        patientDto = oMapper.convertValue(patient, PatientDto.class);
        } else {
            try {
                throw new ServiceException("Provide a valid Id");
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }
        }
        return patientDto;
    }

    @Override
    public PatientDto searchByNameAndLastName(String name, String lastName) {
        PatientDto patientDto;
        Optional<Patient> patient = patientRepository.findByNameAndLastName(name, lastName);
        if(patient.isPresent()){
            patientDto = oMapper.convertValue(patient, PatientDto.class);
        } else {
            try {
                logger.error("Patient not found");
                throw new ServiceException("Provide a valid Name and Lastname");
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }
        }
        return patientDto;
    }
    @Override
    public PatientDto updatePatient(PatientDto patientDto) {
        return addPatient(patientDto);
    }
    @Override
    public void deletePatient(Long id) throws ServiceException {
        if(id != null) {
            patientRepository.deleteById(id);
        }else{
            logger.error("Patient not found");
            throw new ServiceException("The patient id doesn't exist");
        }
    }

    @Override
    public Set<PatientDto> showAll() {
        List<Patient> patients = patientRepository.findAll();
        Set<PatientDto> patientsDTO = new HashSet<>();

        if(!(patients.isEmpty())) {
            for (Patient patient : patients) {
                patientsDTO.add(oMapper.convertValue(patient, PatientDto.class));
            }
        }else {
            try {
                logger.error("Patients not found");
                throw new ServiceException("There are no patients registered yet");
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }
        }
        return patientsDTO;
    }
}
