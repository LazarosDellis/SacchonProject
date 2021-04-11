package gr.codehub.SacchonProjectPfizer.repository;

import gr.codehub.SacchonProjectPfizer.model.ChiefDoctor;

import javax.persistence.EntityManager;

public class ChiefDoctorRepository extends Repository<ChiefDoctor, Integer>{

    public ChiefDoctorRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<ChiefDoctor> getEntityClass() {
        return null;
    }

    @Override
    public String getClassName() {
        return null;
    }
}
