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
                "WHERE p.username = : username",Patient.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    public List<Patient> getPatients(){
        return entityManager.createQuery("SELECT p FROM Patient p", Patient.class)
                .getResultList();
    }


    public List<Measurement> getMeasurement(Date from, Date to){
        return entityManager.createQuery("SELECT m  FROM Measurement m inner join Patient p WHERE  m.date >= : from and m.date <= : to ",
       // return entityManager.createQuery("SELECT m  FROM Measurement m WHERE  m.date :from and :to ",

                Measurement.class)
                .setParameter("from", from)
                .setParameter("to", to)
                .getResultList();

    }

    public List<Consultation> getConsultations(){
        return  entityManager.createQuery("SELECT  c FROM Consultation c ",
                Consultation.class)
                .getResultList();
    }


// VIEW
//their average daily blood glucose level over a user- specified
//period their average carb intake over a user-specified period the
//current and past consultations from doctors







}
