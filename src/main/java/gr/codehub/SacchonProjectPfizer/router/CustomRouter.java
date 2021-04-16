package gr.codehub.SacchonProjectPfizer.router;
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
        return router;
    }


    public Router protectedResources(){
        Router router = new Router();
        router.attach("/patient", PatientListResource.class); //get, post
        router.attach("/patient/{id}", PatientResource.class); //get, put, delete

        router.attach("/doctor", DoctorListResource.class);//get, post
        router.attach("/doctor/{id}", DoctorResource.class);//get, put, delete


        //router.attach("/cart", CartListResource.class); //get, post
        //router.attach("/cart/{id}", CartResource.class);//get, put, delete

        //router.attach("/cart/{id}/product", CartProductListResource.class); //get, post
        //router.attach("/cart/{id}/product/{pid}", CartProductResource.class);//get, put, delete
        return router;

    }


}
