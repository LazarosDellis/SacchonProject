package gr.codehub.SacchonProjectPfizer.resource;

import gr.codehub.SacchonProjectPfizer.representation.PatientRepresentation;
import org.restlet.resource.Post;

public class RegisterResource {
    @Post
    public PatientRepresentation registerPatient(PatientRepresentation patientRepresentation){
        return null;
    }
}
