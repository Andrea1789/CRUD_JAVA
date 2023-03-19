package com.odontology.dh.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @Column(name = "id_patient")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idPatient;
    private String name;

    @Column(name = "last_name")
    private String lastName;

    private Date discharge;
    private Long dni;

    @OneToOne(targetEntity = Address.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_address")
    private Address address;

    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Appointment> appointments = new HashSet<>();

}
