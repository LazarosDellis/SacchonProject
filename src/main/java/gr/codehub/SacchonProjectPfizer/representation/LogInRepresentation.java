//package gr.codehub.SacchonProjectPfizer.representation;
//
//import gr.codehub.SacchonProjectPfizer.exception.AuthorizationException;
//import gr.codehub.SacchonProjectPfizer.jpaUtil.JpaUtil;
//import gr.codehub.SacchonProjectPfizer.model.LogIn;
//import gr.codehub.SacchonProjectPfizer.model.Measurement;
//import gr.codehub.SacchonProjectPfizer.model.Patient;
//import gr.codehub.SacchonProjectPfizer.repository.MeasurementRepository;
//import gr.codehub.SacchonProjectPfizer.resource.ApiResult;
//import gr.codehub.SacchonProjectPfizer.resource.LoginResource;
//import gr.codehub.SacchonProjectPfizer.resource.ResourceUtils;
//import gr.codehub.SacchonProjectPfizer.security.Shield;
//import lombok.extern.java.Log;
//import org.restlet.resource.Post;
//
//import javax.persistence.EntityManager;
//
//public class LogInRepresentation {
//
//    private String username;
//    private String password;
//
//    public LogInRepresentation(LogIn login) {
//        if (login != null) {
//            username = login.getUsername();
//            password = login.getPassword();
//
//        }
//
//
//    }
//
//    public LogIn logIn() {
//
//
//        LogIn logIn = new LogIn();
//        logIn.setUsername(username);
//        logIn.setPassword(password);
//
//
//        return logIn;
//    }
//
//}
