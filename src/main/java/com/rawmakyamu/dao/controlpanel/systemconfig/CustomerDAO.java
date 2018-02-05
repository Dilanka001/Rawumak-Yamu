/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rawmakyamu.dao.controlpanel.systemconfig;

import com.rawmakyamu.bean.controlpanel.systemconfig.CustomerBean;
import com.rawmakyamu.bean.controlpanel.systemconfig.CustomerInputBean;
import com.rawmakyamu.dao.common.CommonDAO;
import com.rawmakyamu.util.common.HibernateInit;
import com.rawmakyamu.util.mapping.Customer;
import com.rawmakyamu.util.mapping.Status;
import com.rawmakyamu.util.mapping.Systemaudit;
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
public class CustomerDAO {

    public List<CustomerBean> getSearchList(CustomerInputBean inputBean, int max, int first, String orderBy) throws Exception {

        List<CustomerBean> dataList = new ArrayList<CustomerBean>();
        Session session = null;
        try {

            if (orderBy.equals("") || orderBy == null) {
                orderBy = "order by u.createdtime desc";
            }

            long count = 0;
            String where = this.makeWhereClause(inputBean);

            session = HibernateInit.sessionFactory.openSession();

            String sqlCount = "select count(id) from Customer as u where " + where;
            Query queryCount = session.createQuery(sqlCount);

            Iterator itCount = queryCount.iterate();
            count = (Long) itCount.next();

            if (count > 0) {

                String sqlSearch = "from Customer u where " + where + orderBy;
                Query querySearch = session.createQuery(sqlSearch);
                querySearch.setMaxResults(max);
                querySearch.setFirstResult(first);

                Iterator it = querySearch.iterate();

                while (it.hasNext()) {

                    CustomerBean customer = new CustomerBean();
                    Customer v = (Customer) it.next();

                    try {
                        customer.setId(v.getId().toString());
                    } catch (NullPointerException npe) {
                        customer.setId("--");
                    }
                    try {
                        customer.setFirstName(v.getFirstname());
                    } catch (NullPointerException npe) {
                        customer.setFirstName("--");
                    }
                    try {
                        customer.setLastName(v.getLastname().toString());
                    } catch (NullPointerException npe) {
                        customer.setLastName("--");
                    }
                    try {
                        customer.setNic(v.getNic().toString());
                    } catch (NullPointerException npe) {
                        customer.setNic("--");
                    }
                    try {
                        customer.setMobile(v.getMobile().toString());
                    } catch (NullPointerException npe) {
                        customer.setMobile("--");
                    }
                    try {
                        customer.setEmail(v.getEmail().toString());
                    } catch (NullPointerException npe) {
                        customer.setEmail("--");
                    }
                    try {
                        customer.setStatus(v.getStatus().getDescription());
                    } catch (NullPointerException npe) {
                        customer.setStatus("--");
                    }
                    try {
                        if (v.getCreatedtime() != null && !v.getCreatedtime().toString().isEmpty()) {
                            customer.setCreatedtime(v.getCreatedtime().toString());
//                            vehicle.setCreatedtime(v.getCreatedtime().toString().substring(0, 19));
                        } else {
                            customer.setCreatedtime("--");
                        }

                    } catch (NullPointerException npe) {
                        customer.setCreatedtime("--");
                    }

                    customer.setFullCount(count);

                    dataList.add(customer);
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

    private String makeWhereClause(CustomerInputBean inputBean) {
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
        if (inputBean.getNicSearch() != null && !inputBean.getNicSearch().isEmpty()) {
            where += " and lower(u.nic) like lower('%" + inputBean.getNicSearch() + "%')";
        }
        if (inputBean.getMobile() != null && !inputBean.getMobile().isEmpty()) {
            where += " and lower(u.mobile) like lower('%" + inputBean.getMobile() + "%')";
        }
        if (inputBean.getEmailSearch() != null && !inputBean.getEmailSearch().isEmpty()) {
            where += " and lower(u.email) like lower('%" + inputBean.getEmailSearch() + "%')";
        }
        return where;
    }

    public String deleteCustomer(CustomerInputBean inputBean, Systemaudit audit) throws Exception {
        Session session = null;
        Transaction txn = null;
        String message = "";
        try {
            session = HibernateInit.sessionFactory.openSession();
            txn = session.beginTransaction();
            Date sysDate = CommonDAO.getSystemDate(session);

            Customer u = (Customer) session.get(Customer.class, new BigDecimal(inputBean.getId()));
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

    public Customer findCustomerById(String id) throws Exception {
        Customer v = null;
        Session session = null;
        try {
            session = HibernateInit.sessionFactory.openSession();

            String sql = "from Customer as u where u.id=:id";
            Query query = session.createQuery(sql).setString("id", id);
            v = (Customer) query.list().get(0);

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

    public String updateCustomer(CustomerInputBean inputBean, Systemaudit audit) throws Exception {

        Session session = null;
        Transaction txn = null;
        String message = "";

        try {
            session = HibernateInit.sessionFactory.openSession();
            txn = session.beginTransaction();
            Date sysDate = CommonDAO.getSystemDate(session);

            Customer u = (Customer) session.get(Customer.class, new BigDecimal(inputBean.getId()));

            if (u != null) {

                String oldValue = u.getId()
                        + "|" + u.getFirstname()
                        + "|" + u.getLastname()
                        + "|" + u.getNic()
                        + "|" + u.getMobile()
                        + "|" + u.getEmail()
                        + "|" + u.getStatus().getDescription();

                u.setFirstname(inputBean.getFirstName().trim());
                u.setLastname(inputBean.getLastName());
                u.setNic(inputBean.getNic());
                u.setMobile(inputBean.getMobile());
                u.setEmail(inputBean.getEmail());

                Status st = (Status) session.get(Status.class, inputBean.getStatus().trim());
                u.setStatus(st);

                u.setLastupdatedtime(sysDate);
//                u.setLastupdateduser(audit.getLastupdateduser());

                String newV = u.getId()
                        + "|" + inputBean.getFirstName()
                        + "|" + inputBean.getLastName()
                        + "|" + inputBean.getNic()
                        + "|" + inputBean.getMobile()
                        + "|" + inputBean.getEmail()
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
