package gr.codehub.SacchonProjectPfizer.resource;

import gr.codehub.SacchonProjectPfizer.exception.AuthorizationException;
import gr.codehub.SacchonProjectPfizer.jpaUtil.JpaUtil;

import gr.codehub.SacchonProjectPfizer.model.Measurement;
import gr.codehub.SacchonProjectPfizer.model.Patient;
import gr.codehub.SacchonProjectPfizer.repository.MeasurementRepository;
import gr.codehub.SacchonProjectPfizer.representation.MeasurementRepresentation;
import gr.codehub.SacchonProjectPfizer.security.Shield;
import org.restlet.resource.*;

import javax.persistence.EntityManager;

public class MeasurementResource extends ServerResource {

    private int id;

    @Override
    protected void doInit() {
        id = Integer.parseInt(getAttribute("id"));
    }

    @Get("json")
    public ApiResult<MeasurementRepresentation> getMeasurement() {

        //authorisation check
        try {
            ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);
            // efboleumena try
        } catch (AuthorizationException e) {
            return new ApiResult<>(null, 500, e.getMessage());
        }


        EntityManager em = JpaUtil.getEntityManager();
        MeasurementRepository measurementRepository = new MeasurementRepository(em);
        Measurement measurement = measurementRepository.read(id);
        MeasurementRepresentation measurementRepresentation2 = new MeasurementRepresentation(measurement);
        em.close();
        return new ApiResult<>(measurementRepresentation2, 200, "ok");

    }

    @Post("json")
    public ApiResult<MeasurementRepresentation> add(MeasurementRepresentation measurementRepresentationIn) {

        //authorisation check
        try {
            ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);
        } catch (AuthorizationException e) {
            return new ApiResult<>(null, 500, e.getMessage());
        }

        if (measurementRepresentationIn == null) return null;
        if (measurementRepresentationIn.createMeasurement() == null) return null;

        EntityManager em = JpaUtil.getEntityManager();
        int patientId = measurementRepresentationIn.getPatientId();
        Patient patient = em.find(Patient.class, patientId);

        Measurement measurement = measurementRepresentationIn.createMeasurement();
        measurement.setPatient(patient);

        MeasurementRepository measurementRepository = new MeasurementRepository(em);
        measurementRepository.save(measurement);
        MeasurementRepresentation p = new MeasurementRepresentation(measurement);
        return new ApiResult<>(p, 200, "ok");
    }


    @Put("json")
    public ApiResult<MeasurementRepresentation> putMeasurement(MeasurementRepresentation measurementRepresentation) {

        //authorisation check
        try {
            ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);
        } catch (AuthorizationException e) {
            return new ApiResult<>(null, 500, e.getMessage());
        }

        EntityManager em = JpaUtil.getEntityManager();


        int patientId = measurementRepresentation.getPatientId();
        Patient patient = em.find(Patient.class, patientId);

        MeasurementRepository measurementRepository = new MeasurementRepository(em);
        Measurement measurement = measurementRepository.read(id);

        measurement.setValueOfMeasurement(measurementRepresentation.createMeasurement().getValueOfMeasurement());
        measurement.setDate(measurementRepresentation.createMeasurement().getDate());
        measurement.setTypeOfMeasurement(measurementRepresentation.createMeasurement().getTypeOfMeasurement());
        measurement.setPatient(patient);
        measurementRepository.save(measurement);

        MeasurementRepresentation measurementRepresentation2 = new MeasurementRepresentation(measurement);
        em.close();
        return new ApiResult<>(measurementRepresentation2, 200, "ok");

    }


    @Delete("txt")
    public boolean deleteMeasurement() {
        EntityManager em = JpaUtil.getEntityManager();
        MeasurementRepository measurementRepository = new MeasurementRepository(em);
        return measurementRepository.delete(id);
    }

}

