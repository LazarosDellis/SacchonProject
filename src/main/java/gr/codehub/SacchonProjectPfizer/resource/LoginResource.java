package gr.codehub.SacchonProjectPfizer.resource;

import gr.codehub.SacchonProjectPfizer.jpaUtil.JpaUtil;
import gr.codehub.SacchonProjectPfizer.model.ChiefDoctor;
import gr.codehub.SacchonProjectPfizer.model.Doctor;
import gr.codehub.SacchonProjectPfizer.model.Patient;
import gr.codehub.SacchonProjectPfizer.repository.ChiefDoctorRepository;
import gr.codehub.SacchonProjectPfizer.repository.DoctorRepository;
import gr.codehub.SacchonProjectPfizer.repository.PatientRepository;
import gr.codehub.SacchonProjectPfizer.representation.LogInRepresentation;
import gr.codehub.SacchonProjectPfizer.representation.UserRepresentation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;

public class LoginResource extends ServerResource {

    @Post
    public UserRepresentation verify(LogInRepresentation logInRepresentationIn) {// 0 no LogIn , 1 Patient LogIn , 2  Doctor LogIn, 3 ChiefDoctor LogIn
        //check db for user
        EntityManager em = JpaUtil.getEntityManager();
        PatientRepository patientRepository = new PatientRepository(em);
        DoctorRepository doctorRepository = new DoctorRepository(em);
        ChiefDoctorRepository chiefDoctorRepository = new ChiefDoctorRepository(em);



        Patient patient = patientRepository.getByUsername(logInRepresentationIn.getUsername());
        if(patient != null) {
            if(patient.getPassword().equals(logInRepresentationIn.getPassword()))
                return new UserRepresentation(1, patient.getId());
            else return new UserRepresentation(0,0);
        }

        Doctor doctor = doctorRepository.getByUsername(logInRepresentationIn.getUsername());
        if(doctor != null) {
            if(doctor.getPassword().equals(logInRepresentationIn.getPassword()))
                return new UserRepresentation(2, doctor.getId());
            else return new UserRepresentation(0,0);
        }

        ChiefDoctor chiefDoctor = chiefDoctorRepository.getByUsername(logInRepresentationIn.getUsername());
        if(chiefDoctor != null) {
            if(chiefDoctor.getPassword().equals(logInRepresentationIn.getPassword()))
                return new UserRepresentation(3, chiefDoctor.getId());
            else return new UserRepresentation(0,0);
        }


        return new UserRepresentation(0,0);

    }



}