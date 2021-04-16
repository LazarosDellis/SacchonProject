package gr.codehub.SacchonProjectPfizer.repository;

import gr.codehub.SacchonProjectPfizer.model.Consultation;
import gr.codehub.SacchonProjectPfizer.model.Doctor;
import gr.codehub.SacchonProjectPfizer.model.Measurement;
import gr.codehub.SacchonProjectPfizer.model.Patient;

import javax.persistence.EntityManager;
import java.util.List;

public class DoctorRepository  extends Repository<Doctor, Integer>{


    private EntityManager entityManager;


    public DoctorRepository(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Class<Doctor> getEntityClass() {
        return Doctor.class;
    }

    @Override
    public String getClassName() {
        return Doctor.class.getName();
    }

    public Doctor getById(int doctorId){
        return entityManager.createQuery("SELECT * FROM Doctor", Doctor.class)
                .setParameter("doctorId", doctorId)
                .getSingleResult();
    }

    public List<Patient> getPatient(int doctorId){
        return entityManager.createQuery("SELECT *  FROM Patient  WHERE Patient.id = :patientId",
                Patient.class)
                .setParameter("doctorId", doctorId)
                .getResultList();
    }
}
