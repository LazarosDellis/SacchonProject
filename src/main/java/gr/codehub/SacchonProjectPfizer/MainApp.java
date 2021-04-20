package gr.codehub.SacchonProjectPfizer;

import gr.codehub.SacchonProjectPfizer.jpaUtil.JpaUtil;
import gr.codehub.SacchonProjectPfizer.router.CustomRouter;

import gr.codehub.SacchonProjectPfizer.security.CorsFilter;
import gr.codehub.SacchonProjectPfizer.security.Shield;
import gr.codehub.SacchonProjectPfizer.services.Business;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;

import org.restlet.data.Protocol;
import org.restlet.engine.Engine;

import org.restlet.routing.Router;
import org.restlet.security.ChallengeAuthenticator;
import org.restlet.security.Role;

import javax.persistence.EntityManager;

import java.util.logging.Logger;

public class MainApp extends Application {

    public static final Logger LOGGER = Engine.getLogger(MainApp.class);

    public MainApp(){
        setName("WebAPITutorial");
        setDescription("Full Web API tutorial");

        getRoles().add(new Role(this, Shield.ROLE_ADMIN));
        getRoles().add(new Role(this, Shield.ROLE_DOCTOR));
        getRoles().add(new Role(this, Shield.ROLE_PATIENT));

    }


    public static void main(String[] args) throws Exception{

        LOGGER.info("Contacts application starting...");

        EntityManager em = JpaUtil.getEntityManager();
       // Business.testMe(em);

        em.close();


        Component c = new Component();
        c.getServers().add(Protocol.HTTP, 9000);
        c.getDefaultHost().attach("/v1", new MainApp());
        c.start();

        LOGGER.info("Sample Web API started");
        LOGGER.info("URL: http://localhost:9000/v1/patient");
    }

    @Override
    public Restlet createInboundRoot() {

        CustomRouter customRouter = new CustomRouter(this);
        Shield shield = new Shield(this);

        Router publicRouter = customRouter.publicResources();
        ChallengeAuthenticator apiGuard = shield.createApiGuard();
        // Create the api router, protected by a guard

        Router apiRouter = customRouter.protectedResources();
        apiGuard.setNext(apiRouter);

        publicRouter.attachDefault(apiGuard);

        // return publicRouter;

        CorsFilter corsFilter = new CorsFilter(this);
        return corsFilter.createCorsFilter(publicRouter);
    }
}
