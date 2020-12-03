/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungnd.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "Account", catalog = "HotelBooking", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a")
    , @NamedQuery(name = "Account.findByUserID", query = "SELECT a FROM Account a WHERE a.userID = :userID")
    , @NamedQuery(name = "Account.findByPassword", query = "SELECT a FROM Account a WHERE a.password = :password")
    , @NamedQuery(name = "Account.findByName", query = "SELECT a FROM Account a WHERE a.name = :name")
    , @NamedQuery(name = "Account.findByPhone", query = "SELECT a FROM Account a WHERE a.phone = :phone")
    , @NamedQuery(name = "Account.findByCreateDate", query = "SELECT a FROM Account a WHERE a.createDate = :createDate")
    , @NamedQuery(name = "Account.findByRole", query = "SELECT a FROM Account a WHERE a.role = :role")})
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "UserID", nullable = false, length = 50)
    private String userID;
    @Basic(optional = false)
    @Column(name = "Password", nullable = false, length = 100)
    private String password;
    @Basic(optional = false)
    @Column(name = "Name", nullable = false, length = 100)
    private String name;
    @Basic(optional = false)
    @Column(name = "Phone", nullable = false, length = 10)
    private String phone;
    @Basic(optional = false)
    @Lob
    @Column(name = "Address", nullable = false, length = 2147483647)
    private String address;
    @Basic(optional = false)
    @Column(name = "CreateDate", nullable = false, length = 10)
    private String createDate;
    @Basic(optional = false)
    @Column(name = "Role", nullable = false, length = 10)
    private String role;
    @JoinColumn(name = "Status", referencedColumnName = "StatusID", nullable = false)
    @ManyToOne(optional = false)
    private Status status;
    @OneToMany(mappedBy = "userID")
    private List<Booking> bookingList;

    public Account() {
    }

    public Account(String userID) {
        this.userID = userID;
    }

    public Account(String userID, String password, String name, String phone, String address, String createDate, String role) {
        this.userID = userID;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.createDate = createDate;
        this.role = role;
    }

    public Account(String userID, String password, String name, String phone, String address, String createDate, String role, Status status) {
        this.userID = userID;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.createDate = createDate;
        this.role = role;
        this.status = status;
    }
    
    

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @XmlTransient
    public List<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userID != null ? userID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.userID == null && other.userID != null) || (this.userID != null && !this.userID.equals(other.userID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "trungnd.entity.Account[ userID=" + userID + " ]";
    }
    
}
