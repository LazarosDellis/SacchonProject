package gr.codehub.SacchonProjectPfizer.repository;

import gr.codehub.SacchonProjectPfizer.model.Consultation;

import javax.persistence.EntityManager;

public class ConsultationRepository extends Repository<Consultation, Integer>{
    public ConsultationRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Consultation> getEntityClass() {
        return null;
    }

    @Override
    public String getClassName() {
        return null;
    }
}
