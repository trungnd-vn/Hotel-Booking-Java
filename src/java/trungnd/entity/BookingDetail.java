/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungnd.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HOME
 */
@Entity
@Table(name = "BookingDetail", catalog = "HotelBooking", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BookingDetail.findAll", query = "SELECT b FROM BookingDetail b")
    , @NamedQuery(name = "BookingDetail.findByBookingDetailID", query = "SELECT b FROM BookingDetail b WHERE b.bookingDetailID = :bookingDetailID")
    , @NamedQuery(name = "BookingDetail.findByQuantity", query = "SELECT b FROM BookingDetail b WHERE b.quantity = :quantity")
    , @NamedQuery(name = "BookingDetail.findByTotal", query = "SELECT b FROM BookingDetail b WHERE b.total = :total")})
public class BookingDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "BookingDetailID", nullable = false)
    private Integer bookingDetailID;
    @Basic(optional = false)
    @Column(name = "Quantity", nullable = false)
    private int quantity;
    @Basic(optional = false)
    @Column(name = "Total", nullable = false)
    private double total;
    @JoinColumn(name = "BookingID", referencedColumnName = "BookingID", nullable = false)
    @ManyToOne(optional = false)
    private Booking bookingID;
    @JoinColumn(name = "RoomID", referencedColumnName = "RoomID", nullable = false)
    @ManyToOne(optional = false)
    private Room roomID;

    public BookingDetail() {
    }

    public BookingDetail(Integer bookingDetailID) {
        this.bookingDetailID = bookingDetailID;
    }

    public BookingDetail(Integer bookingDetailID, int quantity, double total) {
        this.bookingDetailID = bookingDetailID;
        this.quantity = quantity;
        this.total = total;
    }

    public BookingDetail(Integer bookingDetailID, int quantity, double total, Booking bookingID, Room roomID) {
        this.bookingDetailID = bookingDetailID;
        this.quantity = quantity;
        this.total = total;
        this.bookingID = bookingID;
        this.roomID = roomID;
    }

    public BookingDetail(Integer bookingDetailID, int quantity, double total, Room roomID) {
        this.bookingDetailID = bookingDetailID;
        this.quantity = quantity;
        this.total = total;
        this.roomID = roomID;
    }
    
    

    public Integer getBookingDetailID() {
        return bookingDetailID;
    }

    public void setBookingDetailID(Integer bookingDetailID) {
        this.bookingDetailID = bookingDetailID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Booking getBookingID() {
        return bookingID;
    }

    public void setBookingID(Booking bookingID) {
        this.bookingID = bookingID;
    }

    public Room getRoomID() {
        return roomID;
    }

    public void setRoomID(Room roomID) {
        this.roomID = roomID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bookingDetailID != null ? bookingDetailID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BookingDetail)) {
            return false;
        }
        BookingDetail other = (BookingDetail) object;
        if ((this.bookingDetailID == null && other.bookingDetailID != null) || (this.bookingDetailID != null && !this.bookingDetailID.equals(other.bookingDetailID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "trungnd.entity.BookingDetail[ bookingDetailID=" + bookingDetailID + " ]";
    }
    
}
