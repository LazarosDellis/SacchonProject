package gr.codehub.SacchonProjectPfizer.repository;

import gr.codehub.SacchonProjectPfizer.model.Doctor;
import gr.codehub.SacchonProjectPfizer.model.Measurement;

import javax.persistence.EntityManager;


public class MeasurementRepository extends Repository<Measurement, Integer>{



    private EntityManager entityManager;
    public MeasurementRepository(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Class<Measurement> getEntityClass() {
        return Measurement.class;
    }

    @Override
    public String getClassName() {
        return Measurement.class.getName();
    }

    public Measurement getById(int measurementId){
        return entityManager.createQuery("SELECT * FROM Doctor", Measurement.class)
                .setParameter("measurementId", measurementId)
                .getSingleResult();
    }
}
