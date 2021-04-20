package gr.codehub.SacchonProjectPfizer.resource;

import gr.codehub.SacchonProjectPfizer.jpaUtil.JpaUtil;
import gr.codehub.SacchonProjectPfizer.model.ChiefDoctor;


import gr.codehub.SacchonProjectPfizer.repository.ChiefDoctorRepository;
import gr.codehub.SacchonProjectPfizer.representation.ChiefDoctorRepresentation;

import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;

public class RegisterChiefDoctorResource extends ServerResource {



    @Post

    public ApiResult<ChiefDoctorRepresentation> registerChiefDoctor(ChiefDoctorRepresentation chiefDoctorRepresentation){
        if (chiefDoctorRepresentation == null)
            return new ApiResult<>(null, 400, "No input data to create the ChiefDoctor");
        if (chiefDoctorRepresentation.getFullName() == null)
            return new ApiResult<>(null, 400, "No name was given to create the ChiefDoctor");
        if (chiefDoctorRepresentation.getUsername() == null)
            return new ApiResult<>(null, 400, "No username was given to create the ChiefDoctor");
        if (usernameExists(chiefDoctorRepresentation.getUsername()))
            return new ApiResult<>(null, 400, "Duplicate username");

        ChiefDoctor chiefDoctor = chiefDoctorRepresentation.createChiefDoctor();
        EntityManager em = JpaUtil.getEntityManager();
        ChiefDoctorRepository chiefDoctorRepository = new ChiefDoctorRepository(em);
        chiefDoctorRepository.save(chiefDoctor);
        return new ApiResult<>(new ChiefDoctorRepresentation(chiefDoctor), 200,
                "The ChiefDoctor was successfully created");
    }
    public boolean usernameExists(String candidateUsername) {
        EntityManager em = JpaUtil.getEntityManager();
        ChiefDoctor c = null;
        try {
            c = em.createQuery("SELECT c from ChiefDoctor c where c.username= :candidate", ChiefDoctor.class)
                    .setParameter("candidate", candidateUsername)
                    .getSingleResult();
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return c != null;
    }

    @Get
    public boolean usernameExists() {
        String candidateUsername = "";

        try {
            candidateUsername = getQueryValue("username");
        } catch (Exception e) {
            return false;
        }
        return usernameExists(candidateUsername);
    }
}
