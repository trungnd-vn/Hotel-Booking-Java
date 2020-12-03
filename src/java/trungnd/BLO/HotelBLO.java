/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungnd.BLO;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import trungnd.entity.Hotel;

/**
 *
 * @author HOME
 */
public class HotelBLO implements Serializable {

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
    
    public List searchByLikeInput(String search) {
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT h FROM Hotel h Where h.name Like :name";
        Query query = em.createQuery(jpql);
        query.setParameter("name", "%" + search + "%");
        List resultList = query.getResultList();
        return resultList;
    }
    
    public List searchByLikeInputMore(String checkOutDate, String checkInDate, String roomType, String area, String hotelName) {
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT D" +
                    " FROM BookingDetail A JOIN Booking B ON A.BookingID = B.BookingID" +
                                                        " AND B.CheckOutDate <= :checkOutDate" +
                                                        " AND B.CheckInDate >= :checkInDate" +
                                            " JOIN Room C ON A.RoomID = C.RoomID" +
                                                        " AND C.Type LIKE :roomType" +
                                            " JOIN Hotel D ON C.HotelID = D.HotelID" +
                                                        " AND D.Area LIKE :area" +
                                                        " OR D.Name LIKE :hotelName";
        Query query = em.createQuery(jpql);
        query.setParameter("checkOutDate", checkOutDate);
        query.setParameter("checkInDate", checkInDate);
        query.setParameter("roomType", "%" + roomType + "%");
        query.setParameter("area", "%" + area + "%");
        query.setParameter("hotelName", "%" + hotelName + "%");
        List resultList = query.getResultList();
        return resultList;
    }
    
    public Hotel getHotelFromDB(int hotelID) {
        EntityManager em = emf.createEntityManager();
        Hotel hotel = null;
        String jpql = "Hotel.findByHotelID";
        Query query = em.createNamedQuery(jpql);
        query.setParameter("hotelID", hotelID);
        hotel = (Hotel) query.getSingleResult();
        return hotel;
    } 
    
    public List getListHotel(){
        EntityManager em = emf.createEntityManager();
        String jpql = "Hotel.findAll";
        Query query = em.createNamedQuery(jpql);
        List resultList = query.getResultList();
        return resultList;
    }
    
}
