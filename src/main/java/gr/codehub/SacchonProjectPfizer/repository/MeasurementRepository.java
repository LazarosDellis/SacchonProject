package gr.codehub.SacchonProjectPfizer.repository;

import gr.codehub.SacchonProjectPfizer.model.Measurement;

import javax.persistence.EntityManager;

public class MeasurementRepository extends Repository<Measurement, Integer>{
    public MeasurementRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Measurement> getEntityClass() {
        return null;
    }

    @Override
    public String getClassName() {
        return null;
    }
}
