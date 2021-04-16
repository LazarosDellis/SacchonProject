package gr.codehub.SacchonProjectPfizer.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date date;
    private String consult;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Patient patient;



    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Doctor doctor;
}
