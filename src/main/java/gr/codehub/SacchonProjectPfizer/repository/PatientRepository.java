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


    public Patient getByUsername(String username ){
        return entityManager.createQuery("SELECT p  FROM Patient p " +
                "WHERE p.username = :username",Patient.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    public List<Patient> getPatients(){
        return entityManager.createQuery("SELECT p FROM Patient p", Patient.class)
                .getResultList();
    }


//    public List<Measurement> getMeasurement(Date from, Date to){
//        return entityManager.createQuery("SELECT m  FROM Measurement m inner join Patient p WHERE  m.date >= : from and m.date <= : to ",
//       // return entityManager.createQuery("SELECT m  FROM Measurement m WHERE  m.date :from and :to ",
//
//                Measurement.class)
//                .setParameter("from", from)
//                .setParameter("to", to)
//                .getResultList();
//
//    }


//    public List<Measurement> getAvgOfMeasurements(){
//        return entityManager.createNativeQuery("SELECT AVG(measurement.valueOfMeasurement) from Measurement M left join( select * from measurement GROUP BY measurement.typeOfMeasurement = 'carbs') average  "+
//                "")
//    }
    // return entityManager.createQuery("SELECT m  FROM Measurement m WHERE  m.date :from and :to ",
//2.
    public List<Measurement> getAvgOfMeasurements(int patientId, Date from1, Date to){
        return entityManager.createQuery("SELECT AVG(m.valueOfMeasurement)  FROM Measurement m WHERE m.patient.id = : patientId and  m.date >= :from1 and m.date <= :to GROUP BY m.typeOfMeasurement ",

                Measurement.class)
                .setParameter("patientId", patientId)
                .setParameter("from1", from1)
                .setParameter("to", to)
                .getResultList();

    }

// VIEW
//their average daily blood glucose level over a user- specified
//period their average carb intake over a user-specified period the
//current and past consultations from doctors


    //   4. The list of the patients with no activity over a time range
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






}
