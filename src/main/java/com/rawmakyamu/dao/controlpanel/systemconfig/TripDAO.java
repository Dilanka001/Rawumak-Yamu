/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rawmakyamu.dao.controlpanel.systemconfig;

import com.rawmakyamu.bean.controlpanel.systemconfig.CustomerBean;
import com.rawmakyamu.bean.controlpanel.systemconfig.CustomerInputBean;
import com.rawmakyamu.bean.controlpanel.systemconfig.TripBean;
import com.rawmakyamu.bean.controlpanel.systemconfig.TripInputBean;
import com.rawmakyamu.util.common.HibernateInit;
import com.rawmakyamu.util.mapping.Customer;
import com.rawmakyamu.util.mapping.Trip;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author chanuka
 */
public class TripDAO {

    public List<TripBean> getSearchList(TripInputBean inputBean, int max, int first, String orderBy) throws Exception {

        List<TripBean> dataList = new ArrayList<TripBean>();
        Session session = null;
        try {

            if (orderBy.equals("") || orderBy == null) {
                orderBy = "order by u.createdtime desc";
            }

            long count = 0;
            String where = this.makeWhereClause(inputBean);

            session = HibernateInit.sessionFactory.openSession();

            String sqlCount = "select count(id) from Trip as u where " + where;
            Query queryCount = session.createQuery(sqlCount);

            Iterator itCount = queryCount.iterate();
            count = (Long) itCount.next();

            if (count > 0) {

                String sqlSearch = "from Trip u where " + where + orderBy;
                Query querySearch = session.createQuery(sqlSearch);
                querySearch.setMaxResults(max);
                querySearch.setFirstResult(first);

                Iterator it = querySearch.iterate();

                while (it.hasNext()) {

                    TripBean trip = new TripBean();
                    Trip v = (Trip) it.next();

                    try {
                        trip.setId(v.getId().toString());
                    } catch (NullPointerException npe) {
                        trip.setId("--");
                    }
                    try {
                        trip.setCustomer(v.getCustomer().getFirstname().toString());
                    } catch (NullPointerException npe) {
                        trip.setCustomer("--");
                    }
                    try {
                        trip.setDriver(v.getDriver().getFirstname().toString());
                    } catch (NullPointerException npe) {
                        trip.setDriver("--");
                    }
                    try {
                        trip.setVehicle(v.getVehicleType().getDescription().toString());
                    } catch (NullPointerException npe) {
                        trip.setVehicle("--");
                    }
                    try {
                        trip.setSource(v.getSource().toString());
                    } catch (NullPointerException npe) {
                        trip.setSource("--");
                    }
                    try {
                        trip.setDestination(v.getDestination().toString());
                    } catch (NullPointerException npe) {
                        trip.setDestination("--");
                    }
                    try {
                        trip.setStartedTime(v.getStartedtime().toString());
                    } catch (NullPointerException npe) {
                        trip.setStartedTime("--");
                    }
                    try {
                        trip.setEndTime(v.getEndtime().toString());
                    } catch (NullPointerException npe) {
                        trip.setEndTime("--");
                    }
                    try {
                        trip.setStatus(v.getStatus().getDescription());
                    } catch (NullPointerException npe) {
                        trip.setStatus("--");
                    }
                    try {
                        trip.setFare(v.getFare().toString());
                    } catch (NullPointerException npe) {
                        trip.setFare("--");
                    }
                    try {
                        if (v.getCreatedtime() != null && !v.getCreatedtime().toString().isEmpty()) {
                            trip.setCreatedtime(v.getCreatedtime().toString());
//                            vehicle.setCreatedtime(v.getCreatedtime().toString().substring(0, 19));
                        } else {
                            trip.setCreatedtime("--");
                        }

                    } catch (NullPointerException npe) {
                        trip.setCreatedtime("--");
                    }

                    trip.setFullCount(count);

                    dataList.add(trip);
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

    private String makeWhereClause(TripInputBean inputBean) {
        String where = "1=1";

        if (inputBean.getCustomerSearch() != null && !inputBean.getCustomerSearch().isEmpty()) {
            where += " and lower(u.customer.firstname) like lower('%" + inputBean.getCustomerSearch() + "%')";
        }
        if (inputBean.getStatusSearch() != null && !inputBean.getStatusSearch().isEmpty()) {
            where += " and u.status.statuscode = '" + inputBean.getStatusSearch() + "'";
        }
        if (inputBean.getDriverSearch() != null && !inputBean.getDriverSearch().isEmpty()) {
            where += " and lower(u.driver.firstname)  like lower('%" + inputBean.getDriverSearch() + "%')";
        }
        if (inputBean.getVehicleSearch() != null && !inputBean.getVehicleSearch().isEmpty()) {
            where += " and lower(u.vehicleType.description) like lower('%" + inputBean.getVehicleSearch() + "%')";
        }
        if (inputBean.getSourceSearch() != null && !inputBean.getSourceSearch().isEmpty()) {
            where += " and lower(u.source) like lower('%" + inputBean.getSourceSearch() + "%')";
        }
        if (inputBean.getDestinationSearch() != null && !inputBean.getDestinationSearch().isEmpty()) {
            where += " and lower(u.destination) like lower('%" + inputBean.getDestinationSearch() + "%')";
        }
        if (inputBean.getStartedTimeSearch() != null && !inputBean.getStartedTimeSearch().isEmpty()) {
            where += " and lower(u.startedtime) like lower('%" + inputBean.getStartedTimeSearch() + "%')";
        }
        if (inputBean.getEndTimeSearch() != null && !inputBean.getEndTimeSearch().isEmpty()) {
            where += " and lower(u.endtime) like lower('%" + inputBean.getEndTimeSearch() + "%')";
        }
        if (inputBean.getFareSearch() != null && !inputBean.getFareSearch().isEmpty()) {
            where += " and lower(u.fare) like lower('%" + inputBean.getFareSearch() + "%')";
        }
        return where;
    }
}
