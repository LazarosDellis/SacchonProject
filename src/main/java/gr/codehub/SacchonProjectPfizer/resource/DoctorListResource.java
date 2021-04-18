package gr.codehub.SacchonProjectPfizer.resource;

import gr.codehub.SacchonProjectPfizer.exception.AuthorizationException;
import gr.codehub.SacchonProjectPfizer.jpaUtil.JpaUtil;
import gr.codehub.SacchonProjectPfizer.model.Doctor;
import gr.codehub.SacchonProjectPfizer.model.Patient;
import gr.codehub.SacchonProjectPfizer.repository.DoctorRepository;
import gr.codehub.SacchonProjectPfizer.repository.PatientRepository;
import gr.codehub.SacchonProjectPfizer.representation.DoctorRepresentation;
import gr.codehub.SacchonProjectPfizer.representation.PatientRepresentation;
import gr.codehub.SacchonProjectPfizer.security.Shield;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class DoctorListResource  extends ServerResource  {


    @Get("json")
    public ApiResult<List<PatientRepresentation>> getPatient(){

        //authorisation check
        try {
            ResourceUtils.checkRole(this, Shield.ROLE_DOCTOR);
        } catch (AuthorizationException e) {
            return new ApiResult<>(null, 500, e.getMessage());
        }


        EntityManager em = JpaUtil.getEntityManager();
        PatientRepository patientRepository = new PatientRepository(em);
        List<Patient> patients = patientRepository.findAll();
        em.close();
        List<PatientRepresentation> patientRepresentationList =
                patients.stream()
                        .map( p-> new PatientRepresentation(p))
                        .collect(toList());

        return new ApiResult<>(patientRepresentationList, 200, "ok");
    }

//    @Post("json") //create
//    public ApiResult<PatientRepresentation> add(PatientRepresentation patientRepresentationIn){
//
//        //authorisation check
//        try {
//            ResourceUtils.checkRole(this, Shield.ROLE_DOCTOR);
//        } catch (AuthorizationException e) {
//            return new ApiResult<>(null, 500, e.getMessage());
//        }
//
//        if (patientRepresentationIn ==null) return null;
//        if (patientRepresentationIn.getFullName() == null) return null;
//
//        Patient patient = patientRepresentationIn.createPatient();
//        EntityManager em = JpaUtil.getEntityManager();
//        PatientRepository patientRepository = new PatientRepository(em);
//        patientRepository.save(patient);
//        PatientRepresentation p = new PatientRepresentation(patient);
//        return p;
//    }

}
