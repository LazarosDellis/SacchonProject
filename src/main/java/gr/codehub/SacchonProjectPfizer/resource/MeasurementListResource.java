package gr.codehub.SacchonProjectPfizer.resource;

import gr.codehub.SacchonProjectPfizer.jpaUtil.JpaUtil;
import gr.codehub.SacchonProjectPfizer.model.Consultation;
import gr.codehub.SacchonProjectPfizer.model.Measurement;
import gr.codehub.SacchonProjectPfizer.repository.ConsultationRepository;
import gr.codehub.SacchonProjectPfizer.repository.MeasurementRepository;
import gr.codehub.SacchonProjectPfizer.representation.ConsultationRepresentation;
import gr.codehub.SacchonProjectPfizer.representation.MeasurementRepresentation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;

public class MeasurementListResource extends ServerResource {


    @Post("json")
    public MeasurementRepresentation add(MeasurementRepresentation measurementRepresentation){

        if (measurementRepresentation ==null) return null;
        if (measurementRepresentation.getTypeOfMeasurement() == null) return null;

        Measurement measurement = measurementRepresentation.createMeasurement();
        EntityManager em = JpaUtil.getEntityManager();
        MeasurementRepository measurementRepository = new MeasurementRepository(em);
        measurementRepository.save(measurement);
        MeasurementRepresentation p = new MeasurementRepresentation(measurement);
        return p;
    }
}
