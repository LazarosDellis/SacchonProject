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




    public Consultation getByConsult(int  consultationId){
        return entityManager.createQuery("SELECT b FROM Consultation b WHERE b.consult = :consult", Consultation.class)
                .setParameter("consultationId", consultationId)
                .getSingleResult();
    }

    public List<Consultation> getConsultations(int patientId){
        return  entityManager.createQuery("SELECT  c FROM Consultation c inner join Patient p  where p.id = : patientId ",
                Consultation.class)
                .setParameter("patientId", patientId)
                .getResultList();
    }




}
