package services;

import models.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class UserService {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("KillerApp");

    public static User getUser(int userid){
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        return manager.createNamedQuery("findOneUser",User.class).setParameter("id", userid).getSingleResult();
    }
    public static List<User> getusers(){
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        return manager.createNamedQuery("findAllUsers", User.class).getResultList();
    }
}
