package com.odontology.dh.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DentistDto {
    private Long idDentist;
    private Long registration;
    private String name;
    private String lastName;
    private Set<AppointmentDto> appointments;

}
