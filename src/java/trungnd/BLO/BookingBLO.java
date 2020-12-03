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
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import trungnd.entity.Account;
import trungnd.entity.Booking;
import trungnd.entity.BookingDetail;
import trungnd.entity.Room;
import trungnd.entity.Status;

/**
 *
 * @author HOME
 */
public class BookingBLO implements Serializable {

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
    
    public int getBookingID() {
        EntityManager em = emf.createEntityManager();
        String sql = "select COUNT(bookingID)+1 from Booking";
        Query query = em.createNativeQuery(sql);
        em.getTransaction().begin();
        int count = (int) query.getSingleResult();
        em.getTransaction().commit();
        return count;
    }

    public int getBookingDetailID() {
        EntityManager em = emf.createEntityManager();
        String sql = "select COUNT(bookingDetailID)+1 from BookingDetail";
        Query query = em.createNativeQuery(sql);
        em.getTransaction().begin();
        int count = (int) query.getSingleResult();
        em.getTransaction().commit();
        return count;
    }

    public boolean addToDB(Integer bookingID, int total, String bookingDate, String checkInDate, String checkOutDate, Account userID, Status status) {
        EntityManager em = emf.createEntityManager();
        System.out.println(bookingDate);
        Booking booking = new Booking(bookingID, total, bookingDate, checkInDate, checkOutDate, userID, status);
        em.getTransaction().begin();
        em.persist(booking);
        em.getTransaction().commit();
        return true;
    }

    public boolean addDetailToDB(Integer bookingDetailID, int quantity, int total, Booking bookingID, Room roomID) {
        EntityManager em = emf.createEntityManager();
        BookingDetail bookingDetail = new BookingDetail(bookingDetailID, quantity, total, bookingID, roomID);
        em.getTransaction().begin();
        em.persist(bookingDetail);
        em.getTransaction().commit();
        return true;
    }
    
}
