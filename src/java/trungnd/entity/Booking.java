/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungnd.entity;

import java.io.Serializable;
import java.util.ArrayList;
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
@Table(name = "Booking", catalog = "HotelBooking", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Booking.findAll", query = "SELECT b FROM Booking b")
    , @NamedQuery(name = "Booking.findByBookingID", query = "SELECT b FROM Booking b WHERE b.bookingID = :bookingID")
    , @NamedQuery(name = "Booking.findByTotal", query = "SELECT b FROM Booking b WHERE b.total = :total")
    , @NamedQuery(name = "Booking.findByBookingDate", query = "SELECT b FROM Booking b WHERE b.bookingDate = :bookingDate")
    , @NamedQuery(name = "Booking.findByCheckInDate", query = "SELECT b FROM Booking b WHERE b.checkInDate = :checkInDate")
    , @NamedQuery(name = "Booking.findByCheckOutDate", query = "SELECT b FROM Booking b WHERE b.checkOutDate = :checkOutDate")})
public class Booking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "BookingID", nullable = false)
    private Integer bookingID;
    @Basic(optional = false)
    @Column(name = "Total", nullable = false)
    private int total;
    @Basic(optional = false)
    @Column(name = "BookingDate", nullable = false, length = 10)
    private String bookingDate;
    @Basic(optional = false)
    @Column(name = "CheckInDate", nullable = false, length = 10)
    private String checkInDate;
    @Basic(optional = false)
    @Column(name = "CheckOutDate", nullable = false, length = 10)
    private String checkOutDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookingID")
    private List<BookingDetail> bookingDetailList;
    @JoinColumn(name = "UserID", referencedColumnName = "UserID")
    @ManyToOne
    private Account userID;
    @JoinColumn(name = "Status", referencedColumnName = "StatusID", nullable = false)
    @ManyToOne(optional = false)
    private Status status;

    public Booking() {
        bookingDetailList = new ArrayList<>();
    }

    public Booking(Integer bookingID) {
        this.bookingID = bookingID;
        bookingDetailList = new ArrayList<>();
    }

    public Booking(Integer bookingID, int total, String bookingDate, String checkInDate, String checkOutDate) {
        this.bookingID = bookingID;
        this.total = total;
        this.bookingDate = bookingDate;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        bookingDetailList = new ArrayList<>();
    }
    
    

    public Booking(Integer bookingID, int total, String bookingDate, String checkInDate, String checkOutDate, Account userID, Status status) {
        this.bookingID = bookingID;
        this.total = total;
        this.bookingDate = bookingDate;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.userID = userID;
        this.status = status;
        bookingDetailList = new ArrayList<>();
    }

    public Booking(int total, String bookingDate, String checkInDate, String checkOutDate, Account userID, Status status) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

    public Integer getBookingID() {
        return bookingID;
    }

    public void setBookingID(Integer bookingID) {
        this.bookingID = bookingID;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    @XmlTransient
    public List<BookingDetail> getBookingDetailList() {
        return bookingDetailList;
    }

    public void setBookingDetailList(List<BookingDetail> bookingDetailList) {
        this.bookingDetailList = bookingDetailList;
    }

    public Account getUserID() {
        return userID;
    }

    public void setUserID(Account userID) {
        this.userID = userID;
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
        hash += (bookingID != null ? bookingID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Booking)) {
            return false;
        }
        Booking other = (Booking) object;
        if ((this.bookingID == null && other.bookingID != null) || (this.bookingID != null && !this.bookingID.equals(other.bookingID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "trungnd.entity.Booking[ bookingID=" + bookingID + " ]";
    }
    
}
