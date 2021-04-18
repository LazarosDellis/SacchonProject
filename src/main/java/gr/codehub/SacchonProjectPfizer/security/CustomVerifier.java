package gr.codehub.SacchonProjectPfizer.security;

import gr.codehub.SacchonProjectPfizer.jpaUtil.JpaUtil;
import gr.codehub.SacchonProjectPfizer.model.Doctor;
import gr.codehub.SacchonProjectPfizer.model.Patient;
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
        PatientRepository patientRepository =new PatientRepository(em);

       Patient patient = patientRepository.getByUsername(username);
        if (patient==null)
            return SecretVerifier.RESULT_INVALID;
        String passwordInDb = patient.getPassword();
        if (compare(passwordInDb.toCharArray(), password) ) {
            Request request = Request.getCurrent();
            request.getClientInfo().getRoles().add
                    (new Role(   patient.getRole()  ));
            return SecretVerifier.RESULT_VALID;
        }
        return SecretVerifier.RESULT_INVALID;
    }


//    public int verify(String username, char[] password) {
//        //check db for user
//        EntityManager em = JpaUtil.getEntityManager();
//        DoctorRepository doctorRepository =new DoctorRepository(em);
//
//        Doctor doctor = doctorRepository.getByUsername(username);
//        if (doctor==null)
//            return SecretVerifier.RESULT_INVALID;
//        String passwordInDb = doctor.getPassword();
//        if (compare(passwordInDb.toCharArray(), password) ) {
//            Request request = Request.getCurrent();
//            request.getClientInfo().getRoles().add
//                    (new Role(   doctor.getRole()  ));
//            return SecretVerifier.RESULT_VALID;
//        }
//        return SecretVerifier.RESULT_INVALID;
//    }


}