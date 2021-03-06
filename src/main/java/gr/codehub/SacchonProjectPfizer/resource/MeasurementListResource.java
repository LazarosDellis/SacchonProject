package gr.codehub.SacchonProjectPfizer.resource;

import gr.codehub.SacchonProjectPfizer.exception.AuthorizationException;
import gr.codehub.SacchonProjectPfizer.jpaUtil.JpaUtil;

import gr.codehub.SacchonProjectPfizer.model.Consultation;
import gr.codehub.SacchonProjectPfizer.model.Measurement;

import gr.codehub.SacchonProjectPfizer.repository.ConsultationRepository;
import gr.codehub.SacchonProjectPfizer.repository.MeasurementRepository;

import gr.codehub.SacchonProjectPfizer.representation.MeasurementRepresentation;
import gr.codehub.SacchonProjectPfizer.security.Shield;
import org.restlet.resource.*;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class MeasurementListResource extends ServerResource {

    private int id;
    private int patientId;


    @Override
    protected void doInit() {
        patientId = Integer.parseInt(getAttribute("patientId"));
        id = Integer.parseInt(getAttribute("id"));
    }


    @Get("json")
    public ApiResult<List<MeasurementRepresentation>> getMeasurement() {


////authorisation check
//        try {
//            ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);
//        } catch (AuthorizationException e) {
//            try {
//                ResourceUtils.checkRole(this, Shield.ROLE_DOCTOR);
//            } catch (AuthorizationException e1) {
//                try{
//                    ResourceUtils.checkRole(this, Shield.ROLE_ADMIN);
//                }catch (AuthorizationException e2) {
//                    return new ApiResult<>(null, 500, e.getMessage());
//                }
//            }
//        }

        EntityManager em = JpaUtil.getEntityManager();

        MeasurementRepository measurementRepository = new MeasurementRepository(em);


        List<Measurement> measurements = null;

        measurements = measurementRepository.getAllMeasurementsOfaPatient(patientId);

        em.close();

        List<MeasurementRepresentation> measurementRepresentationList =
                measurements.stream()
                        .map(MeasurementRepresentation::new)
                        .collect(toList());

        return new ApiResult<>(measurementRepresentationList, 200, "ok");
    }


    @Post("json")
    public ApiResult<MeasurementRepresentation> addMeasurement(MeasurementRepresentation measurementRepresentation) {


        //authorisation check
        try {
            ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);
        } catch (AuthorizationException e) {
            try {
                ResourceUtils.checkRole(this, Shield.ROLE_DOCTOR);
            } catch (AuthorizationException e1) {
                try{
                    ResourceUtils.checkRole(this, Shield.ROLE_ADMIN);
                }catch (AuthorizationException e2) {
                    return new ApiResult<>(null, 500, e.getMessage());
                }
            }
        }
        if (measurementRepresentation == null) return null;
        if (measurementRepresentation.getTypeOfMeasurement() == null) return null;

        Measurement measurement = measurementRepresentation.createMeasurement();
        EntityManager em = JpaUtil.getEntityManager();
        MeasurementRepository measurementRepository = new MeasurementRepository(em);
        measurementRepository.save(measurement);
        MeasurementRepresentation p = new MeasurementRepresentation(measurement);
        return new ApiResult<>(p, 200, "ok");
    }

    @Put("json")//update
    public ApiResult<MeasurementRepresentation> modifyMeasurement(MeasurementRepresentation measurementRepresentation) {

        //authorisation check
        try {
            ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);
        } catch (AuthorizationException e) {
            try {
                ResourceUtils.checkRole(this, Shield.ROLE_DOCTOR);
            } catch (AuthorizationException e1) {
                try{
                    ResourceUtils.checkRole(this, Shield.ROLE_ADMIN);
                }catch (AuthorizationException e2) {
                    return new ApiResult<>(null, 500, e.getMessage());
                }
            }
        }


        EntityManager em = JpaUtil.getEntityManager();
        MeasurementRepository measurementRepository = new MeasurementRepository(em);
        Measurement measurement = measurementRepository.read(id);

        measurement.setValueOfMeasurement(measurementRepresentation.getValueOfMeasurement());
        measurement.setTypeOfMeasurement(measurementRepresentation.getTypeOfMeasurement());
        measurement.setDate(measurementRepresentation.getDate());


        measurementRepository.save(measurement);

        MeasurementRepresentation measurementRepresentation1 = new MeasurementRepresentation(measurement);
        em.close();
        return new ApiResult<>(measurementRepresentation1, 200, "ok");

    }


    @Delete("txt")
    public boolean deleteMeasurement() {


        EntityManager em = JpaUtil.getEntityManager();
        MeasurementRepository measurementRepository = new MeasurementRepository(em);
        return measurementRepository.delete(id);
    }
}
