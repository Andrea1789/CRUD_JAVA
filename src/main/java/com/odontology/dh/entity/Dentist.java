package com.odontology.dh.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "dentists")
public class Dentist {

    @Id
    @Column(name = "id_dentist")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idDentist;
    private Long registration;
    private String name;
    private String lastName;

    @OneToMany(mappedBy = "dentist", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Appointment> appointments = new HashSet<>();


}