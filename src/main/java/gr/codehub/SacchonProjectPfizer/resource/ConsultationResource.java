package gr.codehub.SacchonProjectPfizer.resource;

import gr.codehub.SacchonProjectPfizer.exception.AuthorizationException;
import gr.codehub.SacchonProjectPfizer.jpaUtil.JpaUtil;
import gr.codehub.SacchonProjectPfizer.model.Consultation;
import gr.codehub.SacchonProjectPfizer.model.Doctor;
import gr.codehub.SacchonProjectPfizer.model.Patient;
import gr.codehub.SacchonProjectPfizer.repository.ConsultationRepository;
import gr.codehub.SacchonProjectPfizer.repository.PatientRepository;
import gr.codehub.SacchonProjectPfizer.representation.ConsultationRepresentation;

import gr.codehub.SacchonProjectPfizer.security.Shield;
import org.restlet.resource.*;

import javax.persistence.EntityManager;

public class ConsultationResource extends ServerResource {

    private int patientId;
    private int id;

    @Override
    protected void doInit() {
        patientId = Integer.parseInt(getAttribute("patientId"));
        id = Integer.parseInt(getAttribute("id"));
    }

    @Get("json") // get
    public ApiResult<ConsultationRepresentation> getConsultation() {

        //authorisation check
        try {
            ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);
        } catch (AuthorizationException e) {
            try {
                ResourceUtils.checkRole(this, Shield.ROLE_DOCTOR);
            } catch (AuthorizationException e1) {
                try{
                    ResourceUtils.checkRole(this, Shield.ROLE_ADMIN);
                }catch (AuthorizationException e2) {
                    return new ApiResult<>(null, 500, e.getMessage());
                }
            }


        }

        EntityManager em = JpaUtil.getEntityManager();



        ConsultationRepository consultationRepository = new ConsultationRepository(em);
        Consultation consultation = consultationRepository.read(patientId);
        ConsultationRepresentation consultationRepresentation2 = new ConsultationRepresentation(consultation);
        em.close();


        return new ApiResult<>(consultationRepresentation2, 200, "ok");

    }

    @Post("json")// create
    public ApiResult<ConsultationRepresentation> add(ConsultationRepresentation consultationRepresentationIn) {

        //authorisation check
        try {
            ResourceUtils.checkRole(this, Shield.ROLE_DOCTOR);
        } catch (AuthorizationException e) {
            return new ApiResult<>(null, 500, e.getMessage());
        }

        if (consultationRepresentationIn == null) return null;
        if (consultationRepresentationIn.getConsult() == null) return null;

        EntityManager em = JpaUtil.getEntityManager();

        int doctorId = consultationRepresentationIn.getDoctorId();
        Doctor doctor = em.find(Doctor.class, doctorId);
        int patientId = consultationRepresentationIn.getPatientId();
        Patient patient = em.find(Patient.class, patientId);
        PatientRepository patientRepository = new PatientRepository(em);
        patient.setDoctor(doctor);
        patientRepository.save(patient);


        Consultation consultation = consultationRepresentationIn.createConsultation();
        consultation.setDoctor(doctor);
        consultation.setPatient(patient);

        ConsultationRepository consultationRepository = new ConsultationRepository(em);
        consultationRepository.save(consultation);
        ConsultationRepresentation p = new ConsultationRepresentation(consultation);
        return new ApiResult<>(p, 200, "ok");
    }


    @Put("json")//update
    public ApiResult<ConsultationRepresentation> putConsultation(ConsultationRepresentation consultationRepresentation) {

        //authorisation check
        try {
            ResourceUtils.checkRole(this, Shield.ROLE_DOCTOR);
        } catch (AuthorizationException e) {
            return new ApiResult<>(null, 500, e.getMessage());
        }

        EntityManager em = JpaUtil.getEntityManager();

        int doctorId = consultationRepresentation.getDoctorId();
        Doctor doctor = em.find(Doctor.class, doctorId);
        int patientId = consultationRepresentation.getPatientId();
        Patient patient = em.find(Patient.class, patientId);

        ConsultationRepository consultationRepository = new ConsultationRepository(em);
        Consultation consultation = consultationRepository.read(id);

        consultation.setConsult(consultationRepresentation.getConsult());
        consultation.setDate(consultationRepresentation.getDate());
        consultation.setDoctor(doctor);
        consultation.setPatient(patient);

        consultationRepository.save(consultation);

        ConsultationRepresentation consultationRepresentation2 = new ConsultationRepresentation(consultation);
        em.close();
        return new ApiResult<>(consultationRepresentation2, 200, "ok");

    }


    @Delete("txt")
    public boolean deleteConsultation() {
        EntityManager em = JpaUtil.getEntityManager();
        ConsultationRepository consultationRepository = new ConsultationRepository(em);
        return consultationRepository.delete(id);
    }

}
