package gr.codehub.SacchonProjectPfizer.resource;

import gr.codehub.SacchonProjectPfizer.jpaUtil.JpaUtil;
import gr.codehub.SacchonProjectPfizer.model.Patient;
import gr.codehub.SacchonProjectPfizer.repository.PatientRepository;
import gr.codehub.SacchonProjectPfizer.representation.PatientRepresentation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;

public class RegisterResource extends ServerResource {
//    @Post
//    public PatientRepresentation registerPatient(PatientRepresentation patientRepresentation){
//            if (patientRepresentation == null)
//                return new ApiResult<>(null, 400, "No input data to create the customer");
//            if (patientRepresentation.getFullName() == null)
//                return new ApiResult<>(null, 400, "No name was given to create the customer");
//            if (patientRepresentation.getUsername() == null)
//                return new ApiResult<>(null, 400, "No username was given to create the customer");
//            if (usernameExists(patientRepresentation.getUsername()))
//                return new ApiResult<>(null, 400, "Duplicate username");
//
//            Patient patient = patientRepresentation.createPatient();
//            EntityManager em = JpaUtil.getEntityManager();
//            PatientRepository customerRepository = new PatientRepository(em);
//            customerRepository.save(patient);
//            return new ApiResult<>(new PatientRepresentation(patient), 200,
//                    "The patient was successfully created");
//    }
}
