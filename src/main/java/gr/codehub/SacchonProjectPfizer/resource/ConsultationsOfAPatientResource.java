package gr.codehub.SacchonProjectPfizer.resource;

import gr.codehub.SacchonProjectPfizer.exception.AuthorizationException;
import gr.codehub.SacchonProjectPfizer.jpaUtil.JpaUtil;
import gr.codehub.SacchonProjectPfizer.model.Consultation;
import gr.codehub.SacchonProjectPfizer.repository.ConsultationRepository;
import gr.codehub.SacchonProjectPfizer.representation.ConsultationRepresentation;
import gr.codehub.SacchonProjectPfizer.security.Shield;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ConsultationsOfAPatientResource extends ServerResource {
    private int patientId;
    private int id;

    @Override
    protected void doInit() {
    patientId = Integer.parseInt(getAttribute("id"));

    }

    @Get("json")
    public ApiResult<List<ConsultationRepresentation>> getConsultation() {

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

        ConsultationRepository consultationRepository = new ConsultationRepository(em);

        List<Consultation> consultations = null;


        consultations = consultationRepository.getConsultationsOfAPatient(patientId);




        em.close();

        List<ConsultationRepresentation> consultationRepresentationList =
                consultations.stream()
                        .map(ConsultationRepresentation::new)
                        .collect(toList());

        return new ApiResult<>(consultationRepresentationList, 200, "ok");

    }
}
