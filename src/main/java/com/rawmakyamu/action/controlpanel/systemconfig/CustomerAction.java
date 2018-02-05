/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rawmakyamu.action.controlpanel.systemconfig;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.rawmakyamu.bean.controlpanel.systemconfig.CustomerBean;
import com.rawmakyamu.bean.controlpanel.systemconfig.CustomerInputBean;
import com.rawmakyamu.bean.controlpanel.systemconfig.DriverBean;
import com.rawmakyamu.dao.common.CommonDAO;
import com.rawmakyamu.dao.controlpanel.systemconfig.CustomerDAO;
import com.rawmakyamu.dao.controlpanel.systemconfig.DriverDAO;
import com.rawmakyamu.dao.systemaudit.SystemAuditDAO;
import com.rawmakyamu.util.common.AccessControlService;
import com.rawmakyamu.util.common.Common;
import static com.rawmakyamu.util.common.Common.checkEmptyorNullString;
import static com.rawmakyamu.util.common.Common.checkEmptyorNullString;
import static com.rawmakyamu.util.common.Common.checkEmptyorNullString;
import com.rawmakyamu.util.common.Validation;
import com.rawmakyamu.util.mapping.Customer;
import com.rawmakyamu.util.mapping.Driver;
import com.rawmakyamu.util.mapping.Systemaudit;
import com.rawmakyamu.util.mapping.Task;
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
public class CustomerAction extends ActionSupport implements ModelDriven<Object>, AccessControlService {

    CustomerInputBean inputBean = new CustomerInputBean();

    public Object getModel() {
        return inputBean;
    }

    public String execute() {
        System.out.println("called CustomerAction : execute");
        return SUCCESS;
    }

    private boolean applyUserPrivileges() {
        HttpServletRequest request = ServletActionContext.getRequest();
        List<Task> tasklist = new Common().getUserTaskListByPage(PageVarList.CUSTOMER_MGT_PAGE, request);

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
        String page = PageVarList.CUSTOMER_MGT_PAGE;
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

        System.out.println("called CustomerAction :view");
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
            addActionError("Customer " + MessageVarList.COMMON_ERROR_PROCESS);
            Logger.getLogger(CustomerAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public String List() {
        System.out.println("called CustomerAction: List");
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
            CustomerDAO dao = new CustomerDAO();
            List<CustomerBean> dataList = dao.getSearchList(inputBean, rows, from, orderBy);

            /**
             * for search audit
             */
            if (inputBean.isSearch() && from == 0) {

                HttpServletRequest request = ServletActionContext.getRequest();

                String searchParameters = "["
                        + checkEmptyorNullString("First Name", inputBean.getFirstNameSearch())
                        + checkEmptyorNullString("Last Name", inputBean.getLastNameSearch())
                        + checkEmptyorNullString("Nic", inputBean.getNicSearch())
                        + checkEmptyorNullString("Mobile", inputBean.getMobile())
                        + checkEmptyorNullString("Email", inputBean.getEmailSearch())
                        + checkEmptyorNullString("Status", inputBean.getStatusSearch())
                        + "]";
                Systemaudit audit = Common.makeAudittrace(request, TaskVarList.SEARCH_TASK, PageVarList.CUSTOMER_MGT_PAGE, SectionVarList.USERMANAGEMENT, "Customer search using " + searchParameters + " parameters ", null);
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
            Logger.getLogger(CustomerAction.class.getName()).log(Level.SEVERE, null, e);
            addActionError(" Customer " + MessageVarList.COMMON_ERROR_PROCESS);
        }
        return "list";
    }

    public String Delete() {
        System.out.println("called CustomerAction : Delete");
        String message = null;
        String retType = "delete";
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            CustomerDAO dao = new CustomerDAO();
            Systemaudit audit = Common.makeAudittrace(request, TaskVarList.DELETE_TASK, PageVarList.CUSTOMER_MGT_PAGE, SectionVarList.USERMANAGEMENT, "Customer" + inputBean.getNic() + " deleted", null);
            message = dao.deleteCustomer(inputBean, audit);
            if (message.isEmpty()) {
                message = "Customer  " + MessageVarList.COMMON_SUCC_DELETE;
            }
            inputBean.setMessage(message);
        } catch (Exception e) {
            Logger.getLogger(CustomerAction.class.getName()).log(Level.SEVERE, null, e);
            inputBean.setMessage(OracleMessage.getMessege(e.getMessage()));
        }
        return retType;
    }

    public String Find() {
        System.out.println("called CustomerAction: Find");
        Customer customer = null;
        try {
            if (inputBean.getId() != null && !inputBean.getId().isEmpty()) {

                CustomerDAO dao = new CustomerDAO();

                customer = dao.findCustomerById(inputBean.getId());

                inputBean.setId(customer.getId().toString());
                inputBean.setFirstName(customer.getFirstname());
                inputBean.setLastName(customer.getLastname().toString());
                inputBean.setNic(customer.getNic().toString());
                inputBean.setMobile(customer.getMobile().toString());
                inputBean.setEmail(customer.getEmail().toString());
                inputBean.setStatus(customer.getStatus().getStatuscode());

            } else {
                inputBean.setMessage("Empty Customer.");
            }
        } catch (Exception ex) {
            inputBean.setMessage("Customer " + MessageVarList.COMMON_ERROR_PROCESS);
            Logger.getLogger(CustomerAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "find";

    }

    public String Detail() {
        System.out.println("called CustomerAction: Detail");
        Customer customer = null;
        try {
            if (inputBean.getId() != null && !inputBean.getId().isEmpty()) {

                CustomerDAO dao = new CustomerDAO();
                CommonDAO commonDAO = new CommonDAO();

                inputBean.setStatusList(commonDAO.getDefultStatusList(CommonVarList.STATUS_CATEGORY_GENERAL));
                inputBean.setDefaultStatus(CommonVarList.STATUS_ACTIVE);

                customer = dao.findCustomerById(inputBean.getId());

                inputBean.setId(customer.getId().toString());
                inputBean.setFirstName(customer.getFirstname());
                inputBean.setLastName(customer.getLastname().toString());
                inputBean.setNic(customer.getNic().toString());
                inputBean.setMobile(customer.getMobile().toString());
                inputBean.setEmail(customer.getEmail().toString());
                inputBean.setStatus(customer.getStatus().getStatuscode());

            } else {
                inputBean.setMessage("Empty Customer.");
            }
        } catch (Exception ex) {
            inputBean.setMessage("Customer " + MessageVarList.COMMON_ERROR_PROCESS);
            Logger.getLogger(CustomerAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "detail";
    }

    public String Update() {

        System.out.println("called CustomerAction : update");
        String retType = "message";

        try {
            if (inputBean.getId() != null && !inputBean.getId().isEmpty()) {
                CustomerDAO dao = new CustomerDAO();
                inputBean.setId(inputBean.getId());

                String message = this.validateUpdates();

                if (message.isEmpty()) {

                    HttpServletRequest request = ServletActionContext.getRequest();

                    Systemaudit audit = Common.makeAudittrace(request, TaskVarList.UPDATE_TASK, PageVarList.CUSTOMER_MGT_PAGE, SectionVarList.USERMANAGEMENT, "Customer " + inputBean.getNic() + " updated", null, null, null);
                    message = dao.updateCustomer(inputBean, audit);

                    if (message.isEmpty()) {
                        addActionMessage("Customer " + MessageVarList.COMMON_SUCC_UPDATE);
                    } else {
                        addActionError(message);
                    }

                } else {
                    addActionError(message);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(CustomerAction.class.getName()).log(Level.SEVERE, null, ex);
            addActionError("Customer " + MessageVarList.COMMON_ERROR_UPDATE);
        }
        return retType;
    }

    private String validateUpdates() {
        String message = "";
        if (inputBean.getId() == null || inputBean.getId().trim().isEmpty()) {
            message = MessageVarList.TASK_MGT_EMPTY_TASK_CODE;
        } else if (inputBean.getNic() == null || inputBean.getNic().trim().isEmpty()) {
            message = MessageVarList.TASK_MGT_EMPTY_DESCRIPTION;
        } else if (inputBean.getStatus() != null && inputBean.getStatus().isEmpty()) {
            message = MessageVarList.TASK_MGT_EMPTY_STATUS;
        } else if (!Validation.isSpecailCharacter(inputBean.getId())) {
            message = MessageVarList.TASK_MGT_ERROR_TASKCODE_INVALID;
        } else if (!Validation.isSpecailCharacter(inputBean.getNic())) {
            message = MessageVarList.TASK_MGT_ERROR_DESC_INVALID;
        }
        return message;
    }
}
