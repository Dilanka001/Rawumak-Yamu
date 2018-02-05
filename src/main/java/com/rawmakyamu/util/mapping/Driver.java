package com.rawmakyamu.util.mapping;
// Generated Feb 5, 2018 4:36:38 PM by Hibernate Tools 3.6.0

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Driver generated by hbm2java
 */
@Entity
@Table(name = "DRIVER")
public class Driver implements java.io.Serializable {

    private BigDecimal id;
    private Status status;
    private String firstname;
    private String lastname;
    private Date dateofbirth;
    private String address;
    private String nic;
    private String licence;
    private Date expirydate;
    private String tel1;
    private String tel2;
    private String email;
    private String account;
    private Date createdtime;
    private Date lastupdatedtime;
    private String lastupdateduser;
    private Blob img;

    public Driver() {
    }

    public Driver(BigDecimal id) {
        this.id = id;
    }

    public Driver(BigDecimal id, Status status, String firstname, String lastname, Date dateofbirth, String address, String nic, String licence, Date expirydate, String tel1, String tel2, String email, String account, Date createdtime, Date lastupdatedtime, String lastupdateduser, Blob img) {
        this.id = id;
        this.status = status;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateofbirth = dateofbirth;
        this.address = address;
        this.nic = nic;
        this.licence = licence;
        this.expirydate = expirydate;
        this.tel1 = tel1;
        this.tel2 = tel2;
        this.email = email;
        this.account = account;
        this.createdtime = createdtime;
        this.lastupdatedtime = lastupdatedtime;
        this.lastupdateduser = lastupdateduser;
        this.img = img;
    }

    @Id

    @SequenceGenerator(name = "SequenceIdGenerator", sequenceName = "DRIVER_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SequenceIdGenerator")
    @Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
    public BigDecimal getId() {
        return this.id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STATUS")
    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Column(name = "FIRSTNAME", length = 64)
    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Column(name = "LASTNAME", length = 64)
    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "DATEOFBIRTH", length = 7)
    public Date getDateofbirth() {
        return this.dateofbirth;
    }

    public void setDateofbirth(Date dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    @Column(name = "ADDRESS")
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "NIC", length = 16)
    public String getNic() {
        return this.nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    @Column(name = "LICENCE", length = 20)
    public String getLicence() {
        return this.licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "EXPIRYDATE", length = 7)
    public Date getExpirydate() {
        return this.expirydate;
    }

    public void setExpirydate(Date expirydate) {
        this.expirydate = expirydate;
    }

    @Column(name = "TEL1", length = 20)
    public String getTel1() {
        return this.tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    @Column(name = "TEL2", length = 20)
    public String getTel2() {
        return this.tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    @Column(name = "EMAIL", length = 128)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "ACCOUNT", length = 20)
    public String getAccount() {
        return this.account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "CREATEDTIME", length = 7)
    public Date getCreatedtime() {
        return this.createdtime;
    }

    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "LASTUPDATEDTIME", length = 7)
    public Date getLastupdatedtime() {
        return this.lastupdatedtime;
    }

    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    @Column(name = "LASTUPDATEDUSER", length = 64)
    public String getLastupdateduser() {
        return this.lastupdateduser;
    }

    public void setLastupdateduser(String lastupdateduser) {
        this.lastupdateduser = lastupdateduser;
    }

    @Column(name = "IMG")
    public Blob getImg() {
        return this.img;
    }

    public void setImg(Blob img) {
        this.img = img;
    }

}