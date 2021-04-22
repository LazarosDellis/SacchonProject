package gr.codehub.SacchonProjectPfizer.resource;

import gr.codehub.SacchonProjectPfizer.exception.AuthorizationException;
import gr.codehub.SacchonProjectPfizer.jpaUtil.JpaUtil;
import gr.codehub.SacchonProjectPfizer.model.Patient;
import gr.codehub.SacchonProjectPfizer.repository.ChiefDoctorRepository;
import gr.codehub.SacchonProjectPfizer.repository.PatientRepository;
import gr.codehub.SacchonProjectPfizer.representation.ChiefDoctorRepresentation;
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

public class PatientsWithNoActivityResource extends ServerResource {


    @Get("json")
    public ApiResult<List<PatientRepresentation>> getPatient(){
        String dateF = getAttribute("from");
        String dateT = getAttribute("to");
        Date dateFrom = null;
        Date dateTo = null;
        try {
           dateFrom = new SimpleDateFormat("yyyy-MM-dd").parse(dateF);
           dateTo = new SimpleDateFormat("yyyy-MM-dd").parse(dateT);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
                ResourceUtils.checkRole(this, Shield.ROLE_DOCTOR);
            } catch (AuthorizationException e1) {
                try{
                    ResourceUtils.checkRole(this, Shield.ROLE_ADMIN);
                }catch (AuthorizationException e2) {
                    return new ApiResult<>(null, 400, "No Authorization");
                }
            }






        EntityManager em = JpaUtil.getEntityManager();
        PatientRepository patientRepository = new PatientRepository(em);

        List<Patient> patients = null;

        patients = patientRepository.getActivityMeasurements(dateFrom, dateTo);
        em.close();
        List<PatientRepresentation> patientRepresentationList =
                patients.stream()
                        .map(PatientRepresentation:: new)
                        .collect(toList());

        return new ApiResult<>(patientRepresentationList, 200, "ok");
    }

}
