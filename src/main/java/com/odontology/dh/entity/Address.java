package com.odontology.dh.entity;

import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
public class Address {

    @Id
    @Column(name = "id_address")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private int number;
    private String street;
    private String city;
    private String state;
}
