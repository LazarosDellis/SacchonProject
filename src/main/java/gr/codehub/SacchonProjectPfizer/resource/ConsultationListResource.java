package gr.codehub.SacchonProjectPfizer.resource;

import gr.codehub.SacchonProjectPfizer.exception.AuthorizationException;
import gr.codehub.SacchonProjectPfizer.jpaUtil.JpaUtil;
import gr.codehub.SacchonProjectPfizer.model.Consultation;
import gr.codehub.SacchonProjectPfizer.model.Patient;
import gr.codehub.SacchonProjectPfizer.repository.ConsultationRepository;
import gr.codehub.SacchonProjectPfizer.repository.PatientRepository;
import gr.codehub.SacchonProjectPfizer.representation.ConsultationRepresentation;
import gr.codehub.SacchonProjectPfizer.representation.MeasurementRepresentation;
import gr.codehub.SacchonProjectPfizer.security.Shield;
import org.restlet.resource.*;

import javax.persistence.EntityManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ConsultationListResource extends ServerResource {

    private int id;
    private int patientId;
    private int doctorId;
    private int chiefDoctorId;

    @Override
    protected void doInit() {
        id = Integer.parseInt(getAttribute("id"));
      //  patientId = Integer.parseInt(getAttribute("patientId"));

      //  chiefDoctorId = Integer.parseInt(getAttribute("chiefDoctorId"));
    }





    @Get("json")
    public ApiResult<List<ConsultationRepresentation>> getConsultation() {
     // String dId = getAttribute("doctorId");
        String dateF = getAttribute("from");
        String dateT = getAttribute("to");
        Date dateFrom = null;
        Date dateTo = null;
        Integer doctorId = null;

        try {
            dateFrom = new SimpleDateFormat("yyyy-MM-dd").parse(dateF);
            dateTo = new SimpleDateFormat("yyyy-MM-dd").parse(dateT);

           // docId = new  Integer.parseInt(getAttribute("dId"));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        EntityManager em = JpaUtil.getEntityManager();

        ConsultationRepository consultationRepository = new ConsultationRepository(em);

        List<Consultation> consultations = null;

        //consultations = consultationRepository.getConsultationsByPatientId(patientId);
        consultations = consultationRepository.getConsultationsByDoctorId(doctorId, dateFrom, dateTo);




        em.close();

            List<ConsultationRepresentation> consultationRepresentationList =
                    consultations.stream()
                            .map(ConsultationRepresentation::new)
                            .collect(toList());

            return new ApiResult<>(consultationRepresentationList, 200, "ok");

    }


    @Post("json")
    public ApiResult<ConsultationRepresentation> addConsultation(ConsultationRepresentation consultationRepresentation) {

        //authorisation check
        try {
            ResourceUtils.checkRole(this, Shield.ROLE_DOCTOR);
        } catch (AuthorizationException e) {
            return new ApiResult<>(null, 500, e.getMessage());
        }


        if (consultationRepresentation == null) return null;
        if (consultationRepresentation.getConsult() == null) return null;

        Consultation consultation = consultationRepresentation.createConsultation();
        EntityManager em = JpaUtil.getEntityManager();
        ConsultationRepository consultationRepository = new ConsultationRepository(em);
        consultationRepository.save(consultation);
        ConsultationRepresentation p = new ConsultationRepresentation(consultation);
        return new ApiResult<>(p, 200, "ok");
    }

    @Put("json")
    public ApiResult<ConsultationRepresentation> modifyConsultation(ConsultationRepresentation consultationRepresentation) {

        //authorisation check
        try {
            ResourceUtils.checkRole(this, Shield.ROLE_DOCTOR);
        } catch (AuthorizationException e) {
            return new ApiResult<>(null, 500, e.getMessage());
        }


        EntityManager em = JpaUtil.getEntityManager();
        ConsultationRepository consultationRepository = new ConsultationRepository(em);
        Consultation consultation = consultationRepository.read(id);

        consultation.setConsult(consultationRepresentation.getConsult());
        consultation.setDate(consultationRepresentation.getDate());


        consultationRepository.save(consultation);

        ConsultationRepresentation consultationRepresentation1 = new ConsultationRepresentation(consultation);
        em.close();
        return new ApiResult<>(consultationRepresentation1, 200, "ok");

    }


    @Delete("txt")
    public boolean deleteConsultation() {


        EntityManager em = JpaUtil.getEntityManager();
        ConsultationRepository consultationRepository = new ConsultationRepository(em);
        return consultationRepository.delete(id);
    }

}
