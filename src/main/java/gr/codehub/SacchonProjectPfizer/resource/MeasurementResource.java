package gr.codehub.SacchonProjectPfizer.resource;

import gr.codehub.SacchonProjectPfizer.jpaUtil.JpaUtil;

import gr.codehub.SacchonProjectPfizer.model.Measurement;
import gr.codehub.SacchonProjectPfizer.repository.MeasurementRepository;
import gr.codehub.SacchonProjectPfizer.representation.MeasurementRepresentation;
import org.restlet.resource.*;

import javax.persistence.EntityManager;

public class MeasurementResource extends ServerResource {

    private int id;

    @Override
    protected void doInit() {
        id = Integer.parseInt(getAttribute("id"));
    }

    @Get("json")
    public MeasurementRepresentation getMeasurement() {
        EntityManager em = JpaUtil.getEntityManager();
        MeasurementRepository measurementRepository = new MeasurementRepository(em);
        Measurement measurement = measurementRepository.read(id);
        MeasurementRepresentation measurementRepresentation2 = new MeasurementRepresentation(measurement);
        em.close();
        return measurementRepresentation2;

    }

    @Post("json")
    public MeasurementRepresentation add(MeasurementRepresentation measurementRepresentationIn){

        if (measurementRepresentationIn ==null) return null;
        if (measurementRepresentationIn.createMeasurement() == null) return null;

        Measurement measurement = measurementRepresentationIn.createMeasurement();
        EntityManager em = JpaUtil.getEntityManager();
        MeasurementRepository measurementRepository = new MeasurementRepository(em);
        measurementRepository.save(measurement);
        MeasurementRepresentation p = new MeasurementRepresentation(measurement);
        return p;
    }


    @Put("json")
    public MeasurementRepresentation putMeasurement(MeasurementRepresentation measurementRepresentation) {

        EntityManager em = JpaUtil.getEntityManager();
        MeasurementRepository measurementRepository = new MeasurementRepository(em);
        Measurement measurement = measurementRepository.read(id);

        measurement.setValueOfMeasurement(measurementRepresentation.createMeasurement().getValueOfMeasurement());
        measurement.setDate(measurementRepresentation.createMeasurement().getDate());
        measurement.setTypeOfMeasurement(measurementRepresentation.createMeasurement().getTypeOfMeasurement());
        measurementRepository.save(measurement);

        MeasurementRepresentation measurementRepresentation2 = new MeasurementRepresentation(measurement);
        em.close();
        return measurementRepresentation2;

    }





    @Delete("txt")
    public boolean deleteMeasurement() {
        EntityManager em = JpaUtil.getEntityManager();
        MeasurementRepository measurementRepository = new MeasurementRepository(em);
        return measurementRepository.delete(id);
    }

}

