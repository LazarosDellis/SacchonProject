package gr.codehub.SacchonProjectPfizer.resource;

import gr.codehub.SacchonProjectPfizer.exception.AuthorizationException;
import gr.codehub.SacchonProjectPfizer.jpaUtil.JpaUtil;
import gr.codehub.SacchonProjectPfizer.model.Doctor;
import gr.codehub.SacchonProjectPfizer.model.Patient;
import gr.codehub.SacchonProjectPfizer.repository.DoctorRepository;
import gr.codehub.SacchonProjectPfizer.repository.PatientRepository;
import gr.codehub.SacchonProjectPfizer.representation.DoctorRepresentation;
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

public class DoctorsWithNoActivityResource extends ServerResource {


    @Get("json")
    public ApiResult<List<DoctorRepresentation>> getDoctor(){
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

        //authorisation check
        try {
            ResourceUtils.checkRole(this, Shield.ROLE_ADMIN);
        } catch (AuthorizationException e) {
            return new ApiResult<>(null, 500, e.getMessage());
        }


        EntityManager em = JpaUtil.getEntityManager();
        DoctorRepository doctorRepository = new DoctorRepository(em);

        List<Doctor> doctors = null;

        doctors = doctorRepository.getActivityConsultations(dateFrom, dateTo);
        em.close();
        List<DoctorRepresentation> doctorRepresentationList =
                doctors.stream()
                        .map(DoctorRepresentation:: new)
                        .collect(toList());

        return new ApiResult<>(doctorRepresentationList, 200, "ok");
    }

}