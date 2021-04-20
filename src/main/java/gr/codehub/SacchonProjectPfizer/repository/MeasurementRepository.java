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


    //1.CHIEFDOCTOR The information submissions (personal monitor data) of a patient over a time range

    public List<Measurement> getMeasurementsOfPatient(int patientId, Date from, Date to) {
        return entityManager.createQuery("SELECT m FROM Measurement m  where m.patient.id = :patientId and m.date > :from and m.date < :to  ",
                Measurement.class)
                .setParameter("patientId", patientId)
                .setParameter("from", from)
                .setParameter("to", to)
                .getResultList();
    }






}
