package gr.codehub.SacchonProjectPfizer.resource;

import gr.codehub.SacchonProjectPfizer.jpaUtil.JpaUtil;
import gr.codehub.SacchonProjectPfizer.model.Consultation;
import gr.codehub.SacchonProjectPfizer.repository.ConsultationRepository;
import gr.codehub.SacchonProjectPfizer.representation.ConsultationRepresentation;

import org.restlet.resource.*;

import javax.persistence.EntityManager;

public class ConsultationResource extends ServerResource {

    private int id;

    @Override
    protected void doInit() {
        id = Integer.parseInt(getAttribute("id"));
    }

    @Get("json")
    public ConsultationRepresentation getConsultation() {
        EntityManager em = JpaUtil.getEntityManager();
        ConsultationRepository consultationRepository = new ConsultationRepository(em);
        Consultation consultation = consultationRepository.read(id);
        ConsultationRepresentation consultationRepresentation2 = new ConsultationRepresentation(consultation);
        em.close();
        return consultationRepresentation2;

    }

    @Post("json")
    public ConsultationRepresentation add(ConsultationRepresentation consultationRepresentationIn){

        if (consultationRepresentationIn ==null) return null;
        if (consultationRepresentationIn.getConsult() == null) return null;

        Consultation consultation = consultationRepresentationIn.createConsultation();
        EntityManager em = JpaUtil.getEntityManager();
        ConsultationRepository consultationRepository = new ConsultationRepository(em);
        consultationRepository.save(consultation);
        ConsultationRepresentation p = new ConsultationRepresentation(consultation);
        return p;
    }


    @Put("json")
    public ConsultationRepresentation putConsultation(ConsultationRepresentation consultationRepresentation) {

        EntityManager em = JpaUtil.getEntityManager();
        ConsultationRepository consultationRepository = new ConsultationRepository(em);
        Consultation consultation = consultationRepository.read(id);

        consultation.setConsult(consultationRepresentation.getConsult());
        consultation.setDate(consultationRepresentation.getDate());

        consultationRepository.save(consultation);

        ConsultationRepresentation consultationRepresentation2 = new ConsultationRepresentation(consultation);
        em.close();
        return consultationRepresentation2;

    }





    @Delete("txt")
    public boolean deleteConsultation() {
        EntityManager em = JpaUtil.getEntityManager();
       ConsultationRepository consultationRepository = new ConsultationRepository(em);
        return consultationRepository.delete(id);
    }

}
