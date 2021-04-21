package gr.codehub.SacchonProjectPfizer.resource;

import gr.codehub.SacchonProjectPfizer.exception.AuthorizationException;
import gr.codehub.SacchonProjectPfizer.jpaUtil.JpaUtil;
import gr.codehub.SacchonProjectPfizer.model.Consultation;
import gr.codehub.SacchonProjectPfizer.model.Measurement;
import gr.codehub.SacchonProjectPfizer.model.Patient;
import gr.codehub.SacchonProjectPfizer.repository.ConsultationRepository;
import gr.codehub.SacchonProjectPfizer.repository.PatientRepository;
import gr.codehub.SacchonProjectPfizer.representation.ConsultationRepresentation;
import gr.codehub.SacchonProjectPfizer.representation.PatientRepresentation;
import gr.codehub.SacchonProjectPfizer.security.Shield;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class PatientListResource extends ServerResource {

    @Get("json")
    public ApiResult<List<PatientRepresentation>> getPatient(){

//authorisation check

            try {
                ResourceUtils.checkRole(this, Shield.ROLE_DOCTOR);
            } catch (AuthorizationException e1) {
                try{
                    ResourceUtils.checkRole(this, Shield.ROLE_ADMIN);
                }catch (AuthorizationException e2) {
                    return new ApiResult<>(null, 500, e1.getMessage());
                }
            }



        EntityManager em = JpaUtil.getEntityManager();
        PatientRepository patientRepository = new PatientRepository(em);

        List<Patient> patients = null;

          patients = patientRepository.getPatients();
        em.close();
        List<PatientRepresentation> patientRepresentationList =
                patients.stream()
                        .map(PatientRepresentation:: new)
                        .collect(toList());

        return new ApiResult<>(patientRepresentationList, 200, "ok");
    }

    @Post("json")
    public ConsultationRepresentation add(ConsultationRepresentation consultationRepresentation){

//       {
//            try{
//                ResourceUtils.checkRole(this, Shield.ROLE_DOCTOR);
//            }catch (AuthorizationException e2) {
//                return new ApiResult<>(null, 500, e2.getMessage());
//            }
//        }


        if (consultationRepresentation ==null) return null;
        if (consultationRepresentation.getConsult() == null) return null;

        Consultation consultation = consultationRepresentation.createConsultation();
        EntityManager em = JpaUtil.getEntityManager();
        ConsultationRepository consultationRepository = new ConsultationRepository(em);
        consultationRepository.save(consultation);
        ConsultationRepresentation p = new ConsultationRepresentation(consultation);
        return p;
    }


}
