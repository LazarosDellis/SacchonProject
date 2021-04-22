package gr.codehub.SacchonProjectPfizer.representation;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.restlet.resource.Post;

@Data
@AllArgsConstructor
public class UserRepresentation {

    private int role;
    private int id;

}
