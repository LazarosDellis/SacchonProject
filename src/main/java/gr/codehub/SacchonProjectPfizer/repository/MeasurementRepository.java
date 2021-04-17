package gr.codehub.SacchonProjectPfizer.repository;

import gr.codehub.SacchonProjectPfizer.model.Consultation;
import gr.codehub.SacchonProjectPfizer.model.Doctor;
import gr.codehub.SacchonProjectPfizer.model.Measurement;

import javax.persistence.EntityManager;
import java.util.List;


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



    public List<Measurement> getMeasurements(int patientId){
        return  entityManager.createQuery("SELECT  m FROM Measurement m inner join Patient p  where p.id = : patientId ",
                Measurement.class)
                .setParameter("patientId", patientId)
                .getResultList();
    }
}
