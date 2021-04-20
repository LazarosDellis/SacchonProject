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



        router.attach("/patient", PatientListResource.class); //get, post
        router.attach("/patient/{id}", PatientResource.class); //get, put, delete

        router.attach("/doctor", DoctorListResource.class);//get, post
        router.attach("/doctor/{id}", DoctorResource.class); // get one / post / put

        router.attach("/measurement/{id}", MeasurementResource.class); //get , post



        router.attach("/consultation/{id}/doctor/{doctorId}", ConsultationResource.class);

        router.attach("/doctor/{id}/consultation/{consultationId}", ConsultationResource.class);
        router.attach("/patient/{id}/consultation/{consultationId}", ConsultationResource.class);

        router.attach("/patient/measurements/dateFrom/{from}/dateTo/{to}",PatientsWithNoActivityResource.class );

        router.attach("/doctor/consultation/dateFrom/{from}/dateTo/{to}",DoctorsWithNoActivityResource.class );

        router.attach("/consultation/doctor/{id}/dateFrom/{from}/dateTo/{to}", ConsultationListResource.class);

        router.attach("/measurementavg/patient/{id}/dateFrom/{from}/dateTo/{to}", AVGMeasurementResource.class); //GET AVG OF MEASUREMENTS OF A PATIENT



        router.attach("/measurement/{id}/patient/{patientId}", MeasurementListResource.class); // GET MEASUREMENTS OF A PATIENT
        router.attach("/patient/{id}/consultation", ConsultationsOfAPatientResource.class);// GET CONSULTATIONS OF A PATIENT

        router.attach("/measurement/patient/{id}/dateFrom/{from}/dateTo/{to}", PatientMeasurementsTimeRangeResource.class); //1.CHIEFDOCTOR a patient over a time range

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




        router.attach("/noPatient/noConsultation", PatientsWithNoDoctorResource.class);  //3 CHIEFDOCTOR

        //find patients that have not had a consultation in the last month
        //consult provide advice to a patient for the upcoming month (name
        //of medication and dosage)

        //Patients don't get doctorId

        return router;
    }


    public Router protectedResources(){
        Router router = new Router();



        router.attach("/consultation/{id}/patient/{patientId}", ConsultationResource.class);







        router.attach("/chiefDoctor", ChiefDoctorResource.class);
        router.attach("/chiefDoctor/{id}", ChiefDoctorResource.class);


        return router;

    }

}
