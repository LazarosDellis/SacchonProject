package gr.codehub.SacchonProjectPfizer.resource;

import gr.codehub.SacchonProjectPfizer.jpaUtil.JpaUtil;
import gr.codehub.SacchonProjectPfizer.model.Consultation;
import gr.codehub.SacchonProjectPfizer.repository.ConsultationRepository;
import gr.codehub.SacchonProjectPfizer.representation.ConsultationRepresentation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;

public class ConsultationListResource extends ServerResource {


    @Post("json")
    public ConsultationRepresentation add(ConsultationRepresentation consultationRepresentation){

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
