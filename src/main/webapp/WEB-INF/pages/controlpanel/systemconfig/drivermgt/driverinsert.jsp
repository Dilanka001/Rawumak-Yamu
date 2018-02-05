<%-- 
    Document   : driverinsert
    Created on : Feb 5, 2018, 4:32:14 PM
    Author     : chanuka
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib  uri="/struts-jquery-tags" prefix="sj"%>
<%@taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="resouces/css/common/common_popup.css">
        <title>Insert Driver</title> 
        <script type="text/javascript">
            $.subscribe('resetAddButton', function(event, data) {
                $('#amessage').empty();
                $('#taskCode').val("");
                $('#description').val("");
                $('#status').val("");
            });
        </script>
    </head>
    <body>
        <s:div id="amessage">
            <s:actionerror theme="jquery"/>
            <s:actionmessage theme="jquery"/>
        </s:div>
        <s:form id="driveradd" method="post" action="Driver" theme="simple" cssClass="form" >
            <div class="row row_popup">

                <div class="col-sm-4">
                    <div class="form-group">
                        <span style="color: red">*</span><label>First Name</label>
                        <s:textfield value="%{firstName}" cssClass="form-control" name="firstName" id="firstName" maxLength="64" onkeyup="$(this).val($(this).val().replace(/[^a-zA-Z ]/g,''))" onmouseout="$(this).val($(this).val().replace(/[^a-zA-Z ]/g,''))"/>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <span style="color: red">*</span><label>Last Name</label>
                        <s:textfield value="%{lastName}" cssClass="form-control" name="lastName" id="lastName" maxLength="6" onkeyup="$(this).val($(this).val().replace(/[^a-zA-Z ]/g,''))" onmouseout="$(this).val($(this).val().replace(/[^a-zA-Z ]/g,''))"/>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <span style="color: red">*</span><label>Address </label>
                        <s:textfield value="%{address}" cssClass="form-control" name="address" id="address" maxLength="255" onkeyup="$(this).val($(this).val().replace(/[^a-zA-Z0-9]/g,''))" onmouseout="$(this).val($(this).val().replace(/[^a-zA-Z0-9/g,''))"/>
                    </div>
                </div>
            </div>  
            <div class="row row_popup">

                <div class="col-sm-4">
                    <div class="form-group">
                        <span style="color: red">*</span><label>Nic</label>
                        <s:textfield value="%{nic}" cssClass="form-control" name="nic" id="nic" maxLength="16" onkeyup="$(this).val($(this).val().replace(/[^a-zA-Z0-9 ]/g,''))" onmouseout="$(this).val($(this).val().replace(/[^a-zA-Z0-9 ]/g,''))"/>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <span style="color: red">*</span><label>License</label>
                        <s:textfield value="%{licence}" cssClass="form-control" name="licence" id="licence" maxLength="20" onkeyup="$(this).val($(this).val().replace(/[^a-zA-Z0-9 ]/g,''))" onmouseout="$(this).val($(this).val().replace(/[^a-zA-Z0-9 ]/g,''))"/>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <span style="color: red">*</span><label>Email</label>
                        <s:textfield value="%{email}" cssClass="form-control" name="email" id="email" maxLength="20" onkeyup="$(this).val($(this).val().replace(/[^a-zA-Z0-9 ]/g,''))" onmouseout="$(this).val($(this).val().replace(/[^a-zA-Z0-9 ]/g,''))"/>
                    </div>
                </div>
            </div>  
            <div class="row row_popup">
                <div class="col-sm-4">
                    <div class="form-group">
                        <span style="color: red">*</span><label>Account</label>
                        <s:textfield value="%{account}" cssClass="form-control" name="account" id="account" maxLength="20" onkeyup="$(this).val($(this).val().replace(/[^a-zA-Z0-9 ]/g,''))" onmouseout="$(this).val($(this).val().replace(/[^a-zA-Z0-9 ]/g,''))"/>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <span style="color: red">*</span><label>Status</label>
                        <s:select value="%{status}" cssClass="form-control" id="status" list="%{statusList}"  name="status" headerKey=""  headerValue="--Select Status--" listKey="statuscode" listValue="description"/>
                    </div>
                </div>
            </div>  

            <div class="row row_popup">
                <div class="horizontal_line_popup"></div>
            </div>
            <div class="row row_popup form-inline">
                <div class="col-sm-9">
                    <div class="form-group">
                        <span class="mandatoryfield">Mandatory fields are marked with *</span>
                    </div>
                </div>
                <div class="col-sm-3  text-right">
                    <div class="form-group" style=" margin-left: 10px;margin-right: 0px;">
                        <sj:submit 
                            button="true" 
                            value="Reset" 
                            name="reset" 
                            cssClass="btn btn-default btn-sm"
                            onClickTopics="resetAddButton"
                            />   
                    </div>
                    <div class="form-group" style=" margin-left: 0px;margin-right: 10px;">
                        <s:url action="AddDriver" var="inserturl"/>
                        <sj:submit
                            button="true"
                            value="Add"
                            href="%{inserturl}"
                            onClickTopics=""
                            targets="amessage"
                            id="addbtn"
                            cssClass="btn btn-sm active" 
                            cssStyle="background-color: #ada9a9"                          
                            />                        
                    </div>
                </div>
            </div>
        </s:form>
    </body>
</html>
