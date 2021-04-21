package gr.codehub.SacchonProjectPfizer.resource;

import gr.codehub.SacchonProjectPfizer.exception.AuthorizationException;
import gr.codehub.SacchonProjectPfizer.jpaUtil.JpaUtil;
import gr.codehub.SacchonProjectPfizer.model.Consultation;
import gr.codehub.SacchonProjectPfizer.repository.ConsultationRepository;
import gr.codehub.SacchonProjectPfizer.representation.ConsultationRepresentation;
import gr.codehub.SacchonProjectPfizer.security.Shield;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;

public class OneConsultationOfAPatientResource extends ServerResource {

    private int patientId;

    @Override
    protected void doInit() {
         patientId = Integer.parseInt(getAttribute("patientId"));
    }
    @Get("json")
    public ApiResult<ConsultationRepresentation> getConsultation() {

        //authorisation check
        try {
            ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);
        } catch (AuthorizationException e) {
            try {
                ResourceUtils.checkRole(this, Shield.ROLE_DOCTOR);
           } catch (AuthorizationException e1) {
                try{
                   ResourceUtils.checkRole(this, Shield.ROLE_ADMIN);
               }catch (AuthorizationException e2) {
                  return new ApiResult<>(null, 500, e.getMessage());
                }
           }


        }

        EntityManager em = JpaUtil.getEntityManager();



        ConsultationRepository consultationRepository = new ConsultationRepository(em);
        Consultation consultation = consultationRepository.read(patientId);
        ConsultationRepresentation consultationRepresentation2 = new ConsultationRepresentation(consultation);
        em.close();


        return new ApiResult<>(consultationRepresentation2, 200, "ok");

    }
}
