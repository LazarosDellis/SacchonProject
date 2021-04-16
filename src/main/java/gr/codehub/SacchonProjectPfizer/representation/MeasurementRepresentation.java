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
    private String uri;


    // mappers
    public MeasurementRepresentation ( Measurement measurement) {
        if (measurement != null) {

            typeOfMeasurement = measurement.getTypeOfMeasurement();
            valueOfMeasurement = measurement.getValueOfMeasurement();
            date = measurement.getDate();
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
