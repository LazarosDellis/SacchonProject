package gr.codehub.SacchonProjectPfizer.repository;

import gr.codehub.SacchonProjectPfizer.model.*;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

public class ChiefDoctorRepository extends Repository<ChiefDoctor, Integer> {

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
    // BIRD
    public ChiefDoctor getById(int chiefDoctorId) {
        return entityManager.createQuery("SELECT b FROM ChiefDoctor", ChiefDoctor.class)
                .setParameter("chiefDoctorId", chiefDoctorId)
                .getSingleResult();
    }
    //1. The information submissions (personal monitor data) of a patient over a time range

    public List<Measurement> getMeasurements(String fullName) {
        return entityManager.createQuery("SELECT m FROM Measurement m inner join Patient p  where p.id = : patientId ",
                Measurement.class)
                .setParameter("fullName", fullName)
                .getResultList();
    }

    public List<Patient> getPatient() {
        return entityManager.createQuery("SELECT  p FROM Patient ",
                Patient.class)
                .getResultList();
    }

    public Doctor getByDoctorFullName(String fullName) {
        return entityManager.createQuery("SELECT d FROM Doctor WHERE d.fullName = : fullName "
                , Doctor.class)
                .setParameter("fullName", fullName)
                .getSingleResult();
    }

    public List<Doctor> getDoctor() {
        return entityManager.createQuery("SELECT d  FROM Doctor",
                Doctor.class)
                .getResultList();
    }

    public List<Consultation> getConsultations(String fullName) {
        return entityManager.createQuery("SELECT c FROM Consultation c inner join Doctor d  where d.id = : doctorId ",
                Consultation.class)
                .setParameter("fullName", fullName)
                .getResultList();


    }

    public List<Doctor> getActivityConsults(Date from) {
        return entityManager.createQuery("select d from Doctor d inner join Consultation c where d.id =: doctorId and c.date > :from  ",
                Doctor.class)
                .setParameter("from", from)
                .getResultList();
    }

    public List<Patient> getActivityMeasurements(Date from) {
        return entityManager.createQuery("select p from Patient p inner join Measurement m where p.id =: patientId and m.date > :from  ",
                Patient.class)
                .setParameter("from", from)
                .getResultList();
    }


//    public List<Patient> getPatient(int doctorId){
//        return entityManager.createQuery("SELECT  p FROM Patient p inner join Doctor d where d.id = : doctorId ",
//                Patient.class)
//                .setParameter("doctorId", doctorId)
//                .getResultList();
//    }

    // public List<Consultation> getConsultations(int patientId){
//        return  entityManager.createQuery("SELECT  c FROM Consultation c inner join Patient p  where p.id = : patientId ",
//                Consultation.class)
//                .setParameter("patientId", patientId)
//                .getResultList();
//    }

}
