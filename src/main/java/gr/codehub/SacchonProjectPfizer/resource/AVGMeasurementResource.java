package gr.codehub.SacchonProjectPfizer.resource;

import gr.codehub.SacchonProjectPfizer.exception.AuthorizationException;
import gr.codehub.SacchonProjectPfizer.jpaUtil.JpaUtil;
import gr.codehub.SacchonProjectPfizer.model.Consultation;
import gr.codehub.SacchonProjectPfizer.model.Patient;
import gr.codehub.SacchonProjectPfizer.repository.ConsultationRepository;
import gr.codehub.SacchonProjectPfizer.repository.PatientRepository;
import gr.codehub.SacchonProjectPfizer.representation.ConsultationRepresentation;
import gr.codehub.SacchonProjectPfizer.representation.PatientRepresentation;
import gr.codehub.SacchonProjectPfizer.security.Shield;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class AVGMeasurementResource  extends ServerResource {

    private int id;

    @Override
    protected void doInit() {
        id = Integer.parseInt(getAttribute("id"));

    }

    @Get("json")
    public ApiResult<List<Double>> getConsultation() {

        //authorisation check
        try {
            ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);
        } catch (AuthorizationException e) {
            return new ApiResult<>(null, 500, e.getMessage());
        }
        // String dId = getAttribute("doctorId");
        String dateF = getAttribute("from");
        String dateT = getAttribute("to");
        Date dateFrom = null;
        Date dateTo = null;
        Integer patientId = id;

        try {
            dateFrom = new SimpleDateFormat("yyyy-MM-dd").parse(dateF);
            dateTo = new SimpleDateFormat("yyyy-MM-dd").parse(dateT);


        } catch (ParseException e) {
            e.printStackTrace();
        }

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

        PatientRepository patientRepository = new PatientRepository(em);

        List<Double> avgMeasurements = null;

        //consultations = consultationRepository.getConsultationsByPatientId(patientId);
        avgMeasurements = patientRepository.getAvgOfMeasurements(patientId, dateFrom, dateTo);

        em.close();



        return new ApiResult<>(avgMeasurements, 200, "ok");

    }

}
