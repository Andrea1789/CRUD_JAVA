package com.odontology.dh.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientDto implements Dto {

    private Long idPatient;
    private String name;
    private String lastName;
    private Date discharge;
    private Long dni;
    private AddressDto address;
    private Set<AppointmentDto> appointments;

}
