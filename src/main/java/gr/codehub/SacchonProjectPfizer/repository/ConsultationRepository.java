package gr.codehub.SacchonProjectPfizer.repository;

import gr.codehub.SacchonProjectPfizer.model.Consultation;
import gr.codehub.SacchonProjectPfizer.model.Patient;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ConsultationRepository extends Repository<Consultation, Integer>{

    private EntityManager entityManager;

    public ConsultationRepository(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Class<Consultation> getEntityClass() {
        return Consultation.class;
    }

    @Override
    public String getClassName() {
        return Consultation.class.getName();
    }

    public Consultation getByConsult(String consult){
        return entityManager.createQuery("SELECT b FROM Consultation b WHERE b.consult = :consult", Consultation.class)
                .setParameter("consultation", consult)
                .getSingleResult();
    }

    public Patient getPatient(int consultationId){
        return  entityManager.createQuery("SELECT patient FROM consultation c inner join  where c.id = : consultationId ",
                Patient.class)
                .setParameter("consultationId", consultationId)
                .getSingleResult();
    }




}
