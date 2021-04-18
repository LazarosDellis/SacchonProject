package gr.codehub.SacchonProjectPfizer.resource;

import gr.codehub.SacchonProjectPfizer.jpaUtil.JpaUtil;
import gr.codehub.SacchonProjectPfizer.model.Doctor;
import gr.codehub.SacchonProjectPfizer.model.Patient;
import gr.codehub.SacchonProjectPfizer.repository.DoctorRepository;
import gr.codehub.SacchonProjectPfizer.repository.PatientRepository;
import gr.codehub.SacchonProjectPfizer.representation.DoctorRepresentation;
import gr.codehub.SacchonProjectPfizer.representation.PatientRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import javax.print.Doc;

public class RegisterResource extends ServerResource {



    @Post("json")

    public ApiResult<DoctorRepresentation> registerPatient(DoctorRepresentation doctorRepresentation){
            if (doctorRepresentation == null)
                return new ApiResult<>(null, 400, "No input data to create the customer");
            if (doctorRepresentation.getFullName() == null)
                return new ApiResult<>(null, 400, "No name was given to create the customer");
            if (doctorRepresentation.getUsername() == null)
                return new ApiResult<>(null, 400, "No username was given to create the customer");
            if (usernameExists(doctorRepresentation.getUsername()))
                return new ApiResult<>(null, 400, "Duplicate username");

            Doctor doctor = doctorRepresentation.createDoctor();
            EntityManager em = JpaUtil.getEntityManager();
            DoctorRepository doctorRepository = new DoctorRepository(em);
            doctorRepository.save(doctor);
            return new ApiResult<>(new DoctorRepresentation(doctor), 200,
                    "The Doctor was successfully created");
    }

    public ApiResult<PatientRepresentation> registerPatient(PatientRepresentation patientRepresentation){
            if (patientRepresentation == null)
                return new ApiResult<>(null, 400, "No input data to create the customer");
            if (patientRepresentation.getFullName() == null)
                return new ApiResult<>(null, 400, "No name was given to create the customer");
            if (patientRepresentation.getUsername() == null)
                return new ApiResult<>(null, 400, "No username was given to create the customer");
            if (usernameExists(patientRepresentation.getUsername()))
                return new ApiResult<>(null, 400, "Duplicate username");

            Patient patient = patientRepresentation.createPatient();
            EntityManager em = JpaUtil.getEntityManager();
            PatientRepository patientRepository = new PatientRepository(em);
            patientRepository.save(patient);
            return new ApiResult<>(new PatientRepresentation(patient), 200,
                    "The patient was successfully created");
    }


    public boolean usernameExists(String candidateUsername) {
        EntityManager em = JpaUtil.getEntityManager();
        Patient p = null;
        try {
            p = em.createQuery("SELECT p from Patient p where p.username= :candidate", Patient.class)
                    .setParameter("candidate", candidateUsername)
                    .getSingleResult();
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return p != null;
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
