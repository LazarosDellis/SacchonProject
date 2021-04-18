package gr.codehub.SacchonProjectPfizer.representation;


import gr.codehub.SacchonProjectPfizer.model.ChiefDoctor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
public class ChiefDoctorRepresentation  {
    
        private int id;

        private String fullName;
        private String username;
        private String email;
        private String password;
        private String role;

        private String uri;


        public ChiefDoctorRepresentation(ChiefDoctor chiefDoctor) {
                if (chiefDoctor != null) {
                        fullName = chiefDoctor.getFullName();

                        username = chiefDoctor.getUsername();
                        email = chiefDoctor.getEmail();
                        password = chiefDoctor.getPassword();
                        role = chiefDoctor.getRole();
                        uri =  "http://localhost:9000/v1/chiefDoctor/" + chiefDoctor.getId();
                }

        }

        public ChiefDoctor createChiefDoctor() {
                ChiefDoctor chiefDoctor = new ChiefDoctor();
                chiefDoctor.setFullName(fullName);
                chiefDoctor.setUsername(username);
                chiefDoctor.setEmail(email);
                chiefDoctor.setPassword(password);
                chiefDoctor.setRole(role);
                return chiefDoctor;
        }






}
