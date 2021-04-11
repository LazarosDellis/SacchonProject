package gr.codehub.SacchonProjectPfizer.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
@Data
@Entity
public class ChiefDoctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fullName;
    private String username;
    private String email;
    private String password;


    @OneToMany(mappedBy = "chiefDoctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Doctor>  doctors;

    @OneToMany(mappedBy = "chiefDoctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Patient>  patients;

    @OneToMany(mappedBy = "chiefDoctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Consultation>  consultations;

    @OneToMany(mappedBy = "chiefDoctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Measurement>  measurements;

}
