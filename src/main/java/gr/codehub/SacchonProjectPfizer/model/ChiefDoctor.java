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




}
