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


    // mappers
    public ConsultationRepresentation ( Consultation consultation) {
        if (consultation != null) {
            date = consultation.getDate();
            if(consultation.getPatient() !=null)
                patientId = consultation.getPatient().getId();
            uri = "http://localhost:9000/v1/cosultation/" + consultation.getId();
        }
    }

    public Consultation createConsultation() {
        Consultation consultation = new Consultation();
        consultation.setConsult(consult);
        consultation.setDate(date);

        return consultation;
    }


}
