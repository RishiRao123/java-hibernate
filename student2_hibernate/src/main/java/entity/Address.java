package entity;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="address")
public class Address {
    @Id
    @GeneratedValue
    @Column(name="address_id")
    private int addid;
    @Column(length=50)
    private String street;
    @Column(name="city")
    private String city;
    private boolean isOpen;
    @Transient
    private double x;
    @Temporal(TemporalType.DATE)
    private Date date;
    @Lob
    private byte[] images;

    public Address(int addid, String street, String city, boolean isOpen, double x, Date date, byte[] images) {
        super();
        this.addid = addid;
        this.street = street;
        this.city = city;
        this.isOpen = isOpen;
        this.x = x;
        this.date = date;
        this.images = images;
    }
    public Address() {
        super();
        // TODO Auto-generated constructor stub
    }

    public int getAddid() {
        return addid;
    }
    public void setAddid(int addid) {
        this.addid = addid;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public boolean isOpen() {
        return isOpen;
    }
    public void setOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }
    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public byte[] getImages() {
        return images;
    }
    public void setImages(byte[] images) {
        this.images = images;
    }
    @Override
    public String toString() {
        return "entity.Address [addid=" + addid + ", street=" + street + ", city=" + city + ", isOpen=" + isOpen + ", x=" + x
                + ", date=" + date + ", images=" + Arrays.toString(images) + "]";
    }

}