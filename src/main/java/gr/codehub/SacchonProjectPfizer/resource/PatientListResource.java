package gr.codehub.SacchonProjectPfizer.resource;

import gr.codehub.SacchonProjectPfizer.jpaUtil.JpaUtil;
import gr.codehub.SacchonProjectPfizer.model.Consultation;
import gr.codehub.SacchonProjectPfizer.repository.ConsultationRepository;
import gr.codehub.SacchonProjectPfizer.representation.ConsultationRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class PatientListResource extends ServerResource {

    @Get("json")
    public List<ConsultationRepresentation> getConsult(){
        EntityManager em = JpaUtil.getEntityManager();
        ConsultationRepository consultationRepository = new ConsultationRepository(em);
        List<Consultation> consultation = consultationRepository.findAll();
        em.close();
        List<ConsultationRepresentation> consultationRepresentationList =
                consultation.stream()
                        .map( p-> new ConsultationRepresentation(p))
                        .collect(toList());

        return consultationRepresentationList;
    }

    @Post("json")
    public ConsultationRepresentation add(ConsultationRepresentation consultationRepresentationIn){

        if (consultationRepresentationIn ==null) return null;
        if (consultationRepresentationIn.getConsult() == null) return null;

        Consultation consultation = consultationRepresentationIn.createConsultation();
        EntityManager em = JpaUtil.getEntityManager();
        ConsultationRepository consultationRepository = new ConsultationRepository(em);
        consultationRepository.save(consultation);
        return new ConsultationRepresentation(consultation);
    }


}
