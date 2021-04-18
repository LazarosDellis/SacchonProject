package gr.codehub.SacchonProjectPfizer.resource;

import gr.codehub.SacchonProjectPfizer.exception.AuthorizationException;
import gr.codehub.SacchonProjectPfizer.jpaUtil.JpaUtil;
import gr.codehub.SacchonProjectPfizer.model.Consultation;
import gr.codehub.SacchonProjectPfizer.repository.ConsultationRepository;
import gr.codehub.SacchonProjectPfizer.representation.ConsultationRepresentation;

import gr.codehub.SacchonProjectPfizer.security.Shield;
import org.restlet.resource.*;

import javax.persistence.EntityManager;

public class ConsultationResource extends ServerResource {

    private int id;

    @Override
    protected void doInit() {
        id = Integer.parseInt(getAttribute("id"));
    }

    @Get("json") // get
    public ApiResult<ConsultationRepresentation> getConsultation() {

        //authorisation check
        try {
            ResourceUtils.checkRole(this, Shield.ROLE_USER);
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

    @Post("json")// create
    public ApiResult<ConsultationRepresentation> add(ConsultationRepresentation consultationRepresentationIn) {

        //authorisation check
        try {
            ResourceUtils.checkRole(this, Shield.ROLE_DOCTOR);
        } catch (AuthorizationException e) {
            return new ApiResult<>(null, 500, e.getMessage());
        }

        if (consultationRepresentationIn == null) return null;
        if (consultationRepresentationIn.getConsult() == null) return null;

        Consultation consultation = consultationRepresentationIn.createConsultation();
        EntityManager em = JpaUtil.getEntityManager();
        ConsultationRepository consultationRepository = new ConsultationRepository(em);
        consultationRepository.save(consultation);
        ConsultationRepresentation p = new ConsultationRepresentation(consultation);
        return new ApiResult<>(p, 200, "ok");
    }


    @Put("json")//update
    public ApiResult<ConsultationRepresentation> putConsultation(ConsultationRepresentation consultationRepresentation) {

        //authorisation check
        try {
            ResourceUtils.checkRole(this, Shield.ROLE_DOCTOR);
        } catch (AuthorizationException e) {
            return new ApiResult<>(null, 500, e.getMessage());
        }

        EntityManager em = JpaUtil.getEntityManager();
        ConsultationRepository consultationRepository = new ConsultationRepository(em);
        Consultation consultation = consultationRepository.read(id);

        consultation.setConsult(consultationRepresentation.getConsult());
        consultation.setDate(consultationRepresentation.getDate());

        consultationRepository.save(consultation);

        ConsultationRepresentation consultationRepresentation2 = new ConsultationRepresentation(consultation);
        em.close();
        return new ApiResult<>(consultationRepresentation2, 200, "ok");

    }


    @Delete("txt")
    public boolean deleteConsultation() {
        EntityManager em = JpaUtil.getEntityManager();
        ConsultationRepository consultationRepository = new ConsultationRepository(em);
        return consultationRepository.delete(id);
    }

}
