package services;

import models.Categorie;
import models.Orders;
import models.Product;
import models.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class HibernateTest {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("KillerApp");

    public static void create(Object order){
        // Create an EntityManager
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            // Save the student object
            manager.persist(order);

            // Commit the transaction
            transaction.commit();
        } catch (Exception ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            // Print the Exception
            ex.printStackTrace();
        } finally {
            // Close the EntityManager
            manager.close();
        }
    }

    public static Categorie getCategorie(int categorieID){
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        return manager.createNamedQuery("findOneCategorie", Categorie.class).setParameter("id", categorieID).getSingleResult();
    }
    public static Orders getOrder(int OrderID){
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        return manager.createNamedQuery("findOneOrder", Orders.class).setParameter("id", OrderID).getSingleResult();
    }
    public static Product getProduct(int ProductID){
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        return manager.createNamedQuery("findOneProduct", Product.class).setParameter("id", ProductID).getSingleResult();
    }
    public static User getUser(int userid){
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        return manager.createNamedQuery("findOneUser",User.class).setParameter("id", userid).getSingleResult();
    }
    public static void deleteUser(int userid){
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        manager.createNamedQuery("deleteOrderOwner").setParameter("id",userid);
        manager.createNamedQuery("deleteUserID").setParameter("id", userid);
    }

    public static List readAll() {

        List students = null;

        // Create an EntityManager
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            // Get a List of Students
            students = manager.createQuery("SELECT s FROM User s",
                    User.class).getResultList();

            // Commit the transaction
            transaction.commit();
        } catch (Exception ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            // Print the Exception
            ex.printStackTrace();
        } finally {
            // Close the EntityManager
            manager.close();
        }
        return students;
    }


    public static void delete(int id) {
        // Create an EntityManager
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            // Get the Student object
            User stu = manager.find(User.class, id);

            // Delete the student
            manager.remove(stu);

            // Commit the transaction
            transaction.commit();
        } catch (Exception ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            // Print the Exception
            ex.printStackTrace();
        } finally {
            // Close the EntityManager
            manager.close();
        }
    }

    public static void upate(int id, String naam, String telefoon, String adres, String woonplaats) {
        // Create an EntityManager
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            // Get the Student object
            User stu = manager.find(User.class, id);

            // Change the values
            stu.setNaam(naam);
            stu.setTelefoon(telefoon);
            stu.setAdres(adres);
            stu.setWoonplaats(woonplaats);

            // Update the student
            manager.persist(stu);

            // Commit the transaction
            transaction.commit();
        } catch (Exception ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            // Print the Exception
            ex.printStackTrace();
        } finally {
            // Close the EntityManager
            manager.close();
        }
    }
}