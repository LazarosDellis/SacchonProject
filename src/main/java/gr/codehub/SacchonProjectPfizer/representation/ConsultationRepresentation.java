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


    // mappers
    public ConsultationRepresentation ( Consultation consultation) {
        if (consultation != null) {

            consult = consultation.getConsult();
            date = consultation.getDate();
            uri = "http://localhost:9000/v1/product/" + consultation.getId();
        }
    }

    public Consultation createConsultation() {
        Consultation consultation = new Consultation();
        consultation.setConsult(consult);
        consultation.setDate(date);

        return consultation;
    }


}
