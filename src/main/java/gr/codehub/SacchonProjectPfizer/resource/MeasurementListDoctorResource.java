package gr.codehub.SacchonProjectPfizer.resource;

import gr.codehub.SacchonProjectPfizer.exception.AuthorizationException;
import gr.codehub.SacchonProjectPfizer.jpaUtil.JpaUtil;
import gr.codehub.SacchonProjectPfizer.model.Measurement;
import gr.codehub.SacchonProjectPfizer.model.Patient;
import gr.codehub.SacchonProjectPfizer.repository.MeasurementRepository;
import gr.codehub.SacchonProjectPfizer.representation.MeasurementRepresentation;
import gr.codehub.SacchonProjectPfizer.security.Shield;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class MeasurementListDoctorResource extends ServerResource {

    private int doctorId;

    @Override
    protected void doInit() {


    }

    @Get("json")
    public ApiResult<List<MeasurementRepresentation>> getMeasurement() {

        //authorisation check
//        try {
//            ResourceUtils.checkRole(this, Shield.ROLE_DOCTOR);
//        } catch (AuthorizationException e) {
//            return new ApiResult<>(null, 500, e.getMessage());
//        }

        EntityManager em = JpaUtil.getEntityManager();

        MeasurementRepository measurementRepository = new MeasurementRepository(em);


        List<Measurement> measurements = null;

        measurements = measurementRepository.getMeasurementsByDoctorId(doctorId);

        em.close();

        List<MeasurementRepresentation> measurementRepresentationList =
                measurements.stream()
                        .map(MeasurementRepresentation::new)
                        .collect(toList());

        return new ApiResult<>(measurementRepresentationList, 200, "ok");
    }






}
