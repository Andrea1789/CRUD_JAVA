package com.odontology.dh.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @Column(name = "appointment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;

    @ManyToOne
    @JoinColumn(name = "id_patient", insertable = false, updatable = false )
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "id_dentist", insertable = false, updatable = false)
    private Dentist dentist;

    private String appointmentDate;

}
