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
public class DriverInputBean {

    private static final long serialVersionUID = 1L;
    private String id;
    private String firstName;
    private String lastName;
    private String dob;
    private String address;
    private String nic;
    private String licence;
    private String expiryDate;
    private String tel1;
    private String tel2;
    private String email;
    private String account;
    private String status;
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
    private List<DriverBean> gridModel;
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

    private String firstNameSearch;
    private String lastNameSearch;
    private String addressSearch;
    private String nicSearch;
    private String licenceSearch;
    private String emailSearch;
    private String accountSearch;
    private String statusSearch;
    private String SearchAudit;
    private boolean search;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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

    public List<DriverBean> getGridModel() {
        return gridModel;
    }

    public void setGridModel(List<DriverBean> gridModel) {
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



    public String getStatusSearch() {
        return statusSearch;
    }

    public void setStatusSearch(String statusSearch) {
        this.statusSearch = statusSearch;
    }

    public String getFirstNameSearch() {
        return firstNameSearch;
    }

    public void setFirstNameSearch(String firstNameSearch) {
        this.firstNameSearch = firstNameSearch;
    }

    public String getLastNameSearch() {
        return lastNameSearch;
    }

    public void setLastNameSearch(String lastNameSearch) {
        this.lastNameSearch = lastNameSearch;
    }

    public String getAddressSearch() {
        return addressSearch;
    }

    public void setAddressSearch(String addressSearch) {
        this.addressSearch = addressSearch;
    }

    public String getNicSearch() {
        return nicSearch;
    }

    public void setNicSearch(String nicSearch) {
        this.nicSearch = nicSearch;
    }

    public String getLicenceSearch() {
        return licenceSearch;
    }

    public void setLicenceSearch(String licenceSearch) {
        this.licenceSearch = licenceSearch;
    }

    public String getEmailSearch() {
        return emailSearch;
    }

    public void setEmailSearch(String emailSearch) {
        this.emailSearch = emailSearch;
    }

    public String getAccountSearch() {
        return accountSearch;
    }

    public void setAccountSearch(String accountSearch) {
        this.accountSearch = accountSearch;
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
