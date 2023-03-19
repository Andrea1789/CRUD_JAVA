package com.odontology.dh.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppointmentDto {

    private Long appointmentId;
    private PatientDto patient;
    private DentistDto dentist;
    private Date appointmentDate;

}
