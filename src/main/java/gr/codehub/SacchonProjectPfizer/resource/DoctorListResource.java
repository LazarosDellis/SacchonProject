package gr.codehub.SacchonProjectPfizer.resource;

import gr.codehub.SacchonProjectPfizer.exception.AuthorizationException;
import gr.codehub.SacchonProjectPfizer.jpaUtil.JpaUtil;
import gr.codehub.SacchonProjectPfizer.model.Doctor;

import gr.codehub.SacchonProjectPfizer.repository.DoctorRepository;

import gr.codehub.SacchonProjectPfizer.representation.DoctorRepresentation;

import gr.codehub.SacchonProjectPfizer.security.Shield;
import org.restlet.resource.Get;

import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class DoctorListResource  extends ServerResource  {


    @Get("json")
    public ApiResult<List<DoctorRepresentation>> getDoctor(){

        //authorisation check
        try {
            ResourceUtils.checkRole(this, Shield.ROLE_ADMIN);
        } catch (AuthorizationException e) {
            return new ApiResult<>(null, 500, e.getMessage());
        }


        EntityManager em = JpaUtil.getEntityManager();
        DoctorRepository doctorRepository = new DoctorRepository(em);
        List<Doctor> doctors =null;

        doctors = doctorRepository.getDoctors();
        em.close();

        List<DoctorRepresentation> doctorRepresentationList =
                doctors.stream()
                        .map(DoctorRepresentation:: new)
                        .collect(toList());

        return new ApiResult<>(doctorRepresentationList, 200, "ok");
    }


}
