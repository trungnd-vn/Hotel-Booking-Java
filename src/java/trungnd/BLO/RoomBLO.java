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
import trungnd.entity.Room;

/**
 *
 * @author HOME
 */
public class RoomBLO implements Serializable {

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
    
    public List<Room> getRoomByHotelID(Hotel id) {
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT r FROM Room r Where r.hotelID = :ID";
        Query query = em.createQuery(jpql);
        query.setParameter("ID", id);
        List<Room> resultList = query.getResultList();
        return resultList;
    }
    
    public Room getRoomFromDB (int id){
        EntityManager em = emf.createEntityManager();
        String jpql = "Room.findByRoomID";
        Query query = em.createNamedQuery(jpql);
        query.setParameter("roomID", id);
        Room room = (Room) query.getSingleResult();
        return room;
    }
    
    public List getListRoom(){
        EntityManager em = emf.createEntityManager();
        String jpql = "Room.findAll";
        Query query = em.createNamedQuery(jpql);
        List resultList = query.getResultList();
        return resultList;
    }
    
}
