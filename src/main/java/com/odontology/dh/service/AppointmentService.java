package com.odontology.dh.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.odontology.dh.dto.AppointmentDto;
import com.odontology.dh.dto.DentistDto;
import com.odontology.dh.dto.PatientDto;
import com.odontology.dh.entity.Appointment;
import com.odontology.dh.entity.Dentist;
import com.odontology.dh.entity.Patient;
import com.odontology.dh.repository.IAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

@Service
public class AppointmentService implements IAppointmentService {

    private static final Logger logger = LogManager.getLogger(AppointmentService.class);

    @Autowired
    IAppointmentRepository appointmentRepository;

    @Autowired
    ObjectMapper oMapper;

    private final IPatientService patientService;

    private final IDentistService dentistService;


    @Autowired
    public AppointmentService(IPatientService patientService, IDentistService dentistService) {
        this.patientService = patientService;
        this.dentistService = dentistService;
    }

    @Override
    public AppointmentDto addAppointment(Long idPatient, Long idDentist, String appointmentDate) throws ServiceException {

        if (idPatient != null && idDentist != null && appointmentDate != null) {
            PatientDto patientDto = patientService.searchPatient(idPatient);
            DentistDto dentistDto = dentistService.searchDentist(idDentist);

            if (patientDto != null && dentistDto != null) {
                Patient patientEntity = oMapper.convertValue(patientDto, Patient.class);
                Dentist dentistEntity = oMapper.convertValue(dentistDto, Dentist.class);
                Appointment appointment = new Appointment();
                appointment.setPatient(patientEntity);
                appointment.setDentist(dentistEntity);
                appointment.setAppointmentDate(appointmentDate);

                appointmentRepository.save(appointment);
                logger.info("Appointment successfully created");


                return oMapper.convertValue(appointment, AppointmentDto.class);
            }
        }
            throw new ServiceException("Appointment must have all required fields");

    }
    @Override
    public AppointmentDto searchAppointment(Long id) {
        AppointmentDto appointmentDto;
        Optional<Appointment> appointment = appointmentRepository.findById(id);

        if(appointment.isPresent()) {
            appointmentDto = oMapper.convertValue(appointment, AppointmentDto.class);
            logger.info("Appointment was found");
        }else {
            try {
                throw new ServiceException("The appointment id is not valid");
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }
        }
        return appointmentDto;
    }

    @Override
    public List<AppointmentDto> searchByDate(String date) {
        List<Appointment> appointments = (appointmentRepository.findByDate(date));
        List<AppointmentDto> appointmentsDto = new ArrayList<>();

        if(!(appointments.isEmpty())){
        for(Appointment appointment: appointments) {
            appointmentsDto.add(oMapper.convertValue(appointment, AppointmentDto.class));
        }
        }else {
            try {
                throw new ServiceException("There are no appointments registered on that date");
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }
        }
        logger.info("There are not appointments registered on this date");
        return appointmentsDto;

    }

    @Override
    public AppointmentDto updateAppointment(Long idPatient, Long idDentist, String appointmentDate) throws ServiceException {
       return addAppointment(idPatient, idDentist,appointmentDate);
    }

    @Override
    public void deleteAppointment(Long id) throws ServiceException {
        if(id != null) {
            appointmentRepository.deleteById(id);
        }else{
            throw new ServiceException("The appointment id does't exist");
        }
    }

    @Override
    public Set<AppointmentDto> showAll() {
        List<Appointment> appointments = appointmentRepository.findAll();
        Set<AppointmentDto> appointmentsDto = new HashSet<>();

        if (!(appointments.isEmpty())) {
            for (Appointment appointment : appointments) {
                logger.info("Listing all scheduled appointments");
                appointmentsDto.add(oMapper.convertValue(appointment, AppointmentDto.class));
            }
        } else {
            try {
                throw new ServiceException("There are no appointments registered yet");
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }
        }
        return appointmentsDto;
    }
}
