package gr.codehub.SacchonProjectPfizer.router;

import gr.codehub.SacchonProjectPfizer.repository.ChiefDoctorRepository;
import gr.codehub.SacchonProjectPfizer.resource.*;
import org.restlet.Application;
import org.restlet.routing.Router;

public class CustomRouter {

    private Application application;

    public CustomRouter(Application application) {
        this.application = application;
    }

    public Router publicResources() {
        Router router = new Router();
        router.attach("/ping", PingServerResource.class); //get
        router.attach("/register", RegisterResource.class);

        router.attach("/patient", PatientListResource.class); //get, post
        router.attach("/patient/{id}", PatientResource.class); //get, put, delete

        router.attach("/doctor", DoctorListResource.class);//get, post
        router.attach("/doctor/{id}", DoctorResource.class); // get one / post / put

       // router.attach("/chiefDoctor", ChiefDoctorResource.class);
       // router.attach("/chiefDoctor/{id}", ChiefDoctorResource.class);

        router.attach("/consultation/{id}/patient/{patientId}", ConsultationResource.class);
        router.attach("/consultation/{id}/doctor/{doctorId}", ConsultationResource.class);

        router.attach("/doctor/{id}/consultation/{consultationId}", ConsultationResource.class);



        router.attach("/measurement/{id}", MeasurementResource.class);
        router.attach("/measurement/{id}/patient/{patientId}", MeasurementResource.class);
        router.attach("/measurement/{id}/patient/{patientId}", MeasurementListResource.class);

       // router.attach("/")



        return router;
    }


    public Router protectedResources(){
        Router router = new Router();






        router.attach("/chiefDoctor", ChiefDoctorResource.class);
        router.attach("/chiefDoctor/{id}", ChiefDoctorResource.class);

        router.attach("/consultation", ConsultationListResource.class);


        router.attach("/consultations/{id}", ConsultationResource.class);







        return router;

    }

}
