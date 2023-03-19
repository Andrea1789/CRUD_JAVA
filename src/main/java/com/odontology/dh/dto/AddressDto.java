package com.odontology.dh.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressDto implements Dto{

    private Long id;
    private int number;
    private String street;
    private String city;
    private String state;
}
