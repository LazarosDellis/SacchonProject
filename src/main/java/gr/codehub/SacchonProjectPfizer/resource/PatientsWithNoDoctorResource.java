package gr.codehub.SacchonProjectPfizer.resource;

import gr.codehub.SacchonProjectPfizer.jpaUtil.JpaUtil;
import gr.codehub.SacchonProjectPfizer.model.Doctor;
import gr.codehub.SacchonProjectPfizer.model.Patient;
import gr.codehub.SacchonProjectPfizer.repository.DoctorRepository;
import gr.codehub.SacchonProjectPfizer.repository.PatientRepository;
import gr.codehub.SacchonProjectPfizer.representation.PatientRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class PatientsWithNoDoctorResource extends ServerResource {



    @Get("json")
    public ApiResult<List<PatientRepresentation>> getPatient(){


//        //authorisation check
//        try {
//            ResourceUtils.checkRole(this, Shield.ROLE_USER);
//        } catch (AuthorizationException e) {
//            return new ApiResult<>(null, 500, e.getMessage());
//        }


        EntityManager em = JpaUtil.getEntityManager();
        PatientRepository patientRepository = new PatientRepository(em);

        List<Patient> patients = null;

        patients = patientRepository.getPatientsWithNoDoctor();
        em.close();
        List<PatientRepresentation> patientRepresentationList =
                patients.stream()
                        .map(PatientRepresentation:: new)
                        .collect(toList());

        return new ApiResult<>(patientRepresentationList, 200, "ok");
    }


}
