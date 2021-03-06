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
    public ChiefDoctor getByUsername(String username ) {
        try{  return entityManager.createQuery("SELECT c  FROM ChiefDoctor c " +
                "WHERE c.username = : username", ChiefDoctor.class)
                .setParameter("username", username)
                .getSingleResult();}
        catch (Exception e){
            return null;
        }
    }








//3. The list of the patients who are waiting for a consultation and the time elapsed since they needed to have one

    public List<Patient> getPatientsWithNoConsultation(Date date) {
        return (List<Patient>) entityManager.createNativeQuery(" select patient.* from patient left join (select * from consultation where date> dateadd(day,-30, getdate() ) ) consultation30" +
                "    on consultation30.patient_id =patient.id" +
                "    where consultation30.id is null", Patient.class)
                .setParameter("date", date)
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



}
