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
    private List<Measurement> measurement;

    private String uri;

    public PatientRepresentation(Patient patient) {
        if (patient != null) {
            fullName = patient.getFullName();
            username = patient.getUsername();
            email = patient.getEmail();
            password = patient.getPassword();
            measurement = patient.getMeasurements();
            role = patient.getRole();

            uri = "http://localhost:9000/v1/patient/" + patient.getId();

        }
    }

    public Patient createPatient() {
        Patient patient = new Patient();
        patient.setFullName(fullName);

        patient.setUsername(username);
        patient.setEmail(email);
        patient.setPassword(password);
        patient.setMeasurements(measurement);
        patient.setRole(role);
        return patient;
    }
}
