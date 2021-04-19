package gr.codehub.SacchonProjectPfizer.resource;

import gr.codehub.SacchonProjectPfizer.exception.AuthorizationException;
import gr.codehub.SacchonProjectPfizer.jpaUtil.JpaUtil;
import gr.codehub.SacchonProjectPfizer.model.Consultation;
import gr.codehub.SacchonProjectPfizer.model.Doctor;
import gr.codehub.SacchonProjectPfizer.model.Patient;
import gr.codehub.SacchonProjectPfizer.repository.ConsultationRepository;
import gr.codehub.SacchonProjectPfizer.repository.DoctorRepository;
import gr.codehub.SacchonProjectPfizer.repository.PatientRepository;
import gr.codehub.SacchonProjectPfizer.representation.ConsultationRepresentation;
import gr.codehub.SacchonProjectPfizer.representation.DoctorRepresentation;
import gr.codehub.SacchonProjectPfizer.representation.PatientRepresentation;
import gr.codehub.SacchonProjectPfizer.security.Shield;
import org.restlet.data.Product;
import org.restlet.resource.*;

import javax.persistence.EntityManager;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class DoctorResource extends ServerResource {
    private int id;

    @Override
    protected void doInit() {
        id = Integer.parseInt(getAttribute("id"));
    }


    @Get("json")
    public ApiResult<DoctorRepresentation> getDoctor(){

        //authorisation check
        try {
            ResourceUtils.checkRole(this, Shield.ROLE_DOCTOR);
        } catch (AuthorizationException e) {
            return new ApiResult<>(null, 500, e.getMessage());
        }

        EntityManager em = JpaUtil.getEntityManager();
        DoctorRepository doctorRepository = new DoctorRepository(em);
        Doctor doctor = doctorRepository.read(id);

        DoctorRepresentation doctorRepresentation = new DoctorRepresentation(doctor);
        em.close();

        return new ApiResult<>(doctorRepresentation, 200, "ok");
    }

    @Post("json")
    public ApiResult<DoctorRepresentation> add(DoctorRepresentation doctorRepresentationIn){

        //authorisation check
        try {
            ResourceUtils.checkRole(this, Shield.ROLE_DOCTOR);
        } catch (AuthorizationException e) {
            return new ApiResult<>(null, 500, e.getMessage());
        }

        if (doctorRepresentationIn ==null) return null;
        if (doctorRepresentationIn.getFullName() == null) return null;
        EntityManager em = JpaUtil.getEntityManager();


        Doctor doctor = doctorRepresentationIn.createDoctor();

        DoctorRepository doctorRepository = new DoctorRepository(em);
        doctorRepository.save(doctor);
        DoctorRepresentation d = new DoctorRepresentation(doctor);
        return new ApiResult<>(d, 200, "ok");
    }


    @Put("json")
    public ApiResult<DoctorRepresentation> putDoctor(DoctorRepresentation doctorRepresentation) {

        //authorisation check
        try {
            ResourceUtils.checkRole(this, Shield.ROLE_DOCTOR);
        } catch (AuthorizationException e) {
            return new ApiResult<>(null, 500, e.getMessage());
        }


        EntityManager em = JpaUtil.getEntityManager();
        DoctorRepository doctorRepository = new DoctorRepository(em);
        Doctor doctor = doctorRepository.read(id);

        doctor.setFullName(doctorRepresentation.getFullName());
        doctor.setEmail(doctorRepresentation.getEmail());
        doctor.setUsername(doctorRepresentation.getUsername());
       // doctor.setRole(doctorRepresentation.getRole());
        doctor.setPassword(doctorRepresentation.getPassword());


        doctorRepository.save(doctor);

        DoctorRepresentation doctorRepresentation2 = new DoctorRepresentation(doctor);
        em.close();
        return new ApiResult<>(doctorRepresentation2, 200, "ok");

    }


    @Delete("txt")
    public boolean deleteDoctor() {
        EntityManager em = JpaUtil.getEntityManager();
        DoctorRepository doctorRepository = new DoctorRepository(em);
        return doctorRepository.delete(id);
    }
}
