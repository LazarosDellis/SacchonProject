package gr.codehub.SacchonProjectPfizer.repository;


import gr.codehub.SacchonProjectPfizer.model.Consultation;


import javax.persistence.EntityManager;

import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.concurrent.TimeUnit.DAYS;

public class ConsultationRepository extends Repository<Consultation, Integer> {

    private EntityManager entityManager;

    public ConsultationRepository(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Class<Consultation> getEntityClass() {
        return Consultation.class;
    }

    @Override
    public String getClassName() {
        return Consultation.class.getName();
    }

    public List<Consultation> getAllConsultations() {
        return entityManager.createQuery("SELECT  c FROM Consultation c  ",
                Consultation.class)
                .getResultList();
    }


    public Consultation getByConsult(int consultationId) {
        List<Consultation> myList = new ArrayList();
        Consultation consultation = myList.get(myList.size());
        LocalDate localDate = LocalDate.now();
        long days = DAYS.toChronoUnit().between(LocalDate.now(), (Temporal) consultation.getDate());

        return entityManager.createQuery("SELECT c FROM Consultation c WHERE c.consult = :consult", Consultation.class)
                .setParameter("consultationId", consultationId)
                .getSingleResult();
    }


//    select patient.*
//    from patient left join
//            (select * from consultation where date> dateadd(day,-30, getdate() ) ) consultation30
//    on consultation30.patient_id =patient.id
//    where consultation30.id is null


//    List<Customer> customers = (List<Customer>)em.createNativeQuery
//
//            ("SELECT * FROM customers, "jpqlexample.entities.Customer.class)
//            .getResultList();


    // p.id = :patientId
    //inner join Patient p
    public List<Consultation> getConsultationsOfAPatient(int patientId) {
        try{
        return entityManager.createQuery("SELECT c FROM Consultation c where c.patient.id = :patientId ",
                Consultation.class)
                 .setParameter("patientId", patientId)
                .getResultList();
        }catch (Exception e){
            return null;
        }

    }


    //2. The information submissions (consultations) of a doctor over a time range
    public List<Consultation> getConsultationsByDoctorId(int doctorId, Date from, Date to ) {
       try {
           return entityManager.createNativeQuery(" Select * from Consultation" +
                           " where Consultation.doctor_id = :doctorId" +
                           " AND Consultation.date > :from" +
                           " AND Consultation.date < :to",
                   Consultation.class)
                   .setParameter("doctorId", doctorId)
                   .setParameter("from", from)
                   .setParameter("to", to)
                   .getResultList();
       }catch (Exception e){
           return null;
       }

    }


//    public boolean deleteConsultation(int id) {
//
//            return (boolean) entityManager.createNativeQuery("Delete * from Consultation where Consultation.id = :id", Consultation.class).setParameter("id", id).getSingleResult();
//
//
//
//    }
}
