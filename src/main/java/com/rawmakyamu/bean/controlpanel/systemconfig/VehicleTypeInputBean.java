/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rawmakyamu.bean.controlpanel.systemconfig;

import com.rawmakyamu.util.mapping.Status;
import java.util.List;

/**
 *
 * @author chanuka
 */
public class VehicleTypeInputBean {

    private static final long serialVersionUID = 1L;
    private String id;
    private String description;
    private String status;
    private String priceKm;
    private String commission;
    private String priceMin;
    private String baseFare;
    private String personCapacity;
    private String message;
    private String defaultStatus;
    private List<Status> statusList;
    private String newvalue;
    private String oldvalue;
    /*-------for access control-----------*/
    private boolean vadd;
    private boolean vupdatebutt;
    private boolean vupdatelink;
    private boolean vdelete;
    private boolean vsearch;
    /*-------for access control-----------*/
    /*------------------------list data table  ------------------------------*/
    private List<VehicleTypeBean> gridModel;
    private Integer rows = 0;
    private Integer page = 0;
    private Integer total = 0;
    private Long records = 0L;
    private String sord;
    private String sidx;
    private String searchField;
    private String searchString;
    private String searchOper;
    private boolean loadonce = false;
    /*------------------------list data table  ------------------------------*/
    
    private String descriptionSearch;
    private String statusSearch;
    private String personCapacitySearch;
    private String baseFareSearch;
    private String commissionSearch;
    private String pricekmSearch;
    private String priceMinSearch;
    private String SearchAudit;
    private boolean search;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDefaultStatus() {
        return defaultStatus;
    }

    public void setDefaultStatus(String defaultStatus) {
        this.defaultStatus = defaultStatus;
    }

    public List<Status> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<Status> statusList) {
        this.statusList = statusList;
    }

    public String getNewvalue() {
        return newvalue;
    }

    public void setNewvalue(String newvalue) {
        this.newvalue = newvalue;
    }

    public String getOldvalue() {
        return oldvalue;
    }

    public void setOldvalue(String oldvalue) {
        this.oldvalue = oldvalue;
    }

    public boolean isVadd() {
        return vadd;
    }

    public void setVadd(boolean vadd) {
        this.vadd = vadd;
    }

    public boolean isVupdatebutt() {
        return vupdatebutt;
    }

    public void setVupdatebutt(boolean vupdatebutt) {
        this.vupdatebutt = vupdatebutt;
    }

    public boolean isVupdatelink() {
        return vupdatelink;
    }

    public void setVupdatelink(boolean vupdatelink) {
        this.vupdatelink = vupdatelink;
    }

    public boolean isVdelete() {
        return vdelete;
    }

    public void setVdelete(boolean vdelete) {
        this.vdelete = vdelete;
    }

    public boolean isVsearch() {
        return vsearch;
    }

    public void setVsearch(boolean vsearch) {
        this.vsearch = vsearch;
    }

    public List<VehicleTypeBean> getGridModel() {
        return gridModel;
    }

    public void setGridModel(List<VehicleTypeBean> gridModel) {
        this.gridModel = gridModel;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Long getRecords() {
        return records;
    }

    public void setRecords(Long records) {
        this.records = records;
    }

    public String getSord() {
        return sord;
    }

    public void setSord(String sord) {
        this.sord = sord;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public String getSearchOper() {
        return searchOper;
    }

    public void setSearchOper(String searchOper) {
        this.searchOper = searchOper;
    }

    public boolean isLoadonce() {
        return loadonce;
    }

    public void setLoadonce(boolean loadonce) {
        this.loadonce = loadonce;
    }

    public String getPersonCapacitySearch() {
        return personCapacitySearch;
    }

    public void setPersonCapacitySearch(String personCapacitySearch) {
        this.personCapacitySearch = personCapacitySearch;
    }

    public String getBaseFareSearch() {
        return baseFareSearch;
    }

    public void setBaseFareSearch(String baseFareSearch) {
        this.baseFareSearch = baseFareSearch;
    }

    public String getCommissionSearch() {
        return commissionSearch;
    }

    public void setCommissionSearch(String commissionSearch) {
        this.commissionSearch = commissionSearch;
    }

    public String getPricekmSearch() {
        return pricekmSearch;
    }

    public void setPricekmSearch(String pricekmSearch) {
        this.pricekmSearch = pricekmSearch;
    }

    public String getPriceMinSearch() {
        return priceMinSearch;
    }

    public void setPriceMinSearch(String priceMinSearch) {
        this.priceMinSearch = priceMinSearch;
    }


    public String getDescriptionSearch() {
        return descriptionSearch;
    }

    public void setDescriptionSearch(String descriptionSearch) {
        this.descriptionSearch = descriptionSearch;
    }

    public String getStatusSearch() {
        return statusSearch;
    }

    public void setStatusSearch(String statusSearch) {
        this.statusSearch = statusSearch;
    }

    public String getSearchAudit() {
        return SearchAudit;
    }

    public void setSearchAudit(String SearchAudit) {
        this.SearchAudit = SearchAudit;
    }

    public boolean isSearch() {
        return search;
    }

    public void setSearch(boolean search) {
        this.search = search;
    }

}
