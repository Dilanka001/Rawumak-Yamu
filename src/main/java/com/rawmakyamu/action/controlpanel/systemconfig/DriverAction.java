/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rawmakyamu.action.controlpanel.systemconfig;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.rawmakyamu.action.controlpanel.usermanagement.TaskAction;
import com.rawmakyamu.bean.controlpanel.systemconfig.DriverBean;
import com.rawmakyamu.bean.controlpanel.systemconfig.DriverInputBean;
import com.rawmakyamu.bean.controlpanel.systemconfig.VehicleTypeBean;
import com.rawmakyamu.bean.controlpanel.systemconfig.VehicleTypeInputBean;
import com.rawmakyamu.dao.common.CommonDAO;
import com.rawmakyamu.dao.controlpanel.systemconfig.DriverDAO;
import com.rawmakyamu.dao.controlpanel.systemconfig.VehicleTypeDAO;
import com.rawmakyamu.dao.systemaudit.SystemAuditDAO;
import com.rawmakyamu.util.common.AccessControlService;
import com.rawmakyamu.util.common.Common;
import static com.rawmakyamu.util.common.Common.checkEmptyorNullString;
import static com.rawmakyamu.util.common.Common.checkEmptyorNullString;
import static com.rawmakyamu.util.common.Common.checkEmptyorNullString;
import static com.rawmakyamu.util.common.Common.checkEmptyorNullString;
import com.rawmakyamu.util.common.Validation;
import com.rawmakyamu.util.mapping.Driver;
import com.rawmakyamu.util.mapping.Systemaudit;
import com.rawmakyamu.util.mapping.Task;
import com.rawmakyamu.util.mapping.VehicleType;
import com.rawmakyamu.util.varlist.CommonVarList;
import com.rawmakyamu.util.varlist.MessageVarList;
import com.rawmakyamu.util.varlist.OracleMessage;
import com.rawmakyamu.util.varlist.PageVarList;
import com.rawmakyamu.util.varlist.SectionVarList;
import com.rawmakyamu.util.varlist.SessionVarlist;
import com.rawmakyamu.util.varlist.TaskVarList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author chanuka
 */
public class DriverAction extends ActionSupport implements ModelDriven<Object>, AccessControlService {

    DriverInputBean inputBean = new DriverInputBean();

    public Object getModel() {
        return inputBean;
    }

    public String execute() {
        System.out.println("called VehicleTypeMgtAction : execute");
        return SUCCESS;
    }

    private boolean applyUserPrivileges() {
        HttpServletRequest request = ServletActionContext.getRequest();
        List<Task> tasklist = new Common().getUserTaskListByPage(PageVarList.DRIVER_MGT_PAGE, request);

        if (tasklist != null && tasklist.size() > 0) {
            for (Task task : tasklist) {
                if (task.getTaskcode().toString().equalsIgnoreCase(TaskVarList.ADD_TASK)) {
                    inputBean.setVadd(false);
                } else if (task.getTaskcode().toString().equalsIgnoreCase(TaskVarList.DELETE_TASK)) {
                    inputBean.setVdelete(false);
                } else if (task.getTaskcode().toString().equalsIgnoreCase(TaskVarList.UPDATE_TASK)) {
                    inputBean.setVupdatelink(false);
                } else if (task.getTaskcode().toString().equalsIgnoreCase(TaskVarList.SEARCH_TASK)) {
                    inputBean.setVsearch(false);
                }
            }
        }
        inputBean.setVupdatebutt(true);
        return true;
    }

    public boolean checkAccess(String method, String userRole) {
        boolean status = false;
        String page = PageVarList.DRIVER_MGT_PAGE;
        String task = null;
        if ("view".equals(method)) {
            task = TaskVarList.VIEW_TASK;
        } else if ("List".equals(method)) {
            task = TaskVarList.VIEW_TASK;
        } else if ("Add".equals(method)) {
            task = TaskVarList.ADD_TASK;
        } else if ("Delete".equals(method)) {
            task = TaskVarList.DELETE_TASK;
        } else if ("Find".equals(method)) {
            task = TaskVarList.VIEW_TASK;
        } else if ("Update".equals(method)) {
            task = TaskVarList.UPDATE_TASK;
        } else if ("ViewPopup".equals(method)) {
            task = TaskVarList.VIEW_TASK;
        } else if ("Detail".equals(method)) {
            task = TaskVarList.VIEW_TASK;
        }

        if ("execute".equals(method)) {
            status = true;
        } else {
            HttpServletRequest request = ServletActionContext.getRequest();
            status = new Common().checkMethodAccess(task, page, userRole, request);
        }
        return status;
    }

    public String view() {

        System.out.println("called DriverAction :view");
        String result = "view";

        try {
            if (this.applyUserPrivileges()) {

                CommonDAO dao = new CommonDAO();
                inputBean.setStatusList(dao.getDefultStatusList(CommonVarList.STATUS_CATEGORY_GENERAL));
                inputBean.setDefaultStatus(CommonVarList.STATUS_ACTIVE);

            } else {
                result = "loginpage";
            }

            HttpSession session = ServletActionContext.getRequest().getSession(false);
            if (session.getAttribute(SessionVarlist.MIN_PASSWORD_CHANGE_PERIOD) != null && session.getAttribute(SessionVarlist.ONLY_SHOW_ONTIME) != null) {
                if ((Integer) session.getAttribute(SessionVarlist.ONLY_SHOW_ONTIME) == 0) {
                    session.setAttribute(SessionVarlist.ONLY_SHOW_ONTIME, 1);
                    addActionError((String) session.getAttribute(SessionVarlist.MIN_PASSWORD_CHANGE_PERIOD));
                }
            }

        } catch (Exception ex) {
            addActionError("Driver " + MessageVarList.COMMON_ERROR_PROCESS);
            Logger.getLogger(DriverAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public String ViewPopup() {
        String result = "viewpopup";
        System.out.println("called DriverAction : ViewPopup");
        try {
            if (this.applyUserPrivileges()) {

                CommonDAO dao = new CommonDAO();
                inputBean.setStatusList(dao.getDefultStatusList(CommonVarList.STATUS_CATEGORY_GENERAL));
                inputBean.setDefaultStatus(CommonVarList.STATUS_ACTIVE);

            } else {
                result = "loginpage";
            }

            HttpSession session = ServletActionContext.getRequest().getSession(false);
            if (session.getAttribute(SessionVarlist.MIN_PASSWORD_CHANGE_PERIOD) != null && session.getAttribute(SessionVarlist.ONLY_SHOW_ONTIME) != null) {
                if ((Integer) session.getAttribute(SessionVarlist.ONLY_SHOW_ONTIME) == 0) {
                    session.setAttribute(SessionVarlist.ONLY_SHOW_ONTIME, 1);
                    addActionError((String) session.getAttribute(SessionVarlist.MIN_PASSWORD_CHANGE_PERIOD));
                }
            }

        } catch (Exception ex) {
            addActionError("Driver " + MessageVarList.COMMON_ERROR_PROCESS);
            Logger.getLogger(DriverAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public String List() {
        System.out.println("called DriverAction: List");
        try {
            //if (inputBean.isSearch()) {

            int rows = inputBean.getRows();
            int page = inputBean.getPage();
            int to = (rows * page);
            int from = to - rows;
            long records = 0;
            String orderBy = "";
            if (!inputBean.getSidx().isEmpty()) {
                orderBy = " order by " + inputBean.getSidx() + " " + inputBean.getSord();
            }
            DriverDAO dao = new DriverDAO();
            List<DriverBean> dataList = dao.getSearchList(inputBean, rows, from, orderBy);

            /**
             * for search audit
             */
            if (inputBean.isSearch() && from == 0) {

                HttpServletRequest request = ServletActionContext.getRequest();

                String searchParameters = "["
                        + checkEmptyorNullString("First Name", inputBean.getFirstNameSearch())
                        + checkEmptyorNullString("Last Name", inputBean.getLastNameSearch())
                        + checkEmptyorNullString("Address", inputBean.getAddressSearch())
                        + checkEmptyorNullString("Nic", inputBean.getNicSearch())
                        + checkEmptyorNullString("Licence", inputBean.getLicenceSearch())
                        + checkEmptyorNullString("Email", inputBean.getEmailSearch())
                        + checkEmptyorNullString("Account", inputBean.getAccountSearch())
                        + checkEmptyorNullString("Status", inputBean.getStatusSearch())
                        + "]";
                Systemaudit audit = Common.makeAudittrace(request, TaskVarList.SEARCH_TASK, PageVarList.DRIVER_MGT_PAGE, SectionVarList.USERMANAGEMENT, "Driver search using " + searchParameters + " parameters ", null);
                SystemAuditDAO sysdao = new SystemAuditDAO();
                sysdao.saveAudit(audit);
            }

            if (!dataList.isEmpty()) {
                records = dataList.get(0).getFullCount();
                inputBean.setRecords(records);
                inputBean.setGridModel(dataList);
                int total = (int) Math.ceil((double) records / (double) rows);
                inputBean.setTotal(total);
            } else {
                inputBean.setRecords(0L);
                inputBean.setTotal(0);
            }
            // }
        } catch (Exception e) {
            Logger.getLogger(DriverAction.class.getName()).log(Level.SEVERE, null, e);
            addActionError(" Driver " + MessageVarList.COMMON_ERROR_PROCESS);
        }
        return "list";
    }

    public String Delete() {
        System.out.println("called DriverAction : Delete");
        String message = null;
        String retType = "delete";
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            DriverDAO dao = new DriverDAO();
            Systemaudit audit = Common.makeAudittrace(request, TaskVarList.DELETE_TASK, PageVarList.DRIVER_MGT_PAGE, SectionVarList.USERMANAGEMENT, "Driver" + inputBean.getNic() + " deleted", null);
            message = dao.deleteDriver(inputBean, audit);
            if (message.isEmpty()) {
                message = "Driver  " + MessageVarList.COMMON_SUCC_DELETE;
            }
            inputBean.setMessage(message);
        } catch (Exception e) {
            Logger.getLogger(DriverAction.class.getName()).log(Level.SEVERE, null, e);
            inputBean.setMessage(OracleMessage.getMessege(e.getMessage()));
        }
        return retType;
    }

    public String Add() {
        System.out.println("called DriverAction : add");
        String result = "message";
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            DriverDAO dao = new DriverDAO();
            String message = this.validateInputs();

            if (message.isEmpty()) {

                Systemaudit audit = Common.makeAudittrace(request, TaskVarList.ADD_TASK, PageVarList.DRIVER_MGT_PAGE, SectionVarList.USERMANAGEMENT, "Driver " + inputBean.getNic() + " added", null, null, null);
                message = dao.insertDriver(inputBean, audit);

                if (message.isEmpty()) {
                    addActionMessage("Driver " + MessageVarList.COMMON_SUCC_INSERT);
                } else {
                    addActionError(message);
                }
            } else {
                addActionError(message);
            }

        } catch (Exception ex) {
            addActionError("Driver " + MessageVarList.COMMON_ERROR_PROCESS);
            Logger.getLogger(DriverAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    private String validateInputs() {
        String message = "";
        if (inputBean.getNic() == null || inputBean.getNic().trim().isEmpty()) {
            message = MessageVarList.TASK_MGT_EMPTY_DESCRIPTION;
        } else if (inputBean.getStatus() != null && inputBean.getStatus().isEmpty()) {
            message = MessageVarList.TASK_MGT_EMPTY_STATUS;
        } else if (!Validation.isSpecailCharacter(inputBean.getNic())) {
            message = MessageVarList.TASK_MGT_ERROR_DESC_INVALID;
        }
        return message;
    }

    public String Find() {
        System.out.println("called DriverAction: Find");
        Driver driver = null;
        try {
            if (inputBean.getId() != null && !inputBean.getId().isEmpty()) {

                DriverDAO dao = new DriverDAO();

                driver = dao.findDriverById(inputBean.getId());

                inputBean.setId(driver.getId().toString());
                inputBean.setFirstName(driver.getFirstname());
                inputBean.setLastName(driver.getLastname().toString());
                inputBean.setAddress(driver.getAddress().toString());
                inputBean.setNic(driver.getNic().toString());
                inputBean.setLicence(driver.getLicence().toString());
                inputBean.setEmail(driver.getEmail().toString());
                inputBean.setAccount(driver.getAccount().toString());
                inputBean.setStatus(driver.getStatus().getStatuscode());

            } else {
                inputBean.setMessage("Empty Driver.");
            }
        } catch (Exception ex) {
            inputBean.setMessage("Driver " + MessageVarList.COMMON_ERROR_PROCESS);
            Logger.getLogger(DriverAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "find";

    }

    public String Detail() {
        System.out.println("called DriverAction: Detail");
        Driver driver = null;
        try {
            if (inputBean.getId() != null && !inputBean.getId().isEmpty()) {

                DriverDAO dao = new DriverDAO();
                CommonDAO commonDAO = new CommonDAO();

                inputBean.setStatusList(commonDAO.getDefultStatusList(CommonVarList.STATUS_CATEGORY_GENERAL));
                inputBean.setDefaultStatus(CommonVarList.STATUS_ACTIVE);

                driver = dao.findDriverById(inputBean.getId());

                inputBean.setId(driver.getId().toString());
                inputBean.setFirstName(driver.getFirstname());
                inputBean.setLastName(driver.getLastname().toString());
                inputBean.setAddress(driver.getAddress().toString());
                inputBean.setNic(driver.getNic().toString());
                inputBean.setLicence(driver.getLicence().toString());
                inputBean.setEmail(driver.getEmail().toString());
                inputBean.setAccount(driver.getAccount().toString());
                inputBean.setStatus(driver.getStatus().getStatuscode());

            } else {
                inputBean.setMessage("Empty Driver.");
            }
        } catch (Exception ex) {
            inputBean.setMessage("Driver " + MessageVarList.COMMON_ERROR_PROCESS);
            Logger.getLogger(DriverAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "detail";
    }
    
    public String Update() {

        System.out.println("called DriverAction : update");
        String retType = "message";

        try {
            if (inputBean.getId() != null && !inputBean.getId().isEmpty()) {
                DriverDAO dao = new DriverDAO();
                inputBean.setId(inputBean.getId());

                String message = this.validateUpdates();

                if (message.isEmpty()) {

                    HttpServletRequest request = ServletActionContext.getRequest();

                    Systemaudit audit = Common.makeAudittrace(request, TaskVarList.UPDATE_TASK, PageVarList.DRIVER_MGT_PAGE, SectionVarList.USERMANAGEMENT, "Driver " + inputBean.getNic() + " updated", null, null, null);
                    message = dao.updateDriver(inputBean, audit);

                    if (message.isEmpty()) {
                        addActionMessage("Driver " + MessageVarList.COMMON_SUCC_UPDATE);
                    } else {
                        addActionError(message);
                    }

                } else {
                    addActionError(message);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(DriverAction.class.getName()).log(Level.SEVERE, null, ex);
            addActionError("Driver " + MessageVarList.COMMON_ERROR_UPDATE);
        }
        return retType;
    }

    private String validateUpdates() {
        String message = "";
        if (inputBean.getId() == null || inputBean.getId().trim().isEmpty()) {
            message = MessageVarList.TASK_MGT_EMPTY_TASK_CODE;
        } else if (inputBean.getNic() == null || inputBean.getNic().trim().isEmpty()) {
            message = MessageVarList.TASK_MGT_EMPTY_DESCRIPTION;
        }
        else if (inputBean.getStatus() != null && inputBean.getStatus().isEmpty()) {
            message = MessageVarList.TASK_MGT_EMPTY_STATUS;
        } else if (!Validation.isSpecailCharacter(inputBean.getId())) {
            message = MessageVarList.TASK_MGT_ERROR_TASKCODE_INVALID;
        } else if (!Validation.isSpecailCharacter(inputBean.getNic())) {
            message = MessageVarList.TASK_MGT_ERROR_DESC_INVALID;
        }
        return message;
    }
}
