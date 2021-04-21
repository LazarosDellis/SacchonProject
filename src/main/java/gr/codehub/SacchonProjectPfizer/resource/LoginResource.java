//package gr.codehub.SacchonProjectPfizer.resource;
//
//
//import gr.codehub.SacchonProjectPfizer.jpaUtil.JpaUtil;
//import gr.codehub.SacchonProjectPfizer.model.ChiefDoctor;
//import gr.codehub.SacchonProjectPfizer.model.Doctor;
//import gr.codehub.SacchonProjectPfizer.model.Measurement;
//import gr.codehub.SacchonProjectPfizer.model.Patient;
//import gr.codehub.SacchonProjectPfizer.repository.ChiefDoctorRepository;
//import gr.codehub.SacchonProjectPfizer.repository.DoctorRepository;
//import gr.codehub.SacchonProjectPfizer.repository.MeasurementRepository;
//import gr.codehub.SacchonProjectPfizer.repository.PatientRepository;
//import gr.codehub.SacchonProjectPfizer.representation.LogInRepresentation;
//import gr.codehub.SacchonProjectPfizer.representation.MeasurementRepresentation;
//import gr.codehub.SacchonProjectPfizer.security.Shield;
//import org.restlet.Request;
//import org.restlet.resource.Delete;
//import org.restlet.resource.Get;
//import org.restlet.resource.Post;
//import org.restlet.resource.ServerResource;
//
//import javax.persistence.EntityManager;
//
//public class LoginResource extends ServerResource  {
//
//
//
//
//    @Post("json")
//    public ApiResult<LogInRepresentation> add(LogInRepresentation logInRepresentationIn) {
//
//
//
//        if (logInRepresentationIn == null) return null;
//        else {
//            if (logInRepresentationIn = Shield.ROLE_PATIENT) return null;
//
//
//
//
//
//
//
//
//
//        }
//        EntityManager em = JpaUtil.getEntityManager();
//        int patientId = measurementRepresentationIn.getPatientId();
//        Patient patient = em.find(Patient.class, patientId);
//
//        Measurement measurement = measurementRepresentationIn.createMeasurement();
//        measurement.setPatient(patient);
//
//        MeasurementRepository measurementRepository = new MeasurementRepository(em);
//        measurementRepository.save(measurement);
//        MeasurementRepresentation p = new MeasurementRepresentation(measurement);
//        return new ApiResult<>(p, 200, "ok");
//    }
//
//
//}
