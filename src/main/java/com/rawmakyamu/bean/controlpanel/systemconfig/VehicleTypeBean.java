/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rawmakyamu.bean.controlpanel.systemconfig;

/**
 *
 * @author chanuka
 */
public class VehicleTypeBean {

    private static final long serialVersionUID = 1L;
    private String id;
    private String description;
    private String status;
    private String priceKm;
    private String priceMin;
    private String baseFare;
    private String personCapacity;
    private String commission;
    private String createdtime;
    private String lastupdatedtime;
    private long fullCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriceKm() {
        return priceKm;
    }

    public void setPriceKm(String priceKm) {
        this.priceKm = priceKm;
    }

    public String getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(String priceMin) {
        this.priceMin = priceMin;
    }

    public String getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(String baseFare) {
        this.baseFare = baseFare;
    }

    public String getPersonCapacity() {
        return personCapacity;
    }

    public void setPersonCapacity(String personCapacity) {
        this.personCapacity = personCapacity;
    }

    public String getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(String createdtime) {
        this.createdtime = createdtime;
    }

    public String getLastupdatedtime() {
        return lastupdatedtime;
    }

    public void setLastupdatedtime(String lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    public long getFullCount() {
        return fullCount;
    }

    public void setFullCount(long fullCount) {
        this.fullCount = fullCount;
    }
    
    
    
}
