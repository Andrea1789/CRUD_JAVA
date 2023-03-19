package com.odontology.dh.controller;

import com.odontology.dh.dto.PatientDto;
import com.odontology.dh.service.IPatientService;
import com.odontology.dh.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("patients")
public class PatientController {
    private final IPatientService patientService;

    @Autowired
    public PatientController(IPatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/addNew")
    public ResponseEntity<PatientDto> addPatient(@RequestBody PatientDto patientDto){
        return new ResponseEntity<>(patientService.addPatient(patientDto), HttpStatus.CREATED);
    }

    @GetMapping("/searchById/{id}")
    public ResponseEntity<PatientDto> getPatient(@PathVariable Long id) {
        return new ResponseEntity<>(patientService.searchPatient(id), HttpStatus.OK);
    }

    @GetMapping("/searchByName")
    public ResponseEntity<PatientDto> searchByNameAndLastName(@RequestParam String name, @RequestParam String lastName){
        return new ResponseEntity<>(patientService.searchByNameAndLastName(name, lastName), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<PatientDto> updatePatient(@RequestBody PatientDto patientDto){
        return new ResponseEntity<>(patientService.updatePatient(patientDto), HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable Long id) throws ServiceException {
        patientService.deletePatient(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/showAll")
    public ResponseEntity<Set<PatientDto>> displayAllPatients(){
        Set<PatientDto> result = patientService.showAll();
        return ResponseEntity.ok(result);
    }

}
