package gr.codehub.SacchonProjectPfizer.representation;


import gr.codehub.SacchonProjectPfizer.model.Measurement;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Data
@NoArgsConstructor
public class MeasurementRepresentation {


    private int id;
    private Date date;

    private double valueOfMeasurement;
    private String typeOfMeasurement;
    private int patientId;
    private String uri;


    // mappers
    public MeasurementRepresentation(Measurement measurement) {
        if (measurement != null) {
            date = measurement.getDate();
            typeOfMeasurement = measurement.getTypeOfMeasurement();
            valueOfMeasurement = measurement.getValueOfMeasurement();
            if (measurement.getPatient() != null)
                patientId = measurement.getPatient().getId();
            uri = "http://localhost:9000/v1/measurement/" + measurement.getId();
        }


    }


    public Measurement createMeasurement() {
        Measurement measurement = new Measurement();
        measurement.setValueOfMeasurement(valueOfMeasurement);
        measurement.setTypeOfMeasurement(typeOfMeasurement);
        measurement.setDate(date);

        return measurement;
    }


}
