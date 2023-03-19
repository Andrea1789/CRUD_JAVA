package com.odontology.dh.controller;

import com.odontology.dh.dto.DentistDto;
import com.odontology.dh.service.IDentistService;
import com.odontology.dh.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/dentists")
public class DentistController {

    private final IDentistService dentistService;

    @Autowired
    public DentistController(IDentistService dentistService) {
        this.dentistService = dentistService;
    }

    @PostMapping("/addNew")
    public ResponseEntity<DentistDto> addDentist(@RequestParam Long registration, @RequestParam String name, @RequestParam String lastname){
        return new ResponseEntity<>(dentistService.addDentist(registration, name, lastname), HttpStatus.CREATED);
    }

    @GetMapping("/searchById/{id}")
    public ResponseEntity<DentistDto> searchDentist(@PathVariable Long id){
        return new ResponseEntity<>(dentistService.searchDentist(id), HttpStatus.OK);
    }

    @GetMapping("/searchByRegistration/{registration}")
    public ResponseEntity<DentistDto> searchByRegistration(@PathVariable Long registration) {
        return new ResponseEntity<>(dentistService.searchByRegistration(registration), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<DentistDto> updatePatient(@RequestParam Long registration, @RequestParam String name, @RequestParam String lastname){
        return new ResponseEntity<>(dentistService.updateDentist(registration, name, lastname), HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDentist(@PathVariable Long id) throws ServiceException {
        dentistService.deleteDentist(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/showAll")
    public ResponseEntity<Set<DentistDto>> showAllDentists(){
        return new ResponseEntity<>(dentistService.showAll(), HttpStatus.OK);
    }
}
