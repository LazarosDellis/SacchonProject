package gr.codehub.SacchonProjectPfizer.representation;


import gr.codehub.SacchonProjectPfizer.model.Consultation;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ConsultationRepresentation {

    private int id;
    private String consult;

    private Date date;

    private String uri;
    private int patientId;
    private int doctorId;


    // mappers
    public ConsultationRepresentation(Consultation consultation) {
        if (consultation != null) {
            id = consultation.getId();
            date = consultation.getDate();
            consult = consultation.getConsult();
            if (consultation.getDoctor() != null)
                doctorId = consultation.getDoctor().getId();
           if (consultation.getPatient() != null)
                patientId = consultation.getPatient().getId();
            uri = "http://localhost:9000/v1/consultation/" ;
            //+ consultation.getId()
        }
    }

    public Consultation createConsultation() {
        Consultation consultation = new Consultation();
        consultation.setId(id);
        consultation.setConsult(consult);
        consultation.setDate(date);


        return consultation;
    }


}
