package gr.codehub.SacchonProjectPfizer.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date date;
    private String typeOfMeasurement;
   // private String unitsOfMeasurement;
    private double valueOfMeasurement;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ChiefDoctor chiefDoctor;



}
