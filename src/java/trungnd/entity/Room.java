/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungnd.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author HOME
 */
@Entity
@Table(name = "Room", catalog = "HotelBooking", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Room.findAll", query = "SELECT r FROM Room r")
    , @NamedQuery(name = "Room.findByRoomID", query = "SELECT r FROM Room r WHERE r.roomID = :roomID")
    , @NamedQuery(name = "Room.findByType", query = "SELECT r FROM Room r WHERE r.type = :type")
    , @NamedQuery(name = "Room.findByPrice", query = "SELECT r FROM Room r WHERE r.price = :price")
    , @NamedQuery(name = "Room.findByQuantity", query = "SELECT r FROM Room r WHERE r.quantity = :quantity")})
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "RoomID", nullable = false)
    private Integer roomID;
    @Basic(optional = false)
    @Column(name = "Type", nullable = false, length = 50)
    private String type;
    @Basic(optional = false)
    @Column(name = "Price", nullable = false)
    private double price;
    @Basic(optional = false)
    @Column(name = "Quantity", nullable = false)
    private int quantity;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomID")
    private List<BookingDetail> bookingDetailList;
    @JoinColumn(name = "HotelID", referencedColumnName = "HotelID", nullable = false)
    @ManyToOne(optional = false)
    private Hotel hotelID;
    @JoinColumn(name = "Status", referencedColumnName = "StatusID", nullable = false)
    @ManyToOne(optional = false)
    private Status status;

    public Room() {
    }

    public Room(Integer roomID) {
        this.roomID = roomID;
    }

    public Room(Integer roomID, String type, double price, int quantity) {
        this.roomID = roomID;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
    }

    public Room(Integer roomID, String type, double price, int quantity, Hotel hotelID, Status status) {
        this.roomID = roomID;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.hotelID = hotelID;
        this.status = status;
    }
    
    

    public Integer getRoomID() {
        return roomID;
    }

    public void setRoomID(Integer roomID) {
        this.roomID = roomID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @XmlTransient
    public List<BookingDetail> getBookingDetailList() {
        return bookingDetailList;
    }

    public void setBookingDetailList(List<BookingDetail> bookingDetailList) {
        this.bookingDetailList = bookingDetailList;
    }

    public Hotel getHotelID() {
        return hotelID;
    }

    public void setHotelID(Hotel hotelID) {
        this.hotelID = hotelID;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roomID != null ? roomID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Room)) {
            return false;
        }
        Room other = (Room) object;
        if ((this.roomID == null && other.roomID != null) || (this.roomID != null && !this.roomID.equals(other.roomID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "trungnd.entity.Room[ roomID=" + roomID + " ]";
    }
    
}
