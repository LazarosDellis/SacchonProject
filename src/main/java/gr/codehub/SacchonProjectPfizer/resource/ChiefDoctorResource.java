package gr.codehub.SacchonProjectPfizer.resource;

import gr.codehub.SacchonProjectPfizer.jpaUtil.JpaUtil;
import gr.codehub.SacchonProjectPfizer.model.ChiefDoctor;
import gr.codehub.SacchonProjectPfizer.repository.ChiefDoctorRepository;
import gr.codehub.SacchonProjectPfizer.repository.DoctorRepository;
import gr.codehub.SacchonProjectPfizer.representation.ChiefDoctorRepresentation;
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
    public ChiefDoctorRepresentation getChiefDoctor() {
        EntityManager em = JpaUtil.getEntityManager();
        ChiefDoctorRepository chiefDoctorRepository = new ChiefDoctorRepository(em);
        ChiefDoctor chiefDoctor = chiefDoctorRepository.read(id);
        ChiefDoctorRepresentation chiefDoctorRepresentation2 = new ChiefDoctorRepresentation();
        em.close();
        return chiefDoctorRepresentation2;

    }

    @Delete("txt")
    public boolean deleteChiefDoctor() {
        EntityManager em = JpaUtil.getEntityManager();
        ChiefDoctorRepository chiefDoctorRepository = new ChiefDoctorRepository(em);
        return chiefDoctorRepository.delete(id);
    }


}



