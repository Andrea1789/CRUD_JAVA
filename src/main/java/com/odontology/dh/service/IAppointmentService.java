package com.odontology.dh.service;

import com.odontology.dh.dto.AppointmentDto;

import java.util.Date;
import java.util.List;
import java.util.Set;


public interface IAppointmentService {

    AppointmentDto addAppointment(Long idPatient, Long idDentist, String appointmentDate) throws ServiceException;
    AppointmentDto searchAppointment(Long id);

    List<AppointmentDto> searchByDate(String date);
    AppointmentDto updateAppointment(Long idPatient, Long idDentist, String appointmentDate) throws ServiceException;
    void deleteAppointment(Long id) throws ServiceException;
    Set<AppointmentDto> showAll();
}
