package gr.codehub.SacchonProjectPfizer.resource;

import gr.codehub.SacchonProjectPfizer.exception.AuthorizationException;
import gr.codehub.SacchonProjectPfizer.jpaUtil.JpaUtil;
import gr.codehub.SacchonProjectPfizer.model.Consultation;
import gr.codehub.SacchonProjectPfizer.model.Measurement;
import gr.codehub.SacchonProjectPfizer.repository.ConsultationRepository;
import gr.codehub.SacchonProjectPfizer.repository.MeasurementRepository;
import gr.codehub.SacchonProjectPfizer.representation.ConsultationRepresentation;
import gr.codehub.SacchonProjectPfizer.representation.MeasurementRepresentation;
import gr.codehub.SacchonProjectPfizer.security.Shield;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;

public class GetOneConsultationResource extends ServerResource {


    private int id;

    @Override
    protected void doInit() {
        id = Integer.parseInt(getAttribute("id"));
    }



    @Get("json")
    public ApiResult<ConsultationRepresentation> getConsultation() {

        //authorisation check
        try {
            ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);
            // efboleumena try
        } catch (AuthorizationException e) {
            return new ApiResult<>(null, 500, e.getMessage());
        }


        EntityManager em = JpaUtil.getEntityManager();
        ConsultationRepository consultationRepository = new ConsultationRepository(em);
        Consultation consultation = consultationRepository.read(id);
        ConsultationRepresentation consultationRepresentation2 = new ConsultationRepresentation(consultation);
        em.close();
        return new ApiResult<>(consultationRepresentation2, 200, "ok");

    }
}
