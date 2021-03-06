package gr.codehub.SacchonProjectPfizer.representation;

import gr.codehub.SacchonProjectPfizer.model.Measurement;
import gr.codehub.SacchonProjectPfizer.model.Patient;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PatientRepresentation {
    private int id;

    private String fullName;

    private String username;
    private String password;
    private String email;
    private String role;
    //private List<Measurement> measurement;
   private int doctorId;
    private String uri;

    public PatientRepresentation(Patient patient) {
        if (patient != null) {
            fullName = patient.getFullName();
            id = patient.getId();
            username = patient.getUsername();
            email = patient.getEmail();
            password = patient.getPassword();

            role = patient.getRole();


        }
    }

    public Patient createPatient() {
        Patient patient = new Patient();
        patient.setFullName(fullName);

        patient.setUsername(username);
        patient.setEmail(email);
        patient.setPassword(password);

        patient.setRole(role);
        return patient;
    }
}
