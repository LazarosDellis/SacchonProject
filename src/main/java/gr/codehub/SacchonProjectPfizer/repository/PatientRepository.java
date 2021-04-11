package gr.codehub.SacchonProjectPfizer.repository;

import gr.codehub.SacchonProjectPfizer.model.Patient;

import javax.persistence.EntityManager;

public class PatientRepository  extends Repository<Patient, Integer> {

    public PatientRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Patient> getEntityClass() {
        return null;
    }

    @Override
    public String getClassName() {
        return null;
    }
}
