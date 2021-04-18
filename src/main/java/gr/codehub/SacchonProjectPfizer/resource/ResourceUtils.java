package gr.codehub.SacchonProjectPfizer.resource;

import gr.codehub.SacchonProjectPfizer.exception.AuthorizationException;
import org.restlet.resource.ServerResource;

public class ResourceUtils {

    public static void checkRole(ServerResource serverResource, String role)  throws AuthorizationException {
//        if (!serverResource.isInRole(role)) {
//            throw new AuthorizationException( "You're not authorized to send this call.");
//        }
    }
}
