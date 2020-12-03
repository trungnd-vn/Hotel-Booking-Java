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
import javax.persistence.Lob;
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
@Table(name = "Hotel", catalog = "HotelBooking", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hotel.findAll", query = "SELECT h FROM Hotel h")
    , @NamedQuery(name = "Hotel.findByHotelID", query = "SELECT h FROM Hotel h WHERE h.hotelID = :hotelID")
    , @NamedQuery(name = "Hotel.findByName", query = "SELECT h FROM Hotel h WHERE h.name = :name")
    , @NamedQuery(name = "Hotel.findByArea", query = "SELECT h FROM Hotel h WHERE h.area = :area")})
public class Hotel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "HotelID", nullable = false)
    private Integer hotelID;
    @Basic(optional = false)
    @Column(name = "Name", nullable = false, length = 50)
    private String name;
    @Basic(optional = false)
    @Column(name = "Area", nullable = false, length = 50)
    private String area;
    @Lob
    @Column(name = "Image", length = 2147483647)
    private String image;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hotelID")
    private List<Room> roomList;

    public Hotel() {
    }

    public Hotel(Integer hotelID) {
        this.hotelID = hotelID;
    }

    public Hotel(Integer hotelID, String name, String area) {
        this.hotelID = hotelID;
        this.name = name;
        this.area = area;
    }

    public Hotel(Integer hotelID, String name, String area, String image) {
        this.hotelID = hotelID;
        this.name = name;
        this.area = area;
        this.image = image;
    }

    
    
    public Integer getHotelID() {
        return hotelID;
    }

    public void setHotelID(Integer hotelID) {
        this.hotelID = hotelID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @XmlTransient
    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hotelID != null ? hotelID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hotel)) {
            return false;
        }
        Hotel other = (Hotel) object;
        if ((this.hotelID == null && other.hotelID != null) || (this.hotelID != null && !this.hotelID.equals(other.hotelID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "trungnd.entity.Hotel[ hotelID=" + hotelID + " ]";
    }
    
}
