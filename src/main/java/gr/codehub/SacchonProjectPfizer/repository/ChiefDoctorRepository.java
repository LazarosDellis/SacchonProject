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
    //, Date from, Date to
    //and m.date > : from and m.date < : to
    public List<Measurement> getMeasurements(int patientId) {
        return entityManager.createQuery("SELECT m FROM Measurement m inner join Patient p  where p.id = : patientId   ",
                Measurement.class)
                .setParameter("patientId", patientId)
//                .setParameter("from", from)
//                .setParameter("to", to)
                .getResultList();
    }



//    public List<Patient> patients = (List<Patient>)entityManager.createNativeQuery("select patient.* from patient left join (select * from consultation where date> dateadd(day,-30, getdate() ) ) consultation30" +
//            " on consultation30.patient_id =patient.id where consultation30.id is null")
//            .getResultList();

//3. The list of the patients who are waiting for a consultation and the time elapsed since they needed to have one

    public List<Patient> getPatientsWithNoConsultation(Date date) {
        return (List<Patient>) entityManager.createNativeQuery(" select patient.* from patient left join (select * from consultation where date> dateadd(day,-30, getdate() ) ) consultation30" +
                "    on consultation30.patient_id =patient.id" +
                "    where consultation30.id is null", Patient.class)
                .setParameter("date", date)
                .getResultList();

    }

    //   4. The list of the patients with no activity over a time range
    public List<Patient> getActivityMeasurements(Date from, Date to) {
        return entityManager.createQuery("select p from Patient p inner join Measurement m where p.id =: patientId and m.date > :from and m.date < : to ",
                Patient.class)
                .setParameter("from", from)
                .setParameter("to", to)
                .getResultList();
    }

   // 5. The list of the doctors with no activity over a time range

    public List<Doctor> getActivityConsults(Date from, Date to) {
        return entityManager.createQuery("select d from Doctor d inner join Consultation c where d.id =: doctorId and  c.date > :from and c.date < : to  ",
                Doctor.class)
                .setParameter("from", from)
                .setParameter("to", to)
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
