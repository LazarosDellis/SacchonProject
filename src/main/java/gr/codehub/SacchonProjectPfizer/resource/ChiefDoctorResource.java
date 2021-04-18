package gr.codehub.SacchonProjectPfizer.resource;

import gr.codehub.SacchonProjectPfizer.exception.AuthorizationException;
import gr.codehub.SacchonProjectPfizer.jpaUtil.JpaUtil;
import gr.codehub.SacchonProjectPfizer.model.ChiefDoctor;
import gr.codehub.SacchonProjectPfizer.model.Doctor;
import gr.codehub.SacchonProjectPfizer.repository.ChiefDoctorRepository;
import gr.codehub.SacchonProjectPfizer.repository.DoctorRepository;
import gr.codehub.SacchonProjectPfizer.representation.ChiefDoctorRepresentation;
import gr.codehub.SacchonProjectPfizer.representation.DoctorRepresentation;
import gr.codehub.SacchonProjectPfizer.security.Shield;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;

import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;

public class ChiefDoctorResource extends ServerResource {

    private int id;

    @Override
    protected void doInit() {
        id = Integer.parseInt(getAttribute("id"));
    }



    @Get("json")
    public ApiResult<ChiefDoctorRepresentation> getChiefDoctor() {
        //authorisation check
        try {
            ResourceUtils.checkRole(this, Shield.ROLE_ADMIN);
        } catch (AuthorizationException e) {
            return new ApiResult<>(null, 500, e.getMessage());
        }

        EntityManager em = JpaUtil.getEntityManager();
        ChiefDoctorRepository chiefDoctorRepository = new ChiefDoctorRepository(em);
        ChiefDoctor chiefDoctor = chiefDoctorRepository.read(id);


        ChiefDoctorRepresentation chiefDoctorRepresentation = new ChiefDoctorRepresentation(chiefDoctor);
        em.close();
        return new ApiResult<>(chiefDoctorRepresentation, 200, "ok");

    }

    @Put("json")//update
    public ApiResult<ChiefDoctorRepresentation> putChiefDoctor(ChiefDoctorRepresentation chiefdoctorRepresentation) {

        //authorisation check
        try {
            ResourceUtils.checkRole(this, Shield.ROLE_ADMIN);
        } catch (AuthorizationException e) {
            return new ApiResult<>(null, 500, e.getMessage());
        }


        EntityManager em = JpaUtil.getEntityManager();
        ChiefDoctorRepository chiefDoctorRepository = new ChiefDoctorRepository(em);
        ChiefDoctor chiefDoctor = chiefDoctorRepository.read(id);

        chiefDoctor.setFullName(chiefdoctorRepresentation.getFullName());
        chiefDoctor.setEmail(chiefdoctorRepresentation.getEmail());
        chiefDoctor.setUsername(chiefdoctorRepresentation.getUsername());
        // chiefDoctor.setRole(doctorRepresentation.getRole());
        chiefDoctor.setPassword(chiefdoctorRepresentation.getPassword());


        chiefDoctorRepository.save(chiefDoctor);

        ChiefDoctorRepresentation chiefDoctorRepresentation2 = new ChiefDoctorRepresentation(chiefDoctor);
        em.close();
        return new ApiResult<>(chiefDoctorRepresentation2, 200, "ok");

    }


    @Delete("txt")
    public boolean deleteChiefDoctor() {
        EntityManager em = JpaUtil.getEntityManager();
        ChiefDoctorRepository chiefDoctorRepository = new ChiefDoctorRepository(em);
        return chiefDoctorRepository.delete(id);
    }


}



