package com.odontology.dh.controller;

import com.odontology.dh.dto.AppointmentDto;
import com.odontology.dh.service.IAppointmentService;
import com.odontology.dh.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("agenda")
public class AppointmentController {
    private final IAppointmentService appointmentService;

    @Autowired
    public AppointmentController(IAppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/addNew")
    public ResponseEntity<AppointmentDto> addAppointment
            (@RequestParam Long idPatient, @RequestParam Long idDentist, @RequestParam String appointmentDate) throws ServiceException {
        return new ResponseEntity<>(appointmentService.addAppointment(idPatient, idDentist, appointmentDate), HttpStatus.CREATED);
    }

    @GetMapping("/searchById/{id}")
    public ResponseEntity<AppointmentDto> getAppointment(@PathVariable Long id){
        return new ResponseEntity<>(appointmentService.searchAppointment(id), HttpStatus.OK);
    }

    @GetMapping("/searchByDate/{appointmentDate}")
    public List<AppointmentDto> getbyDate(@PathVariable String appointmentDate){
        return appointmentService.searchByDate(appointmentDate);
    }

    @PutMapping("/update")
    public ResponseEntity<AppointmentDto> updateAppointment
            (@RequestParam Long idPatient, @RequestParam Long idDentist, @RequestParam String appointmentDate) throws ServiceException {
        return new ResponseEntity<>(appointmentService.updateAppointment(idPatient, idDentist, appointmentDate), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable Long id) throws ServiceException {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/showAll")
    public ResponseEntity<Set<AppointmentDto>> listAllAppointments(){
            Set<AppointmentDto> appointments = appointmentService.showAll();
            return ResponseEntity.ok(appointments);
    }



}
