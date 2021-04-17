package gr.codehub.SacchonProjectPfizer.repository;

import gr.codehub.SacchonProjectPfizer.model.Consultation;
import gr.codehub.SacchonProjectPfizer.model.Doctor;
import gr.codehub.SacchonProjectPfizer.model.Measurement;
import gr.codehub.SacchonProjectPfizer.model.Patient;

import javax.persistence.EntityManager;
import java.util.Date;
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


    //BIRD
    public Doctor getById(int doctorId){
        return entityManager.createQuery("SELECT d FROM Doctor d", Doctor.class)
                .setParameter("doctorId", doctorId)
                .getSingleResult();
    }

    public List<Patient> getPatient(int doctorId){
        return entityManager.createQuery("SELECT p  FROM Patient p WHERE p.id = :patientId",
                Patient.class)
                .setParameter("doctorId", doctorId)
                .getResultList();
    }

    public List<Measurement> getPatientData(String fullName) {
        return entityManager.createQuery("SELECT m FROM Measurement m inner join Patient p  where p.id = : patientId ",
                Measurement.class)
                .setParameter("fullName", fullName)
                .getResultList();

    }

    public List<Consultation> getConsultations(String fullName) {
       return entityManager.createQuery("SELECT c FROM Consultation c inner join Patient p where p.id = : patientId ",
                Consultation.class)
                .setParameter("fullName", fullName)
                .getResultList();

    }


    public List<Patient> getPatientsWithNoDoctor(){
        return entityManager.createQuery("SELECT p FROM Patient p inner join Doctor d where d.id IS NUll ",
                Patient.class)
                .getResultList();

    }

    public List<Patient> getMinePatientsWithNoConsult(){
        return entityManager.createQuery("SELECT p FROM Patient p inner join Consultation c where current(date) - max(consultation.date) ",
                Patient.class)
               .getResultList();

    }



}
