package gr.codehub.SacchonProjectPfizer;

import gr.codehub.SacchonProjectPfizer.jpaUtil.JpaUtil;
import gr.codehub.SacchonProjectPfizer.services.Business;

import javax.persistence.EntityManager;

public class MainApp {

    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();
        Business.createData(em);
//     sss
        em.close();
    }
}
