package com.odontology.dh.service;

import com.odontology.dh.dto.DentistDto;
import com.odontology.dh.entity.Appointment;

import java.util.Set;

public interface IDentistService {

    DentistDto addDentist(Long registration, String name, String lastname);
    DentistDto searchDentist(Long id);

    DentistDto searchByRegistration(Long registration);
    DentistDto updateDentist(Long registration, String name, String lastname);
    void deleteDentist(Long id) throws ServiceException;
    Set<DentistDto> showAll();
}
