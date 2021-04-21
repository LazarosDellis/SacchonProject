package gr.codehub.SacchonProjectPfizer.resource;

import gr.codehub.SacchonProjectPfizer.jpaUtil.JpaUtil;
import gr.codehub.SacchonProjectPfizer.model.Consultation;
import gr.codehub.SacchonProjectPfizer.model.Doctor;
import gr.codehub.SacchonProjectPfizer.model.Patient;
import gr.codehub.SacchonProjectPfizer.repository.ConsultationRepository;
import gr.codehub.SacchonProjectPfizer.repository.PatientRepository;
import gr.codehub.SacchonProjectPfizer.representation.ConsultationRepresentation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;

public class PostConsultationResource extends ServerResource {

    private int doctorId;

    @Override
    protected void doInit() {
       // doctorId = Integer.parseInt(getAttribute("doctorId"));
    }

    @Post("json")// create consultation from a doctor to a patient
    public ApiResult<ConsultationRepresentation> add(ConsultationRepresentation consultationRepresentationIn) {

        //authorisation check
//        try {
//            ResourceUtils.checkRole(this, Shield.ROLE_DOCTOR);
//        } catch (AuthorizationException e) {
//            return new ApiResult<>(null, 500, e.getMessage());
//        }

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

}
