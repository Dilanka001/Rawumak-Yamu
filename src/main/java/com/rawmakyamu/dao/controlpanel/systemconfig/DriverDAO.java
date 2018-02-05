/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rawmakyamu.dao.controlpanel.systemconfig;

import com.rawmakyamu.bean.controlpanel.systemconfig.DriverBean;
import com.rawmakyamu.bean.controlpanel.systemconfig.DriverInputBean;
import com.rawmakyamu.bean.controlpanel.systemconfig.VehicleTypeBean;
import com.rawmakyamu.bean.controlpanel.systemconfig.VehicleTypeInputBean;
import com.rawmakyamu.dao.common.CommonDAO;
import com.rawmakyamu.util.common.HibernateInit;
import com.rawmakyamu.util.mapping.Driver;
import com.rawmakyamu.util.mapping.Status;
import com.rawmakyamu.util.mapping.Systemaudit;
import com.rawmakyamu.util.mapping.VehicleType;
import com.rawmakyamu.util.varlist.CommonVarList;
import com.rawmakyamu.util.varlist.MessageVarList;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author chanuka
 */
public class DriverDAO {

    public List<DriverBean> getSearchList(DriverInputBean inputBean, int max, int first, String orderBy) throws Exception {

        List<DriverBean> dataList = new ArrayList<DriverBean>();
        Session session = null;
        try {

            if (orderBy.equals("") || orderBy == null) {
                orderBy = "order by u.createdtime desc";
            }

            long count = 0;
            String where = this.makeWhereClause(inputBean);

            session = HibernateInit.sessionFactory.openSession();

            String sqlCount = "select count(id) from Driver as u where " + where;
            Query queryCount = session.createQuery(sqlCount);

            Iterator itCount = queryCount.iterate();
            count = (Long) itCount.next();

            if (count > 0) {

                String sqlSearch = "from Driver u where " + where + orderBy;
                Query querySearch = session.createQuery(sqlSearch);
                querySearch.setMaxResults(max);
                querySearch.setFirstResult(first);

                Iterator it = querySearch.iterate();

                while (it.hasNext()) {

                    DriverBean driver = new DriverBean();
                    Driver v = (Driver) it.next();

                    try {
                        driver.setId(v.getId().toString());
                    } catch (NullPointerException npe) {
                        driver.setId("--");
                    }
                    try {
                        driver.setFirstName(v.getFirstname());
                    } catch (NullPointerException npe) {
                        driver.setFirstName("--");
                    }
                    try {
                        driver.setLastName(v.getLastname().toString());
                    } catch (NullPointerException npe) {
                        driver.setLastName("--");
                    }
                    try {
                        driver.setAddress(v.getAddress().toString());
                    } catch (NullPointerException npe) {
                        driver.setAddress("--");
                    }
                    try {
                        driver.setNic(v.getNic().toString());
                    } catch (NullPointerException npe) {
                        driver.setNic("--");
                    }
                    try {
                        driver.setLicence(v.getLicence().toString());
                    } catch (NullPointerException npe) {
                        driver.setLicence("--");
                    }
                    try {
                        driver.setEmail(v.getEmail().toString());
                    } catch (NullPointerException npe) {
                        driver.setEmail("--");
                    }
                    try {
                        driver.setAccount(v.getAccount().toString());
                    } catch (NullPointerException npe) {
                        driver.setAccount("--");
                    }
                    try {
                        driver.setStatus(v.getStatus().getDescription());
                    } catch (NullPointerException npe) {
                        driver.setStatus("--");
                    }
                    try {
                        if (v.getCreatedtime() != null && !v.getCreatedtime().toString().isEmpty()) {
                            driver.setCreatedtime(v.getCreatedtime().toString());
//                            vehicle.setCreatedtime(v.getCreatedtime().toString().substring(0, 19));
                        } else {
                            driver.setCreatedtime("--");
                        }

                    } catch (NullPointerException npe) {
                        driver.setCreatedtime("--");
                    }

                    driver.setFullCount(count);

                    dataList.add(driver);
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                session.flush();
                session.close();
            } catch (Exception e) {
                throw e;
            }
        }
        return dataList;
    }

    private String makeWhereClause(DriverInputBean inputBean) {
        String where = "1=1";

        if (inputBean.getFirstNameSearch() != null && !inputBean.getFirstNameSearch().isEmpty()) {
            where += " and lower(u.firstname) like lower('%" + inputBean.getFirstNameSearch() + "%')";
        }
        if (inputBean.getStatusSearch() != null && !inputBean.getStatusSearch().isEmpty()) {
            where += " and u.status.statuscode = '" + inputBean.getStatusSearch() + "'";
        }
        if (inputBean.getLastNameSearch() != null && !inputBean.getLastNameSearch().isEmpty()) {
            where += " and lower(u.lastname)  like lower('%" + inputBean.getLastNameSearch() + "%')";
        }
        if (inputBean.getAddressSearch() != null && !inputBean.getAddressSearch().isEmpty()) {
            where += " and lower(u.address) like lower('%" + inputBean.getAddressSearch() + "%')";
        }
        if (inputBean.getNicSearch() != null && !inputBean.getNicSearch().isEmpty()) {
            where += " and lower(u.nic) like lower('%" + inputBean.getNicSearch() + "%')";
        }
        if (inputBean.getLicenceSearch() != null && !inputBean.getLicenceSearch().isEmpty()) {
            where += " and lower(u.licence) like lower('%" + inputBean.getLicenceSearch() + "%')";
        }
        if (inputBean.getEmailSearch() != null && !inputBean.getEmailSearch().isEmpty()) {
            where += " and lower(u.email) like lower('%" + inputBean.getEmailSearch() + "%')";
        }
        if (inputBean.getAccountSearch() != null && !inputBean.getAccountSearch().isEmpty()) {
            where += " and lower(u.account) like lower('%" + inputBean.getAccountSearch() + "%')";
        }
        return where;
    }

    public String deleteDriver(DriverInputBean inputBean, Systemaudit audit) throws Exception {
        Session session = null;
        Transaction txn = null;
        String message = "";
        try {
            session = HibernateInit.sessionFactory.openSession();
            txn = session.beginTransaction();
            Date sysDate = CommonDAO.getSystemDate(session);

            Driver u = (Driver) session.get(Driver.class, new BigDecimal(inputBean.getId()));
            if (u != null) {

                long count = 0;

                String sqlCount = "select count(taskcode) from Pagetask as u where u.task.taskcode=:taskcode";
                Query queryCount = session.createQuery(sqlCount).setString("taskcode", inputBean.getId().trim());

                Iterator itCount = queryCount.iterate();
                count = (Long) itCount.next();

                if (count > 0) {
                    message = MessageVarList.COMMON_NOT_DELETE;
                } else {
                    audit.setCreatetime(sysDate);
                    audit.setLastupdatedtime(sysDate);

                    session.save(audit);
                    session.delete(u);
                    txn.commit();
                }

            } else {
                message = MessageVarList.COMMON_NOT_EXISTS;
            }
        } catch (Exception e) {
            if (txn != null) {
                txn.rollback();
            }
            throw e;
        } finally {
            try {
                session.flush();
                session.close();
            } catch (Exception e) {
                throw e;
            }
        }
        return message;
    }

    public String insertDriver(DriverInputBean inputBean, Systemaudit audit) throws Exception {
        Session session = null;
        Transaction txn = null;
        String message = "";
        try {
            session = HibernateInit.sessionFactory.openSession();
            Date sysDate = CommonDAO.getSystemDate(session);
            txn = session.beginTransaction();

            String sql = "from Driver as u where lower(u.nic)=:nic ";
            Query query = session.createQuery(sql).setString("nic", inputBean.getNic().trim().toLowerCase());
            if (query.list() == null || query.list().size() < 1) {

                Driver v = new Driver();
                v.setFirstname(inputBean.getFirstName());
                v.setLastname(inputBean.getLastName());
                v.setAddress(inputBean.getAddress());
                v.setNic(inputBean.getNic());
                v.setLicence(inputBean.getLicence());
                v.setEmail(inputBean.getEmail());
                v.setAccount(inputBean.getAccount());

                Status st = (Status) session.get(Status.class, inputBean.getStatus().trim());
                v.setStatus(st);

                v.setCreatedtime(sysDate);
                v.setLastupdatedtime(sysDate);
                v.setLastupdateduser(audit.getLastupdateduser());

                String newV = v.getFirstname()
                        + "|" + v.getStatus().getDescription();

                audit.setNewvalue(newV);
                audit.setCreatetime(sysDate);
                audit.setLastupdatedtime(sysDate);

                session.save(audit);
                session.save(v);

                txn.commit();

            } else {

                long count = 0;
                String sqlCount = "select count(id) from Driver as u where u.status=:statuscode AND lower(u.nic)=:nic";
                Query queryCount = session.createQuery(sqlCount).setString("statuscode", CommonVarList.STATUS_DELETE)
                        .setString("nic", inputBean.getNic().trim().toLowerCase());

                Iterator itCount = queryCount.iterate();
                count = (Long) itCount.next();

                if (count > 0) {
                    message = "$" + inputBean.getNic().trim();
                } else {
                    message = MessageVarList.COMMON_ALREADY_EXISTS;
                }
            }

        } catch (Exception e) {
            if (txn != null) {
                txn.rollback();
            }
            throw e;
        } finally {
            try {
                session.flush();
                session.close();
            } catch (Exception e) {
                throw e;
            }
        }
        return message;
    }

    public Driver findDriverById(String id) throws Exception {
        Driver v = null;
        Session session = null;
        try {
            session = HibernateInit.sessionFactory.openSession();

            String sql = "from Driver as u where u.id=:id";
            Query query = session.createQuery(sql).setString("id", id);
            v = (Driver) query.list().get(0);

        } catch (Exception e) {
            throw e;
        } finally {
            try {
                session.flush();
                session.close();
            } catch (Exception e) {
                throw e;
            }
        }
        return v;

    }

    public String updateDriver(DriverInputBean inputBean, Systemaudit audit) throws Exception {

        Session session = null;
        Transaction txn = null;
        String message = "";

        try {
            session = HibernateInit.sessionFactory.openSession();
            txn = session.beginTransaction();
            Date sysDate = CommonDAO.getSystemDate(session);

            Driver u = (Driver) session.get(Driver.class, new BigDecimal(inputBean.getId()));

            if (u != null) {

                String oldValue = u.getId()
                        + "|" + u.getFirstname()
                        + "|" + u.getLastname()
                        + "|" + u.getAddress()
                        + "|" + u.getNic()
                        + "|" + u.getLicence()
                        + "|" + u.getEmail()
                        + "|" + u.getAccount()
                        + "|" + u.getStatus().getDescription();

                u.setFirstname(inputBean.getFirstName().trim());
                u.setLastname(inputBean.getLastName());
                u.setAddress(inputBean.getAddress());
                u.setNic(inputBean.getNic());
                u.setLicence(inputBean.getLicence());
                u.setEmail(inputBean.getEmail());
                u.setAccount(inputBean.getAccount());

                Status st = (Status) session.get(Status.class, inputBean.getStatus().trim());
                u.setStatus(st);

                u.setLastupdatedtime(sysDate);
                u.setLastupdateduser(audit.getLastupdateduser());

                String newV = u.getId()
                        + "|" + inputBean.getFirstName()
                        + "|" + inputBean.getLastName()
                        + "|" + inputBean.getAddress()
                        + "|" + inputBean.getNic()
                        + "|" + inputBean.getLicence()
                        + "|" + inputBean.getEmail()
                        + "|" + inputBean.getAccount()
                        + "|" + inputBean.getStatus();

                audit.setOldvalue(oldValue);
                audit.setNewvalue(newV);
                audit.setCreatetime(sysDate);
                audit.setLastupdatedtime(sysDate);

                session.save(audit);
                session.update(u);

                txn.commit();
            } else {
                message = MessageVarList.COMMON_NOT_EXISTS;
            }
        } catch (Exception e) {
            if (txn != null) {
                txn.rollback();
            }
            throw e;
        } finally {
            try {
                session.flush();
                session.close();
            } catch (Exception e) {
                throw e;
            }
        }
        return message;
    }
}
