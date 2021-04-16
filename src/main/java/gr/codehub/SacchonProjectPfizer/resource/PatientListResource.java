package gr.codehub.SacchonProjectPfizer.resource;

import gr.codehub.SacchonProjectPfizer.jpaUtil.JpaUtil;
import gr.codehub.SacchonProjectPfizer.model.Consultation;
import gr.codehub.SacchonProjectPfizer.model.Measurement;
import gr.codehub.SacchonProjectPfizer.model.Patient;
import gr.codehub.SacchonProjectPfizer.repository.ConsultationRepository;
import gr.codehub.SacchonProjectPfizer.repository.PatientRepository;
import gr.codehub.SacchonProjectPfizer.representation.ConsultationRepresentation;
import gr.codehub.SacchonProjectPfizer.representation.PatientRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

import javax.persistence.EntityManager;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class PatientListResource {

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
