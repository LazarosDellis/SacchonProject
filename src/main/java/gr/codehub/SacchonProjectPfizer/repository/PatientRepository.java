package gr.codehub.SacchonProjectPfizer.repository;

import gr.codehub.SacchonProjectPfizer.model.Consultation;
import gr.codehub.SacchonProjectPfizer.model.Doctor;
import gr.codehub.SacchonProjectPfizer.model.Measurement;
import gr.codehub.SacchonProjectPfizer.model.Patient;

import javax.persistence.EntityManager;
import java.util.Date;
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

    //BIRD
    public Patient getByUsername(String username ){
       try{ return entityManager.createQuery("SELECT p  FROM Patient p " +
                "WHERE p.username = :username",Patient.class)
                .setParameter("username", username)
                .getSingleResult();}
       catch (Exception e){
           return null;
       }
    }

    public List<Patient> getPatients(){
        return entityManager.createQuery("SELECT p FROM Patient p", Patient.class)
                .getResultList();
    }


//2.
    public List<Double> getAvgOfMeasurements(int patientId, Date from1, Date to){
        return entityManager.createQuery("SELECT AVG(m.valueOfMeasurement)  FROM Measurement m WHERE m.patient.id = : patientId and  m.date >= :from1 and m.date <= :to GROUP BY m.typeOfMeasurement " +
                        " ORDER BY m.typeOfMeasurement" ,
                Double.class)
                .setParameter("patientId", patientId)
                .setParameter("from1", from1)
                .setParameter("to", to)
                .getResultList();

    }




    // CHIEFDOCTOR  4. The list of the patients with no activity over a time range
    public List<Patient> getActivityMeasurements(Date from, Date to) {
        return entityManager.createNativeQuery("SELECT * FROM Patient WHERE Patient.id NOT IN(SELECT Patient.id FROM Patient " +
                        "Left JOIN Measurement " +
                        "ON Patient.id = Measurement.patient_id " +
                        "WHERE Measurement.date > :from " +
                        "AND Measurement.date < :to) ",
                Patient.class)
                .setParameter("from", from)
                .setParameter("to", to)
                .getResultList();
    }


    //DOCTOR 4.

    public List<Patient> getPatientsWithNoDoctor() {
        return entityManager.createNativeQuery("select patient.* from patient left join (select * from consultation where date> dateadd(day,-30, getdate() ) ) consultation30" +
                " on consultation30.patient_id =patient.id where consultation30.id is null", Patient.class)
                .getResultList();


    }








}
