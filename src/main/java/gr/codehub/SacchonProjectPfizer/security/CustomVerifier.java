package gr.codehub.SacchonProjectPfizer.security;

import gr.codehub.SacchonProjectPfizer.jpaUtil.JpaUtil;
import gr.codehub.SacchonProjectPfizer.model.ChiefDoctor;
import gr.codehub.SacchonProjectPfizer.model.Doctor;
import gr.codehub.SacchonProjectPfizer.model.Patient;
import gr.codehub.SacchonProjectPfizer.repository.ChiefDoctorRepository;
import gr.codehub.SacchonProjectPfizer.repository.DoctorRepository;
import gr.codehub.SacchonProjectPfizer.repository.PatientRepository;
import org.restlet.Request;
import org.restlet.security.Role;
import org.restlet.security.SecretVerifier;

import javax.persistence.EntityManager;
import javax.print.Doc;

public class CustomVerifier extends SecretVerifier {

    @Override
    public int verify(String username, char[] password) {
        //check db for user
        EntityManager em = JpaUtil.getEntityManager();
        PatientRepository patientRepository = new PatientRepository(em);
        DoctorRepository doctorRepository = new DoctorRepository(em);
        ChiefDoctorRepository chiefDoctorRepository = new ChiefDoctorRepository(em);

        Patient patient = patientRepository.getByUsername(username);
        if (patient != null) {
            String passwordInDb = patient.getPassword();
            if (compare(passwordInDb.toCharArray(), password)) {
                Request request = Request.getCurrent();
                request.getClientInfo().getRoles().add
                        (new Role(patient.getRole()));
                return SecretVerifier.RESULT_VALID;
            }
            return SecretVerifier.RESULT_INVALID;
        }
        Doctor doctor = doctorRepository.getByUsername(username);
        if (doctor != null) {
            String passwordInDb = doctor.getPassword();
            if (compare(passwordInDb.toCharArray(), password)) {
                Request request = Request.getCurrent();
                request.getClientInfo().getRoles().add
                        (new Role(doctor.getRole()));
                return SecretVerifier.RESULT_VALID;
            }
            return SecretVerifier.RESULT_INVALID;
        }

        ChiefDoctor chiefDoctor = chiefDoctorRepository.getByUsername(username);
        if (chiefDoctor != null) {
            String passwordInDb = chiefDoctor.getPassword();
            if (compare(passwordInDb.toCharArray(), password)) {
                Request request = Request.getCurrent();
                request.getClientInfo().getRoles().add
                        (new Role(chiefDoctor.getRole()));
                return SecretVerifier.RESULT_VALID;
            }
            return SecretVerifier.RESULT_INVALID;
        }
        return SecretVerifier.RESULT_INVALID;

    }


}