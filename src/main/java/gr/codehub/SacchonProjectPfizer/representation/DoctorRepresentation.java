package gr.codehub.SacchonProjectPfizer.representation;


import gr.codehub.SacchonProjectPfizer.model.Doctor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DoctorRepresentation {

    private int id;

    private String fullName;

    private String username;
    private String password;
    private String email;
    private String role;

    private String uri;

    public DoctorRepresentation(Doctor doctor) {
        if (doctor != null) {
            fullName = doctor.getFullName();

            username = doctor.getUsername();
            email = doctor.getEmail();
            password = doctor.getPassword();
            role = doctor.getRole();
            uri =  "http://localhost:9000/v1/doctor/" + doctor.getId();
        }

    }

    public Doctor createDoctor() {
        Doctor doctor = new Doctor();
        doctor.setFullName(fullName);
        doctor.setUsername(username);
        doctor.setEmail(email);
        doctor.setPassword(password);
        doctor.setRole(role);
        return doctor;
    }

}
