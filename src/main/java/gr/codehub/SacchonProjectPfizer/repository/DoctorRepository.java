package gr.codehub.SacchonProjectPfizer.repository;

import gr.codehub.SacchonProjectPfizer.model.Doctor;
import gr.codehub.SacchonProjectPfizer.model.Patient;

import javax.persistence.EntityManager;

public class DoctorRepository  extends Repository<Doctor, Integer>{

    public DoctorRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Doctor> getEntityClass() {
        return null;
    }

    @Override
    public String getClassName() {
        return null;
    }
}
