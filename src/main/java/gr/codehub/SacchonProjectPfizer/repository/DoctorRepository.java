package gr.codehub.SacchonProjectPfizer.repository;

import gr.codehub.SacchonProjectPfizer.model.Consultation;
import gr.codehub.SacchonProjectPfizer.model.Doctor;
import gr.codehub.SacchonProjectPfizer.model.Measurement;
import gr.codehub.SacchonProjectPfizer.model.Patient;

import javax.persistence.EntityManager;
import javax.print.Doc;
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
    public Doctor getByUsername(String username ) {
      try{  return entityManager.createQuery("SELECT d  FROM Doctor d " +
                "WHERE d.username = : username", Doctor.class)
                .setParameter("username", username)
                .getSingleResult();}
      catch (Exception e){
          return null;
      }
    }
    public List<Patient> getPatient(int doctorId){
        return entityManager.createQuery("SELECT p  FROM Patient p WHERE p.id = :patientId",
                Patient.class)
                .setParameter("doctorId", doctorId)
                .getResultList();
    }
//DOCTOR 2.
    public List<Measurement> getPatientData(int patientId) {
        return entityManager.createQuery(" SELECT m, c FROM Measurement m inner join Patient p Consultation c  where m.id = :patientId and c. ",
                Measurement.class)
                .setParameter("patientId", patientId)
                .getResultList();

    }
//DOCTOR 2.
    public List<Consultation> getConsultations(int patientId) {
       return entityManager.createQuery("SELECT c FROM Consultation c inner join Patient p where p.id = :patientId ",
                Consultation.class)
                .setParameter("patientId", patientId)
                .getResultList();

    }




//    public List<Patient> getPatientsWithNoDoctor(){
//        return entityManager.createQuery("SELECT p FROM Patient p inner join Doctor d where d.id IS NUll ",
//                Patient.class)
//                .getResultList();
//
//    }

    public List<Patient> getMinePatientsWithNoConsult(){
        return entityManager.createQuery("SELECT p FROM Patient p inner join Consultation c where current(date) - max(consultation.date) ",
                Patient.class)
               .getResultList();

    }

    public List<Doctor> getDoctors(){
        return entityManager.createQuery("SELECT d FROM Doctor d", Doctor.class)
                .getResultList();
    }

    // 5. The list of the doctors with no activity over a time range

    public List<Doctor> getActivityConsultations(Date from, Date to) {
        return entityManager.createNativeQuery("SELECT * FROM Doctor WHERE Doctor.id NOT IN(SELECT Doctor.id FROM Doctor " +
                        "Left JOIN Consultation " +
                        "ON Doctor.id = Consultation.doctor_id " +
                        "WHERE Consultation.date > :from " +
                        "AND Consultation.date < :to) ",
                Doctor.class)
                .setParameter("from", from)
                .setParameter("to", to)
                .getResultList();
    }


}
