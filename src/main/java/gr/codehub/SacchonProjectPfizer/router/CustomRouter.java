package gr.codehub.SacchonProjectPfizer.router;

import gr.codehub.SacchonProjectPfizer.resource.DoctorResource;
import gr.codehub.SacchonProjectPfizer.resource.PatientResource;
import gr.codehub.SacchonProjectPfizer.resource.PingServerResource;
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
        return router;
    }


    public Router protectedResources(){
        Router router = new Router();
        router.attach("/patient", PatientResource.class); //get, post
        router.attach("/patient/{id}", PatientResource.class); //get, put, delete

        router.attach("/doctor", DoctorResource.class);//get, post
        router.attach("/doctor/{id}", DoctorResource.class);//get, put, delete






        return router;

    }

}
