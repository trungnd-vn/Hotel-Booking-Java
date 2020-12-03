/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungnd.BLO;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import trungnd.entity.Account;
import trungnd.entity.Status;

/**
 *
 * @author HOME
 */
public class AccountBLO implements Serializable {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("J3.L.P0012HotelBookingJPAPU");

    public void persist(Object object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public boolean checkLogin(String username, String password) {
        EntityManager em = emf.createEntityManager();

        String jpql = "Select a from Account a Where a.userID = :userID And a.password = :password";

        Query query = em.createQuery(jpql);
        query.setParameter("userID", username);
        query.setParameter("password", password);

        try {
            query.getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    public Account loginPage(String userID) {
        Account acc = null;
        EntityManager em = emf.createEntityManager();

        Query queryLogin = em.createNamedQuery("Account.findByUserID");
        queryLogin.setParameter("userID", userID);

        try {
            acc = (Account) queryLogin.getSingleResult();
            return acc;
        } catch (NoResultException e) {
            return null;
        }
    }

    public boolean insertAccount(String userID, String password, String name, String phone, String address, String createDate, String role, Status status) {
        EntityManager em = emf.createEntityManager();
        Account user = em.find(Account.class, userID);
        if (user == null) {
            user = new Account(userID, password, name, phone, address, createDate, role, status);
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            return true;
        }
        return false;
    }
    
}
