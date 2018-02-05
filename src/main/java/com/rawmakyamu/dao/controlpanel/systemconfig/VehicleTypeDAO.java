/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rawmakyamu.dao.controlpanel.systemconfig;

import com.rawmakyamu.bean.controlpanel.systemconfig.VehicleTypeBean;
import com.rawmakyamu.bean.controlpanel.systemconfig.VehicleTypeInputBean;
import com.rawmakyamu.bean.controlpanel.usermanagement.TaskBean;
import com.rawmakyamu.bean.controlpanel.usermanagement.TaskInputBean;
import com.rawmakyamu.dao.common.CommonDAO;
import com.rawmakyamu.util.common.HibernateInit;
import com.rawmakyamu.util.mapping.Status;
import com.rawmakyamu.util.mapping.Systemaudit;
import com.rawmakyamu.util.mapping.Task;
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
public class VehicleTypeDAO {

    public List<VehicleTypeBean> getSearchList(VehicleTypeInputBean inputBean, int max, int first, String orderBy) throws Exception {

        List<VehicleTypeBean> dataList = new ArrayList<VehicleTypeBean>();
        Session session = null;
        try {

            if (orderBy.equals("") || orderBy == null) {
                orderBy = "order by u.createdtime desc";
            }

            long count = 0;
            String where = this.makeWhereClause(inputBean);

            session = HibernateInit.sessionFactory.openSession();

            String sqlCount = "select count(id) from VehicleType as u where " + where;
            Query queryCount = session.createQuery(sqlCount);

            Iterator itCount = queryCount.iterate();
            count = (Long) itCount.next();

            if (count > 0) {

                String sqlSearch = "from VehicleType u where " + where + orderBy;
                Query querySearch = session.createQuery(sqlSearch);
                querySearch.setMaxResults(max);
                querySearch.setFirstResult(first);

                Iterator it = querySearch.iterate();

                while (it.hasNext()) {

                    VehicleTypeBean vehicle = new VehicleTypeBean();
                    VehicleType v = (VehicleType) it.next();

                    try {
                        vehicle.setId(v.getId().toString());
                    } catch (NullPointerException npe) {
                        vehicle.setId("--");
                    }
                    try {
                        vehicle.setDescription(v.getDescription());
                    } catch (NullPointerException npe) {
                        vehicle.setDescription("--");
                    }
                    try {
                        vehicle.setBaseFare(v.getBaseFare().toString());
                    } catch (NullPointerException npe) {
                        vehicle.setBaseFare("--");
                    }
                    try {
                        vehicle.setPersonCapacity(v.getPersonCapacity().toString());
                    } catch (NullPointerException npe) {
                        vehicle.setPersonCapacity("--");
                    }
                    try {
                        vehicle.setPriceKm(v.getPriceKm().toString());
                    } catch (NullPointerException npe) {
                        vehicle.setPriceKm("--");
                    }
                    try {
                        vehicle.setPriceMin(v.getPriceMin().toString());
                    } catch (NullPointerException npe) {
                        vehicle.setPriceMin("--");
                    }
                    try {
                        vehicle.setCommission(v.getCommission().toString());
                    } catch (NullPointerException npe) {
                        vehicle.setCommission("--");
                    }
                    try {
                        vehicle.setStatus(v.getStatus().getDescription());
                    } catch (NullPointerException npe) {
                        vehicle.setStatus("--");
                    }
                    try {
                        if (v.getCreatedtime() != null && !v.getCreatedtime().toString().isEmpty()) {
                            vehicle.setCreatedtime(v.getCreatedtime().toString());
//                            vehicle.setCreatedtime(v.getCreatedtime().toString().substring(0, 19));
                        } else {
                            vehicle.setCreatedtime("--");
                        }

                    } catch (NullPointerException npe) {
                        vehicle.setCreatedtime("--");
                    }

                    vehicle.setFullCount(count);

                    dataList.add(vehicle);
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

    private String makeWhereClause(VehicleTypeInputBean inputBean) {
        String where = "1=1";

        if (inputBean.getDescriptionSearch() != null && !inputBean.getDescriptionSearch().isEmpty()) {
            where += " and lower(u.description) like lower('%" + inputBean.getDescriptionSearch() + "%')";
        }
        if (inputBean.getStatusSearch() != null && !inputBean.getStatusSearch().isEmpty()) {
            where += " and u.status.statuscode = '" + inputBean.getStatusSearch() + "'";
        }
        if (inputBean.getPersonCapacitySearch() != null && !inputBean.getPersonCapacitySearch().isEmpty()) {
            where += " and u.personCapacity  like ('%" + inputBean.getPersonCapacitySearch() + "%')";
        }
        if (inputBean.getBaseFareSearch() != null && !inputBean.getBaseFareSearch().isEmpty()) {
            where += " and u.baseFare like ('%" + inputBean.getBaseFareSearch() + "%')";
        }
        if (inputBean.getCommissionSearch() != null && !inputBean.getCommissionSearch().isEmpty()) {
            where += " and u.commission like ('%" + inputBean.getCommissionSearch() + "%')";
        }
        if (inputBean.getPricekmSearch() != null && !inputBean.getPricekmSearch().isEmpty()) {
            where += " and u.priceKm like ('%" + inputBean.getPricekmSearch() + "%')";
        }
        if (inputBean.getPriceMinSearch() != null && !inputBean.getPriceMinSearch().isEmpty()) {
            where += " and u.priceMin like ('%" + inputBean.getPriceMinSearch() + "%')";
        }
        return where;
    }

    public String insertVehicleType(VehicleTypeInputBean inputBean, Systemaudit audit) throws Exception {
        Session session = null;
        Transaction txn = null;
        String message = "";
        try {
            session = HibernateInit.sessionFactory.openSession();
            Date sysDate = CommonDAO.getSystemDate(session);
            txn = session.beginTransaction();

            String sql = "from VehicleType as u where lower(u.description)=:description ";
            Query query = session.createQuery(sql).setString("description", inputBean.getDescription().trim().toLowerCase());
            if (query.list() == null || query.list().size() < 1) {

                VehicleType v = new VehicleType();
                v.setDescription(inputBean.getDescription().trim());
                v.setBaseFare(new BigDecimal(inputBean.getBaseFare()));
                v.setCommission(new BigDecimal(inputBean.getCommission()));
                v.setPersonCapacity(new Byte(inputBean.getPersonCapacity()));
                v.setPriceKm(new BigDecimal(inputBean.getPriceKm()));
                v.setPriceMin(new BigDecimal(inputBean.getPriceMin()));

                Status st = (Status) session.get(Status.class, inputBean.getStatus().trim());
                v.setStatus(st);

                v.setCreatedtime(sysDate);
                v.setLastupdatedtime(sysDate);
                v.setLastupdateduser(audit.getLastupdateduser());

                String newV = v.getDescription()
                        + "|" + v.getStatus().getDescription();

                audit.setNewvalue(newV);
                audit.setCreatetime(sysDate);
                audit.setLastupdatedtime(sysDate);

                session.save(audit);
                session.save(v);

                txn.commit();

            } else {

                long count = 0;
                String sqlCount = "select count(id) from VehicleType as u where u.status=:statuscode AND lower(u.description)=:description";
                Query queryCount = session.createQuery(sqlCount).setString("statuscode", CommonVarList.STATUS_DELETE)
                        .setString("description", inputBean.getDescription().trim().toLowerCase());

                Iterator itCount = queryCount.iterate();
                count = (Long) itCount.next();

                if (count > 0) {
                    message = "$" + inputBean.getDescription().trim();
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

    public String deleteVehicle(VehicleTypeInputBean inputBean, Systemaudit audit) throws Exception {
        Session session = null;
        Transaction txn = null;
        String message = "";
        try {
            session = HibernateInit.sessionFactory.openSession();
            txn = session.beginTransaction();
            Date sysDate = CommonDAO.getSystemDate(session);

            VehicleType u = (VehicleType) session.get(VehicleType.class, new BigDecimal(inputBean.getId()));
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

    public VehicleType findVehicleById(String id) throws Exception {
        VehicleType v = null;
        Session session = null;
        try {
            session = HibernateInit.sessionFactory.openSession();

            String sql = "from VehicleType as u where u.id=:id";
            Query query = session.createQuery(sql).setString("id", id);
            v = (VehicleType) query.list().get(0);

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

    public String updateVehicleType(VehicleTypeInputBean inputBean, Systemaudit audit) throws Exception {

        Session session = null;
        Transaction txn = null;
        String message = "";

        try {
            session = HibernateInit.sessionFactory.openSession();
            txn = session.beginTransaction();
            Date sysDate = CommonDAO.getSystemDate(session);

            VehicleType u = (VehicleType) session.get(VehicleType.class, new BigDecimal(inputBean.getId()));

            if (u != null) {

                String oldValue = u.getId()
                        + "|" + u.getDescription()
                        + "|" + u.getBaseFare()
                        + "|" + u.getCommission()
                        + "|" + u.getPersonCapacity()
                        + "|" + u.getPriceKm()
                        + "|" + u.getPriceMin()
                        + "|" + u.getStatus().getDescription();

                u.setDescription(inputBean.getDescription().trim());
                u.setBaseFare(new BigDecimal(inputBean.getBaseFare()));
                u.setCommission(new BigDecimal(inputBean.getCommission()));
                u.setPersonCapacity(new Byte(inputBean.getPersonCapacity()));
                u.setPriceKm(new BigDecimal(inputBean.getPriceKm()));
                u.setPriceMin(new BigDecimal(inputBean.getPriceMin()));

                Status st = (Status) session.get(Status.class, inputBean.getStatus().trim());
                u.setStatus(st);

                u.setLastupdatedtime(sysDate);
                u.setLastupdateduser(audit.getLastupdateduser());

                String newV = u.getId()
                        + "|" + inputBean.getDescription()
                        + "|" + inputBean.getBaseFare()
                        + "|" + inputBean.getCommission()
                        + "|" + inputBean.getPersonCapacity()
                        + "|" + inputBean.getPriceKm()
                        + "|" + inputBean.getPriceMin()
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
