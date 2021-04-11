package gr.codehub.SacchonProjectPfizer.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fullName;
    private String username;
    private String email;
    private String password;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Patient> patients;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Consultation> consultations;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ChiefDoctor chiefDoctor;


}
