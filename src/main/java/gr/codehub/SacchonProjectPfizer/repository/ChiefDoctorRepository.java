package gr.codehub.SacchonProjectPfizer.repository;

import gr.codehub.SacchonProjectPfizer.model.ChiefDoctor;
import gr.codehub.SacchonProjectPfizer.model.Doctor;
import gr.codehub.SacchonProjectPfizer.model.Patient;

import javax.persistence.EntityManager;
import java.util.List;

public class ChiefDoctorRepository extends Repository<ChiefDoctor, Integer>{

    private EntityManager entityManager;

    public ChiefDoctorRepository(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Class<ChiefDoctor> getEntityClass() {
        return ChiefDoctor.class;
    }

    @Override
    public String getClassName() {
        return ChiefDoctor.class.getName();
    }

    public ChiefDoctor getById(int chiefDoctorId){
        return entityManager.createQuery("SELECT b FROM ChiefDoctor", ChiefDoctor.class)
                .setParameter("chiefDoctorId", chiefDoctorId)
                .getSingleResult();
    }

    public List<Patient> getPatient(int doctorId){
        return entityManager.createQuery("SELECT *  FROM Patient  ",
                Patient.class)
                .setParameter("doctorId", doctorId)
                .getResultList();
    }

    public List<Doctor> getDoctor(int chiefDoctorId){
        return entityManager.createQuery("SELECT *  FROM Doctor  ",
                Doctor.class)
                .setParameter("chiefDoctorId", chiefDoctorId)
                .getResultList();
    }
}
