package gr.codehub.SacchonProjectPfizer.repository;

import gr.codehub.SacchonProjectPfizer.model.Consultation;
import gr.codehub.SacchonProjectPfizer.model.Doctor;
import gr.codehub.SacchonProjectPfizer.model.Measurement;
import gr.codehub.SacchonProjectPfizer.model.Patient;

import javax.persistence.EntityManager;
import java.util.List;

public class PatientRepository  extends Repository<Patient, Integer> {

    private EntityManager entityManager;

    public PatientRepository(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Class<Patient> getEntityClass() {
        return Patient.class;
    }

    @Override
    public String getClassName() {
        return Patient.class.getName();
    }


    public Patient getById(int patientId){
        return entityManager.createQuery("SELECT *  FROM Patient  ",
                Patient.class)
                .setParameter("patientId", patientId)
                .getSingleResult();
    }



    public List<Measurement> getMeasurement(int patientId){
        return entityManager.createQuery("SELECT *  FROM Patient  WHERE Patient.id = :patientId",
                Measurement.class)
                .setParameter("patientId", patientId)
                .getResultList();
    }

    public List<Consultation> getConsultation(int patientId){
        return entityManager.createQuery("SELECT *  FROM Patient  WHERE Patient.id = :patientId",
                Consultation.class)
                .setParameter("patientId", patientId)
                .getResultList();
    }

}
