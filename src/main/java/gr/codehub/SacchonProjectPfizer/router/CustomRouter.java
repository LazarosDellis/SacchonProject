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

        router.attach("/registerDoctor", RegisterDoctorResource.class);
        router.attach("/registerPatient", RegisterPatientResource.class);

        router.attach("/registerChiefDoctor", RegisterChiefDoctorResource.class);
        router.attach("/login", LoginResource.class);


           return router;
    }


    public Router protectedResources(){
        Router router = new Router();




        router.attach("/patient/{id}", PatientResource.class); //get, put, delete



        router.attach("/patient/measurements/dateFrom/{from}/dateTo/{to}",PatientsWithNoActivityResource.class ); //4. CHIEFDOCTOR

        router.attach("/consultation/{id}", GetOneConsultationResource.class);
        router.attach("/patient", PatientListResource.class); //get, post


        router.attach("/doctor", DoctorListResource.class);//get, post
        router.attach("/doctor/{id}", DoctorResource.class); // get one / post / put

        router.attach("/measurement/{id}", MeasurementResource.class); //get , post
      //  router.attach("/measurement", MeasurementResource.class);//post
        router.attach("/noPatient/noConsultation", PatientsWithNoDoctorResource.class);  //3 DOCTOR

        router.attach("/consultation/{id}/doctor/{doctorId}", ConsultationResource.class); // Doctor updates consultation

        router.attach("/consultation/doctor/{id}", PostConsultationResource.class);   // Doctor creates consultation
        router.attach("/consult/{id}", ConsultationResource.class);//delete

        router.attach("/doctor/{id}/consultation/{consultationId}", ConsultationResource.class);
        router.attach("/patient/{id}/consultation/{consultationId}", ConsultationResource.class);



        router.attach("/doctor/consultation/dateFrom/{from}/dateTo/{to}",DoctorsWithNoActivityResource.class ); //5.CHIEFDOCTOR

        router.attach("/consultation/doctor/{id}/dateFrom/{from}/dateTo/{to}", ConsultationListResource.class);

        router.attach("/measurementavg/patient/{id}/dateFrom/{from}/dateTo/{to}", AVGMeasurementResource.class); //GET AVG OF MEASUREMENTS OF A PATIENT

        router.attach("/consultation/{id}/patient/{patientId}", OneConsultationOfAPatientResource.class); // see a single consultation of a patient!!!!!!!!!

        router.attach("/measurement/{id}/patient/{patientId}", MeasurementListResource.class); // GET MEASUREMENTS OF A PATIENT
        router.attach("/patient/{id}/consultation", ConsultationsOfAPatientResource.class);// GET CONSULTATIONS OF A PATIENT

        router.attach("/measurement/patient/{id}/dateFrom/{from}/dateTo/{to}", PatientMeasurementsTimeRangeResource.class); //1.CHIEFDOCTOR a patient over a time range



        router.attach("/chiefDoctor", ChiefDoctorResource.class);
        router.attach("/chiefDoctor/{id}", ChiefDoctorResource.class);


        return router;

    }

}
