package gr.codehub.SacchonProjectPfizer.resource;

import gr.codehub.SacchonProjectPfizer.jpaUtil.JpaUtil;
import gr.codehub.SacchonProjectPfizer.model.Doctor;
import gr.codehub.SacchonProjectPfizer.model.Patient;
import gr.codehub.SacchonProjectPfizer.repository.DoctorRepository;
import gr.codehub.SacchonProjectPfizer.repository.PatientRepository;
import gr.codehub.SacchonProjectPfizer.representation.DoctorRepresentation;
import gr.codehub.SacchonProjectPfizer.representation.PatientRepresentation;
import org.restlet.data.Product;
import org.restlet.resource.*;

import javax.persistence.EntityManager;

public class PatientResource extends ServerResource {

    private int id;

    @Override
    protected void doInit() {
        id = Integer.parseInt(getAttribute("id"));
    }

    @Get("json")
    public PatientRepresentation getPatient() {
        EntityManager em = JpaUtil.getEntityManager();
        PatientRepository patientRepository = new PatientRepository(em);
        Patient patient = patientRepository.read(id);

        PatientRepresentation patientRepresentation = new PatientRepresentation(patient);
        em.close();
        return patientRepresentation;

    }


    @Post("json")
    public PatientRepresentation add(PatientRepresentation patientRepresentationIn){

        if (patientRepresentationIn ==null) return null;
        if (patientRepresentationIn.getFullName() == null) return null;

        Patient patient = patientRepresentationIn.createPatient();
        EntityManager em = JpaUtil.getEntityManager();
        PatientRepository patientRepository = new PatientRepository(em);
        patientRepository.save(patient);
        PatientRepresentation p = new PatientRepresentation(patient);
        return p;
    }

    @Put("json")
    public PatientRepresentation putPatient(PatientRepresentation patientRepresentation) {

        EntityManager em = JpaUtil.getEntityManager();
        PatientRepository productRepository = new PatientRepository(em);
        Patient patient = productRepository.read(id);

        patient.setMeasurements(patientRepresentation.getMeasurement());


        productRepository.save(patient);

        PatientRepresentation patientRepresentation2 = new PatientRepresentation(patient);
        em.close();
        return patientRepresentation2;
    }



    @Delete("txt")
    public boolean deletePatient() {
        EntityManager em = JpaUtil.getEntityManager();
        PatientRepository patientRepository = new PatientRepository(em);
        return patientRepository.delete(id);
    }

}
