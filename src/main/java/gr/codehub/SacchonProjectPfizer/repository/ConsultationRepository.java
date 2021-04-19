package gr.codehub.SacchonProjectPfizer.repository;

import gr.codehub.SacchonProjectPfizer.model.Consultation;
import gr.codehub.SacchonProjectPfizer.model.Patient;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.util.ArrayList;
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

    public List<Consultation> getConsultationsByPatientId(int patientId) {
        return entityManager.createQuery("SELECT  c FROM Consultation c WHERE c.patient.id = : patientId  ",
                Consultation.class)
                .setParameter("patientId", patientId)
                .getResultList();
    }


    //2. The information submissions (consultations) of a doctor over a time range
    public List<Consultation> getConsultationsByDoctorId(int doctorId) {
        return entityManager.createQuery("SELECT c FROM Consultation c WHERE c.doctor.id = : doctorId",
                Consultation.class)
                .setParameter("doctorId", doctorId)
                .getResultList();


    }



}
