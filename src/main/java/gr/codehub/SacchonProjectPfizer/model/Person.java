package gr.codehub.SacchonProjectPfizer.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public abstract class Person {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fullName;
    @Column(unique = true)
    private String username;
    private String email;
    private String password;
    private String role;
}
