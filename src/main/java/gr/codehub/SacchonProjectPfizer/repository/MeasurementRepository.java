package gr.codehub.SacchonProjectPfizer.repository;

import gr.codehub.SacchonProjectPfizer.model.Consultation;
import gr.codehub.SacchonProjectPfizer.model.Doctor;
import gr.codehub.SacchonProjectPfizer.model.Measurement;
import gr.codehub.SacchonProjectPfizer.model.Patient;
import gr.codehub.SacchonProjectPfizer.resource.MeasurementListResource;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;


public class MeasurementRepository extends Repository<Measurement, Integer>{



    private EntityManager entityManager;
    public MeasurementRepository(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Class<Measurement> getEntityClass() {
        return Measurement.class;
    }

    @Override
    public String getClassName() {
        return Measurement.class.getName();
    }


    public List<Measurement> getAllMeasurementsOfaPatient(int patientId) {
        return entityManager.createQuery("SELECT m FROM Measurement m where m.patient.id = :patientId ", Measurement.class)
                .setParameter("patientId", patientId)
                .getResultList();
    }



    public List<Measurement> getMeasurementsByDoctorId(int doctorId){
        return entityManager.createQuery("SELECT m FROM Measurement m INNER JOIN Patient p  WHERE m.patient.id = :patientId and p.doctorId = :doctorId", Measurement.class)
                .setParameter("doctorId", doctorId)
                .getResultList();

    }


//    SELECT *
//    from Measurement m
//    inner join Patient p  on m.patient_id = p.id
//    WHERE p.doctor_id = 1





//    public List<Measurement> getMeasurements(int patientId){
//        return  entityManager.createQuery("SELECT  m FROM Measurement m inner join Patient p  where p.id = : patientId ",
//                Measurement.class)
//                .setParameter("patientId", patientId)
//                .getResultList();
//    }



    //1. The information submissions (personal monitor data) of a patient over a time range

    //, Date from, Date to
    // and m.date > : from and m.date < : to
    public List<Measurement> getMeasurementsByPatientId(int patientId ) {
        return entityManager.createQuery("SELECT m FROM Measurement m WHERE m.patient.id = :patientId ",
                Measurement.class)
                .setParameter("patientId", patientId)
//                .setParameter("from", from)
//                .setParameter("to", to)
                .getResultList();
    }
}
