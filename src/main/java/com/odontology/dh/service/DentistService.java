package com.odontology.dh.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.odontology.dh.dto.DentistDto;
import com.odontology.dh.entity.Appointment;
import com.odontology.dh.entity.Dentist;
import com.odontology.dh.repository.IDentistRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DentistService implements IDentistService{

    private static final Logger logger = LogManager.getLogger(DentistService.class);


    @Autowired
    IDentistRepository dentistRepository;

    @Autowired
    ObjectMapper oMapper;
    @Override
    public DentistDto addDentist(Long registration, String name, String lastname) {
       Dentist dentist = new Dentist();

       if (registration != null && name != null && lastname != null) {
            dentist.setRegistration(registration);
            dentist.setName(name);
            dentist.setLastName(lastname);
            dentistRepository.save(dentist);
            logger.info("Dentist created successfully");
        } else {
            try {
                logger.error("Submited incorrect information");
                throw new ServiceException("The dentist must have a valid registration number");
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }
        }
        return oMapper.convertValue(dentist, DentistDto.class);
    }

    @Override
    public DentistDto searchDentist(Long id) {
        Optional<Dentist> dentistOpt = dentistRepository.findById(id);
        DentistDto dentistDto;
        if (dentistOpt.isPresent()) {
            logger.info("Dentist was found");
            dentistDto = oMapper.convertValue(dentistOpt, DentistDto.class);
        } else {
            try {
                throw new ServiceException("Provide a valid Id");
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }
        }
        logger.error("Submitted incorrect id");
        return dentistDto;
    }

    @Override
    public DentistDto searchByRegistration(Long registration) {
        DentistDto dentistDto;
        Optional<Dentist> dentist = dentistRepository.findDentistByRegistration(registration);
        if (dentist.isPresent()) {
            logger.info("Dentist was found");
            dentistDto = oMapper.convertValue(dentist, DentistDto.class);
        } else {
            try {
                throw new ServiceException("Provide a valid Registration number");
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }
        }
        logger.error("Submitted incorrect registration number");
        return dentistDto;
    }

    @Override
    public DentistDto updateDentist(Long registration, String name, String lastname) {
        return addDentist(registration, name, lastname);
    }


    @Override
    public void deleteDentist(Long id) throws ServiceException {
        if(id != null) {
            logger.info("Dentist successfully deleted");
            dentistRepository.deleteById(id);
        } else {
            throw new ServiceException("The dentist id doesn't exist");
        }
    }

    @Override
    public Set<DentistDto> showAll() {
        List<Dentist> dentists = dentistRepository.findAll();
        Set<DentistDto> dentistDTO = new HashSet<>();

        if (!(dentists.isEmpty())) {
            for (Dentist dentist : dentists) {
                dentistDTO.add(oMapper.convertValue(dentist, DentistDto.class));
            }
        }else{
                try {
                    throw new ServiceException("There are no Dentists registered yet");
                } catch (ServiceException e) {
                    throw new RuntimeException(e);
                }
            }
            logger.error("There aren't any dentists registered");
            return dentistDTO;
        }
    }

