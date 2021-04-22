package gr.codehub.SacchonProjectPfizer.resource;

import gr.codehub.SacchonProjectPfizer.exception.AuthorizationException;
import gr.codehub.SacchonProjectPfizer.jpaUtil.JpaUtil;
import gr.codehub.SacchonProjectPfizer.model.Doctor;
import gr.codehub.SacchonProjectPfizer.model.Patient;
import gr.codehub.SacchonProjectPfizer.repository.DoctorRepository;
import gr.codehub.SacchonProjectPfizer.repository.PatientRepository;

import gr.codehub.SacchonProjectPfizer.representation.PatientRepresentation;
import gr.codehub.SacchonProjectPfizer.security.Shield;

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

        //authorisation check

            try {
                ResourceUtils.checkRole(this, Shield.ROLE_DOCTOR);
            } catch (AuthorizationException e1) {
                try{
                    ResourceUtils.checkRole(this, Shield.ROLE_ADMIN);
                }catch (AuthorizationException e2) {

                }
            }




        EntityManager em = JpaUtil.getEntityManager();
        PatientRepository patientRepository = new PatientRepository(em);
        Patient patient = patientRepository.read(id);

        PatientRepresentation patientRepresentation = new PatientRepresentation(patient);
        em.close();
        return  (patientRepresentation);

    }


    @Post("json")
    public PatientRepresentation add(PatientRepresentation patientRepresentationIn){

        //authorisation check

        try {
            ResourceUtils.checkRole(this, Shield.ROLE_DOCTOR);
        } catch (AuthorizationException e1) {
            try{
                ResourceUtils.checkRole(this, Shield.ROLE_ADMIN);
            }catch (AuthorizationException e2) {

            }
        }



        if (patientRepresentationIn ==null) return null;
        if (patientRepresentationIn.getFullName() == null) return null;

        EntityManager em = JpaUtil.getEntityManager();

        int doctorId = patientRepresentationIn.getDoctorId();
        Doctor doctor = em.find(Doctor.class, doctorId);

        Patient patient = patientRepresentationIn.createPatient();
        patient.setDoctor(doctor);


        PatientRepository patientRepository = new PatientRepository(em);
        patientRepository.save(patient);
        PatientRepresentation p = new PatientRepresentation(patient);
        return p;
    }

    @Put("json")
    public PatientRepresentation putPatient(PatientRepresentation patientRepresentation) {

        EntityManager em = JpaUtil.getEntityManager();

        int doctorId = patientRepresentation.getDoctorId();
        Doctor doctor = em.find(Doctor.class, doctorId);

        PatientRepository patientRepository = new PatientRepository(em);
        Patient patient = patientRepository.read(id);


        patient.setUsername(patientRepresentation.getUsername());
        patient.setEmail(patientRepresentation.getEmail());
        patient.setFullName(patientRepresentation.getFullName());
        patient.setPassword(patientRepresentation.getPassword());
        patient.setDoctor(doctor);


        patientRepository.save(patient);

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
